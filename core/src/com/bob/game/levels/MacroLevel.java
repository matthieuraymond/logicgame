package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public enum MacroLevel implements LevelFactory {
    level1("maps/tmx/macro.tmx",
            6,11,
            "In this mode, I'll change my behavior.\nIndeed, it depends on how many light bulbs I have.\n\nCan you help me collect this light bulb?",
            new String[]{"screens/tut_macro.png"}
    ),
    level2("maps/tmx/macro2.tmx",
            6,11,
            "Okay, \nNow that you understood the concept, can you apply the same principles here?"
    );

    private final Level level;

    MacroLevel(String map, int x, int y, String text) {
       this(map,x,y,text,new String[]{});
    }

    MacroLevel(String map, int x, int y, String text, String[] tutorialImages) {

        final int ordinal = this.ordinal();

        this.level = new Level(map, x, y, text, tutorialImages) {

            @Override
            public Level next() {
                LevelFactory[] levels = MacroLevel.values();

                return levels[(ordinal + 1) % levels.length].getLevel();
            }

            @Override
            public Boolean allowMacro() {
                return true;
            }

            @Override
            public void save() {
                Preferences prefs = Gdx.app.getPreferences("Progress");
                prefs.putInteger("macroProgress", ordinal);
                prefs.flush();
            }
        };
    }

    public Level getLevel() {
        return level;
    }
}
