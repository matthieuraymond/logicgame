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
            "Hi again!\nnMake sure you've played the write mode first!\n\nHere you'll need to guess where I'm going!\nClick on the question mark when you've guessed.",
            new String[]{"screens/tut_read.png"}
    ),
    level2("maps/tmx/guess.tmx",
            11,11,
            new Block[][]{
                    {Block.RED, Block.AND, Block.RED_PREV, Block.IMPLY, Block.DOWN},
                    {Block.RED, Block.AND, Block.YELLOW_PREV, Block.IMPLY, Block.LEFT},
                    {Block.YELLOW, Block.AND, Block.YELLOW_PREV, Block.IMPLY, Block.DOWN},
                    {Block.YELLOW, Block.AND, Block.RED_PREV, Block.IMPLY, Block.UP},
                    {Block.PURPLE, Block.AND, Block.YELLOW_PREV, Block.IMPLY, Block.RIGHT},
                    {Block.GREEN, Block.AND, Block.RED_PREV,Block.IMPLY, Block.LEFT},
                    {Block.PURPLE, Block.AND, Block.PURPLE_PREV, Block.IMPLY, Block.RIGHT},
                    {Block.GREEN, Block.AND, Block.GREEN_PREV,Block.IMPLY, Block.LEFT},
            },
            "The previous one was too easy...\n\nSame layout, different rules, can you guess it right?"
    ),
    level3("maps/tmx/guess2.tmx",
            11,11,
            new Block[][]{
                    {Block.WHITE, Block.AND, Block.WHITE_PREV, null, Block.IMPLY, Block.RIGHT},
                    {Block.WHITE, Block.AND, Block.RED_PREV, null, Block.IMPLY, Block.LEFT},
                    {Block.WHITE, Block.AND, Block.GREEN_PREV, null, Block.IMPLY, Block.UP},
                    {Block.GREEN, Block.AND, Block.NOT, Block.WHITE_PREV, Block.IMPLY, Block.DOWN},
                    {Block.GREEN, Block.AND, Block.WHITE_PREV, null, Block.IMPLY, Block.RIGHT},
                    {Block.RED, Block.AND, Block.NOT, Block.WHITE_PREV, Block.IMPLY, Block.UP},
                    {Block.RED, Block.AND, Block.WHITE_PREV, null, Block.IMPLY, Block.LEFT},
            },
            "You will not get that one,\nI am sure that it is too complicated!"
    );

    private final Level level;

    ReadLevel(String map, int x, int y, Block[][] rules, String text) {
        this(map, x, y, rules, text, new String[]{});
    }

    ReadLevel(String map, int x, int y, Block[][] rules, String text, String[] tutorialImages) {

        final int ordinal = this.ordinal();

        this.level = new Level(map, x, y, rules, text, tutorialImages) {

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
