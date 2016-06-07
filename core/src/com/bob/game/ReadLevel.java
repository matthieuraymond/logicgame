package com.bob.game;

import com.bob.game.inputs.Block;

public enum ReadLevel {

    level1("maps/tmx/straight.tmx",
            2,11,
            new Block[][]{{}},
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"
    ),
    level3("maps/tmx/turn.tmx",
            2,11,
            new Block[][]{{}},
            "WOW! That was amazing!\n\nHowever I still have a little problem:\n\tI don't know how to cross that one, can you help me again?\n\nThanks!"
    ),
    level4("maps/tmx/not.tmx",
            2,11,
            new Block[][]{{}},
            "Be careful, this one is tricky!\n\nLet's see if you can riddle me this."
    ),
    level5("maps/tmx/loop.tmx",
            7,11,
            new Block[][]{{}},
            "Be careful, this one is tricky!\n\nYou'll have to make good use of the \"previous\" block.\nThanks for your help."
    );

    private Level level;

    ReadLevel(String map, int x, int y, Block[][] rules, String text) {

        final int ordinal = this.ordinal();

        this.level = new Level(map, x, y, 0, new Block[]{}, rules, text) {

            @Override
            public Level next() {
                ReadLevel[] levels = ReadLevel.values();

                return levels[(ordinal + 1) % levels.length].getLevel();
            }
        };
    }

    public Level getLevel() {
        return level;
    }
}
