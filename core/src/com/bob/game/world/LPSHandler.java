package com.bob.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.bob.lps.controller.syntax.JLPSSyntaxLexer;
import com.bob.lps.controller.syntax.JLPSSyntaxParser;
import com.bob.lps.model.*;
import com.bob.main.Config;
import org.antlr.runtime.*;

import java.util.*;

public class LPSHandler implements InstructionStrategy {

    private final Set<String> facts = new HashSet<>();
    private final Set<String> actions  = new HashSet<>();

    public LPSHandler(String worldDescription, String rules, int x, int y) {
        this(worldDescription, "", rules, x, y);
    }

    public LPSHandler(String worldDescription, String objectDescription, String rules, int x, int y) {
        try {
            resetLPS();
            StringBuilder lpsString = new StringBuilder("Database {\n\tFacts {\n\t\t");
            lpsString.append(objectDescription);
            lpsString.append("lights(0).\n\tisIn(" + x + "," + y + ").\n"); //	wasIn(" + x + "," + y + ").

            lpsString.append("\t}\n\nRules {\n\t\t");

            FileHandle middleScript = Gdx.files.internal("scripts/middle");
            FileHandle tailScript = Gdx.files.internal("scripts/tail");

            lpsString.append(worldDescription);
            lpsString.append(middleScript.readString());
            lpsString.append(rules);
            lpsString.append(tailScript.readString());

            if (Config.printLPS) {
                System.out.println(lpsString.toString());
            }

            CharStream stream = new ANTLRStringStream(lpsString.toString());
            streamReader(stream);
            
        } catch (Exception e) {
            System.out.println("Unable to load script");
        }

        setLimit();
    }

    public LPSHandler() {
        // Empty for test purposes, should not be used in production
    }

    // LPS usage could be optimized here if needed
    private void resetLPS() {
        CycleHandler.reset();
        Database.reset();
        GoalsList.reset();
        ReactiveRuleSet.reset();
    }

    private void streamReader(CharStream fileStream) throws RecognitionException {
        JLPSSyntaxLexer lexer = new JLPSSyntaxLexer(fileStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        JLPSSyntaxParser parser = new JLPSSyntaxParser(tokenStream);
        JLPSSyntaxParser.file_return returns = parser.file();

        this.facts.addAll(returns.facts);
        this.actions.addAll(returns.actions);
    }

    private void setLimit() {
        Map<String, Integer> limits = new HashMap<>();
        if (this.facts != null) {
            for(String fact : this.facts) {
                limits.put(fact, Config.LPSLimit);
            }
        }

        if (this.actions != null) {
            for(String action : this.actions) {
                limits.put(action, 2);
            }
        }

        Database.getInstance().setLimits(limits);
    }

    @Override
    public void update() {
        CycleHandler.getInstance().updateFireAndSolve();
    }

    private RuleSet getEvents() {
        return CycleHandler.getInstance().getEvents();
    }

    @Override
    public EntityState getState() {

        Set<Goal> set = GoalsList.getInstance().getActiveGoals();
        Iterator<Goal> it = set.iterator();

        if (hasContradictoryInstruction(set)) {
            return EntityState.CONFUSED;
        }

        while (it.hasNext()) {
            Goal g = it.next();
            switch (g.getGoal().getTerm(0).toString()) {
                case "goRight":
                    return EntityState.WALK_RIGHT;
                case "goLeft":
                    return EntityState.WALK_LEFT;
                case "goUp":
                    return EntityState.WALK_UP;
                case "goDown":
                    return EntityState.WALK_DOWN;
            }
        }

        return null;
    }

    public List<Integer> getAppliedRuleIndexes() {
        List<Integer> result = new ArrayList<Integer>();;

        Set<Goal> set = GoalsList.getInstance().getActiveGoals();
        Iterator<Goal> it = set.iterator();


        while (it.hasNext()) {
            Goal g = it.next();
            switch (g.getGoal().getTerm(0).toString()) {
                case "goRight":
                case "goLeft":
                case "goUp":
                case "goDown":
                case "wait":
                    result.add(Integer.parseInt(g.getGoal().getTerm(1).getName()));
                    break;
            }
        }

        return result;
    }

    private boolean hasContradictoryInstruction(Set<Goal> set) {

        int noGoRight = 0;
        int noGoLeft = 0;
        int noGoUp = 0;
        int noGoDown = 0;

        boolean result = false;

        for (Goal g: set) {
            switch (g.getGoal().getTerm(0).toString()) {
                case "goRight":
                    noGoRight++;
                    result |= (noGoDown + noGoLeft + noGoUp) > 0;
                    break;
                case "goLeft":
                    noGoLeft++;
                    result |= (noGoDown + noGoRight + noGoUp) > 0;
                    break;
                case "goUp":
                    noGoUp++;
                    result |= (noGoDown + noGoLeft + noGoRight) > 0;
                    break;
                case "goDown":
                    noGoDown++;
                    result |= (noGoUp + noGoLeft + noGoRight) > 0;
                    break;
            }
        }

        return result;
    }

    private int convertToInt(String s) {
        return Integer.parseInt(s.charAt(0) == 'm' ? "-" + s.substring(1) : s);
    }

}
