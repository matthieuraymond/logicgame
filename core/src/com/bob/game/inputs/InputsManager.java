package com.bob.game.inputs;

import com.bob.game.Level;

public class InputsManager {

    private Rule[] rules;
    private InputsLayer layer;

    public InputsManager(InputsLayer layer) {
        this.rules = new Rule[8];
        this.layer = layer;

        layer.initRules(rules);
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
        layer.clearInputs();
    }

    public void resetInputs(Block[] blocks) {

        resetInputs();

        for (Block b: blocks) {
            layer.createInput(b);
        }
    }

    public String getRulesString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            res.append(rules[i].getString());
        }

        return res.toString();
    }

    public void lockRules(Level level) {
        layer.lockRules(level.getNoRules());
    }

    public void setupInputs(Level level) {
        resetInputs(level.getInputs());
        lockRules(level);
        resetRules();
    }
}
