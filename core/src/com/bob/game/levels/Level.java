package com.bob.game.levels;

import com.bob.game.inputs.Block;

public abstract class Level {

    private final String map;
    private final int coordX;
    private final int coordY;
    private int noRules = 8;
    private Block[] inputs = new Block[]{};
    private Block[][] rules = new Block[][]{};
    private String[] tutorialImages = new String[]{};
    private final String text;

    public Level(String map, int x, int y, Block[][] rules, String text, String[] tutorialImages) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.rules = rules;
        this.text = text;
        this.tutorialImages = tutorialImages;
    }

    public Level(String map, int x, int y, int noRules, Block[] inputs, String text, String[] tutorialImages) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
        this.inputs = inputs;
        this.text = text;
        this.tutorialImages = tutorialImages;
    }

    public Level(String map, int x, int y, String text, String[] tutorialImages) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.text = text;
        this.tutorialImages = tutorialImages;
    }

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

    public abstract Level next();

    public Block[][] getRules() {
        return rules;
    }

    public abstract void save();

    public String[] getTutorialImages() {
        return tutorialImages;
    }
}
