package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.LinkedList;
import java.util.List;

public class InputsManager {

    private Rule[] rules;
    private InputsLayer layer;

    public InputsManager() {
        this.rules = new Rule[8];

        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule();
        }
    }

    public void initRuleView(Skin skin, int startingX, int startingY) {

        for (Rule rule : rules) {
            rule.initView(layer, skin, startingX, startingY);
            startingY -= 70;
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
            String ruleString = rules[i].getString(i);
            if (!ruleString.equals("")) {
                res.append(ruleString);
            }
        }

        return res.toString();
    }

    public void setupInputs(Block[] blocks, int refX, int refY) {
        resetInputs();

        BlockCoordinatesGenerator bcg = new BlockCoordinatesGenerator(refX, refY);

        for (Block b: blocks) {
            int[] coord = bcg.getCoordinates(b.getType());
            layer.createInput(b, coord[0], coord[1]);
        }
    }

    public void toggleLights() {
        for (Rule rule : rules) {
            rule.toggleLights();
        }
    }

    public void setupRules(int noRules, Block[][] newRules, boolean draggable) {
        resetRules(newRules);
        setupRuleView(noRules, draggable);
    }

    public void setupRules(int noRules, Rule[] newRules) {
        this.rules = newRules;
        setupRuleView(noRules, false);
    }

    private void setupRuleView(int noRules, boolean draggable) {
        for (int i = 0; i < rules.length; i++) {
            if (i < noRules) {
                layer.addTargets(rules[i].getTargets());
                rules[i].displayImages(draggable);
                rules[i].lock(false);
            } else {
                rules[i].lock(true);
            }
        }
    }

    public void lightOnRule(List<Integer> ruleIndexes) {
        for(Integer ruleIndex: ruleIndexes) {
            if (ruleIndex >= 0 && ruleIndex < rules.length) {
                rules[ruleIndex].lightOn();
            }
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

    public Block[][] getRules() {
        Block[][] res = new Block[rules.length][];

        for (int i = 0; i < res.length; i++) {
            List<Block> blocks = rules[i].getBlockStack();
            res[i] = blocks.toArray(new Block[blocks.size()]);
        }

        return res;
    }

    public void lightOffRules() {
        for (Rule r: rules) {
            r.lightOff();
        }
    }
}
