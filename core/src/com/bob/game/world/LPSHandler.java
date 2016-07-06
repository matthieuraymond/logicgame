package com.bob.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.bob.lps.controller.syntax.JLPSSyntaxLexer;
import com.bob.lps.controller.syntax.JLPSSyntaxParser;
import com.bob.lps.model.*;
import org.antlr.runtime.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LPSHandler {

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
            lpsString.append("lights(0).\n\tisIn(" + x + "," + y + ").\n\twasIn(" + x + "," + y + ").\n");

            lpsString.append("\t}\n\nRules {\n\t\t");

            FileHandle middleScript = Gdx.files.internal("resources/scripts/middle");
            FileHandle tailScript = Gdx.files.internal("resources/scripts/tail");

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
    }

    // TODO - optimize LPS usage
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

    public void update() {
        CycleHandler.getInstance().updateFireAndSolve();
    }

    private RuleSet getEvents() {
        return CycleHandler.getInstance().getEvents();
    }

    public EntityState getNewState() {
        /* Todo: eventually refactor to use goRight and moveTo instead of moveFromTo see lights in JLPS folder */

        RuleSet instructions = getEvents();
        if (instructions.getRuleCount() > 0) {
            SimpleSentence nextRule = instructions.getRule(0).getHead();

            // TODO REFACTOR DAT SHIT
            if (!nextRule.getTerm(0).toString().equals("moveFromTo")) return null;

            int fromX = convertToInt(nextRule.getTerm(1).toString());
            int fromY = convertToInt(nextRule.getTerm(2).toString());
            int toX = convertToInt(nextRule.getTerm(3).toString());
            int toY = convertToInt(nextRule.getTerm(4).toString());

            if (toX > fromX) {
                return EntityState.WALK_RIGHT;
            } else if (toX < fromX) {
                return EntityState.WALK_LEFT;
            } else if (toY > fromY) {
                return EntityState.WALK_UP;
            } else if (toY < fromY) {
                return EntityState.WALK_DOWN;
            }
        }
        return null;
    }

    private int convertToInt(String s) {
        return Integer.parseInt(s.charAt(0) == 'm' ? "-" + s.substring(1) : s);
    }

}
