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
    protected String[] hints;
    protected String text;

    protected Level next;

    public Level(String map, int x, int y, String text, String[] hints, String[] tutorialImages) {
        this.inputs = new Block[]{};
        this.rules = new Block[][]{};
        this.tutorialImages = new String[]{};
        this.hints = new String[]{};
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.text = text;
        this.hints = hints;
        this.tutorialImages = tutorialImages;
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

    public Boolean hasHints() {
        return hints.length > 0;
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

    public String[] getHints() {
        return hints;
    }
}
