package com.bob.game.levels;

import com.bob.game.inputs.Block;

public abstract class Level {

    protected String map;
    protected int coordX;
    protected int coordY;
    protected int noRules = 8;
    protected Block[] inputs;
    protected Block[][] rules;
    protected String[] tutorialImages;
    protected String text;

    protected Level next;

    public Level() {
        inputs = new Block[]{};
        rules = new Block[][]{};
        tutorialImages = new String[]{};
    }

    public abstract void save();

    public Level getNext() {
        return next;
    };

    public Boolean allowMacro() {
        return false;
    }

    public Boolean allowRuleReset() {
        return true;
    }

    public Boolean hasTutorial() {
        return tutorialImages.length > 0;
    }

    public Block[] getInputs() {
        return inputs;
    }

    public int getNoRules() {
        return noRules;
    }

    public String getMap() {
        return map;
    }

    public int getX() {
        return coordX;
    }

    public int getY() {
        return coordY;
    }

    public String getText() {
        return text;
    }

    public Block[][] getRules() {
        return rules;
    }

    public String[] getTutorialImages() {
        return tutorialImages;
    }

    public void setNext(Level next) {
        this.next = next;
    }
}
