package com.lps.game;

import com.lps.controller.syntax.JLPSSyntaxLexer;
import com.lps.controller.syntax.JLPSSyntaxParser;
import com.lps.model.CycleHandler;
import com.lps.model.Database;
import com.lps.model.RuleSet;
import com.lps.model.SimpleSentence;
import org.antlr.runtime.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LPSHandler {

    private Set<String> facts = new HashSet<String>();
    private Set<String> actions  = new HashSet<String>();

    public LPSHandler(String scriptName) {
        try {
            ANTLRFileStream stream = new ANTLRFileStream("scripts/" + scriptName);
            fileReader(stream);
        } catch (Exception e) {
            System.out.println("Unable to load script");
        }

        setLimit();
    }

    public void fileReader(CharStream fileStream) throws RecognitionException {
        JLPSSyntaxLexer lexer = new JLPSSyntaxLexer(fileStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        JLPSSyntaxParser parser = new JLPSSyntaxParser(tokenStream);
        JLPSSyntaxParser.file_return returns = parser.file();
        boolean[] warnings = returns.w;

        this.facts.addAll(returns.facts);
        this.actions.addAll(returns.actions);

        if (Config.LPSDebug) {
            if (warnings[0]) {
                System.out.println("\u001B[33m" + "/!\\ No initial facts defined" + "\u001B[37m");
            }
            if (warnings[1]) {
                System.out.println("\u001B[33m" + "/!\\ No intensional rules defined" + "\u001B[37m");
            }
            if (warnings[2]) {
                System.out.println("\u001B[33m" + "/!\\ No domain theory defined" + "\u001B[37m");
            }
            if (warnings[3]) {
                System.out.println("\u001B[33m" + "/!\\ No reactive rules defined" + "\u001B[37m");
            }
            if (warnings[4]) {
                System.out.println("\u001B[33m" + "/!\\ No goals defined" + "\u001B[37m");
            }
            if (warnings[5]) {
                System.out.println("\u001B[33m" + "/!\\ No initial events defined" + "\u001B[37m");
            }
        }
    }

    private void setLimit() {
        Map<String, Integer> limits = new HashMap<String, Integer>();
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

    public RuleSet getEvents() {
        return CycleHandler.getInstance().getEvents();
    }

    public EntityState getNewState() {
        RuleSet instructions = getEvents();
        if (instructions.getRuleCount() > 0) {
            SimpleSentence nextRule = instructions.getRule(0).getHead();
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
