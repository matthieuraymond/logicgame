package com.bob.game;

public class InputsManager {

    Rule[] rules;
    PlayView view;

    public InputsManager(PlayView view) {
        this.rules = new Rule[8];
        this.view = view;

        // Todo put in strat pattern
        view.initRules(rules);
    }

    public boolean checkRules() {
        boolean allValid = true;

        for (Rule rule : rules) {
            allValid &= (rule.isValid());
        }

        return allValid;
    }

    public void resetRules() {
        for (Rule r: rules) {
            r.reset();
        }
    }

    public void resetRules(Rule[] newRules) {
        resetRules();

        for (int i = 0; i < newRules.length && i < rules.length; i++) {
            rules[i] = newRules[i];
        }
    }

    public void resetInputs() {
        view.clearInputs();
    }

    public void resetInputs(String[] colors, boolean prevAuthorised) {

        resetInputs();

        for (String color : colors) {
            view.createInput(color + "(X,Y)", color, Type.FLUENT, "If Bob is on a " + color + " cell");
            if (prevAuthorised) {
                view.createInput(color + "(U,V)", color + "_prev", Type.FLUENT, "If Bob was previously on a " + color + " cell");
            }
        }

        for (String direction : new String[]{"Left", "Right", "Up", "Down"}) {
            view.createInput("go" + direction + "()", direction, Type.CONSEQUENT, "Bob should go " + direction);
        }

        view.createInput("&", "and", Type.AND, "AND, to be used in: if a AND b");
        view.createInput("->", "imply", Type.IMPLY, "IMPLY/THEN, to be used in: if a THEN b");
        view.createInput("!", "not", Type.NOT, "NOT, to be used in: NOT a");
    }

    public String getRulesString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            res.append(rules[i].getString());
        }

        return res.toString();
    }

    public void lockRules(Level level) {
        view.lockRules(((WriteLevel)level).getNoRules());
    }
}
