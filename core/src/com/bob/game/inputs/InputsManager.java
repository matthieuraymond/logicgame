package com.bob.game.inputs;

import com.bob.game.levels.Level;

import java.util.LinkedList;

public class InputsManager {

    private final Rule[] rules;
    private InputsLayer layer;

    public InputsManager() {
        this.rules = new Rule[8];

        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule();
        }
    }

    public void initRuleView() {
        for (Rule rule : rules) {
            rule.initView(layer);
        }
    }

    public void setLayer(InputsLayer layer) {
        this.layer = layer;
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

    private void resetInputs() {
        layer.clearInputs();
    }

    public String getRulesString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            String ruleString = rules[i].getString();
            if (!ruleString.equals("")) {
                res.append(ruleString);
                res.append("("+i+").");
            }
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
            if (i < level.getNoRules()) {
                layer.initRuleTargets(rules[i]);
                rules[i].displayImages();
                rules[i].lock(false);
            } else {
                rules[i].lock(true);
            }
        }
    }

    public void lightOnRule(int ruleIndex) {
        for (Rule r: rules) {
            r.lightOff();
        }
        if (ruleIndex >= 0 && ruleIndex < rules.length) {
            rules[ruleIndex].lightOn();
        }
    }

    public boolean onlyConsequentUsed() {
        boolean res = true;
        for(Rule r: rules) {
            res &= r.onlyConsequentUsed();
        }

        return res;
    }

    public LinkedList<Block> getBlockStack() {
        LinkedList<Block> blockStack = new LinkedList<>();

        for (Rule r: rules) {
            blockStack.addAll(r.getBlockStack());
        }

        return blockStack;
    }

    public void setupMacro() {
        layer.setUpMacroPanel();
    }

    public void deleteMacro() {
        layer.deleteMacroPanel();
    }
}
