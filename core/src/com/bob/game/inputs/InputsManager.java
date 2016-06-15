package com.bob.game.inputs;

import com.bob.game.levels.Level;

public class InputsManager {

    private Rule[] rules;
    private InputsLayer layer;

    public InputsManager() {
        this.rules = new Rule[8];

        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule();
        }
    }

    public void setLayer(InputsLayer layer) {
        this.layer = layer;
    }

    public void initView() {

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

    public void resetRules(Block[][] newRules) {
        resetRules();

        for (int i = 0; i < newRules.length && i < rules.length; i++) {
            rules[i].setRuleBlocks(newRules[i]);
        }
    }

    public void resetInputs() {
        layer.clearInputs();
    }

    public String getRulesString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            res.append(rules[i].getString());
        }

        return res.toString();
    }

    public void setupInputs(Level level) {
        resetInputs();

        for (Block b: level.getInputs()) {
            layer.createInput(b);
        }
    }

    public void toggleLights() {
        for (Rule rule : rules) {
            rule.toggleLights();
        }
    }

    public void setupRules(Level level) {

        resetRules(level.getRules());

        for (int i = 0; i < rules.length; i++) {
            rules[i].initView(layer);
            if (i < level.getNoRules()) {
                layer.initRuleTargets(rules[i]);
            } else {
                rules[i].lock();
            }
        }
    }
}
