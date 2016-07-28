package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bob.game.inputs.Block;

public enum ReadLevel implements LevelFactory {

    level1("maps/tmx/guess.tmx",
            11,11,
            new Block[][]{
                    {Block.RED, Block.IMPLY, Block.DOWN},
                    {Block.PURPLE, Block.IMPLY, Block.RIGHT},
                    {Block.YELLOW, Block.IMPLY, Block.DOWN},
                    {Block.GREEN, Block.IMPLY, Block.LEFT}
            },
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"
    );

    private final Level level;

    ReadLevel(String map, int x, int y, Block[][] rules, String text) {

        final int ordinal = this.ordinal();

        this.level = new Level(map, x, y, rules, text) {

            @Override
            public Level next() {
                LevelFactory[] levels = ReadLevel.values();

                return levels[(ordinal + 1) % levels.length].getLevel();
            }

            @Override
            public void save() {
                Preferences prefs = Gdx.app.getPreferences("Progress");
                prefs.putInteger("readProgress", ordinal);
                prefs.flush();
            }

            @Override
            public Boolean allowRuleReset() {
                return false;
            }
        };
    }

    public Level getLevel() {
        return level;
    }
}
