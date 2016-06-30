package com.bob.game.levels;

import com.bob.game.inputs.Block;

public abstract class Level {

    private final String map;
    private final int coordX;
    private final int coordY;
    private int noRules = 8;
    private Block[] inputs = new Block[]{};
    private Block[][] rules = new Block[][]{};
    private final String text;

    public Level(String map, int x, int y, Block[][] rules, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.rules = rules;
        this.text = text;
    }

    public Level(String map, int x, int y, int noRules, Block[] inputs, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
        this.inputs = inputs;
        this.text = text;
    }

    public Level(String map, int x, int y, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.text = text;
    }

    public Boolean allowMacro() {
        return false;
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
}
