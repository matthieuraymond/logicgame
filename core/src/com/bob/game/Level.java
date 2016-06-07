package com.bob.game;

import com.bob.game.inputs.Block;

public enum Level {
    // map, startX, startY, noRules, inputs, rules, field)
    level1("maps/tmx/straight.tmx",
            2,11,
            1,
            new Block[]{Block.WHITE, Block.RED, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
            false,
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"
    ),
    level3("maps/tmx/turn.tmx",
            2,11,
            2,
            new Block[]{Block.WHITE, Block.RED, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
            false,
            "WOW! That was amazing!\n\nHowever I still have a little problem:\n\tI don't know how to cross that one, can you help me again?\n\nThanks!"
    ),
    level4("maps/tmx/not.tmx",
            2,11,
            4,
            new Block[]{Block.WHITE, Block.RED, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
            false,
            "Be careful, this one is tricky!\n\nLet's see if you can riddle me this."),
    level5("maps/tmx/loop.tmx",
            7,11,
            5,
            new Block[]{Block.WHITE, Block.RED, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
            true,
            "Be careful, this one is tricky!\n\nYou'll have to make good use of the \"previous\" block.\nThanks for your help."
    );

    private String map;
    private int coordX;
    private int coordY;
    private int noRules;
    private Block[] inputs;
    private boolean prevAuthorised;
    private String text;

    Level(String map, int x, int y, int noRules, Block[] inputs, boolean prevAuthorised, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
        this.inputs = inputs;
        this.prevAuthorised = prevAuthorised;
        this.text = text;
    }

    public Block[] getInputs() {
        return inputs;
    }

    public boolean isPrevAuthorised() {
        return prevAuthorised;
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

    public Level next() {
        Level[] levels = Level.values();

        return levels[(this.ordinal() + 1) % levels.length];
    }
}
