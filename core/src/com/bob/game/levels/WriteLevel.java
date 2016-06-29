package com.bob.game.levels;

import com.bob.game.inputs.Block;

public enum WriteLevel implements LevelFactory {

    level1("resources/maps/tmx/short.tmx",
            9,11,
            8,
            new Block[]{Block.RIGHT},
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"
    ),
    level2("resources/maps/tmx/straight.tmx",
            5,11,
            1,
            new Block[]{Block.WHITE, Block.IMPLY, Block.RIGHT},
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"
    ),
    level3("resources/maps/tmx/turn.tmx",
            2,11,
            2,
            new Block[]{Block.WHITE, Block.RED, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
            "WOW! That was amazing!\n\nHowever I still have a little problem:\n\tI don't know how to cross that one, can you help me again?\n\nThanks!"
    ),
    level4("resources/maps/tmx/not.tmx",
            2,11,
            4,
            new Block[]{Block.WHITE, Block.RED, Block.GREEN, Block.ORANGE, Block.PURPLE, Block.YELLOW, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
            "Be careful, this one is tricky!\n\nLet's see if you can riddle me this."
    ),
    level5("resources/maps/tmx/loop.tmx",
            7,11,
            5,
            new Block[]{Block.WHITE, Block.RED, Block.GREEN, Block.YELLOW, Block.WHITE_PREV, Block.RED_PREV, Block.GREEN_PREV, Block.YELLOW_PREV, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
            "Be careful, this one is tricky!\n\nYou'll have to make good use of the \"previous\" block.\nThanks for your help."
    );

    private final Level level;

    WriteLevel(String map, int x, int y, int noRules, Block[] inputs, String text) {

        final int ordinal = this.ordinal();

        this.level = new Level(map, x, y, noRules, inputs, text) {

            @Override
            public Level next() {
                LevelFactory[] levels = WriteLevel.values();

                return levels[(ordinal + 1) % levels.length].getLevel();
            }
        };
    }

    public Level getLevel() {
        return level;
    }
}
