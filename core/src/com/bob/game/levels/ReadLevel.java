package com.bob.game.levels;

import com.bob.game.inputs.Block;

public enum ReadLevel {

    level1("maps/tmx/guess.tmx",
            2,11,
            new Block[][]{{Block.WHITE, Block.IMPLY, Block.RIGHT}},
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"
    );

    private Level level;

    ReadLevel(String map, int x, int y, Block[][] rules, String text) {

        final int ordinal = this.ordinal();

        this.level = new Level(map, x, y, 8, new Block[]{}, rules, text) {

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
