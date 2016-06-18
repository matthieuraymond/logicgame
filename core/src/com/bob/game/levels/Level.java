package com.bob.game.levels;

import com.bob.game.inputs.Block;

public abstract class Level {

    private final String map;
    private final int coordX;
    private final int coordY;
    private final int noRules;
    private final Block[] inputs;
    private final Block[][] rules;
    private final String text;

    public Level(String map, int x, int y, int noRules, Block[] inputs, Block[][] rules, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
        this.inputs = inputs;
        this.rules = rules;
        this.text = text;
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
