package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public enum MacroLevel implements LevelFactory {
    level1("maps/tmx/macro.tmx",
            6,11,
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!",
            new String[]{"screens/tut_macro.png"}
    ),
    level2("maps/tmx/macro2.tmx",
            6,11,
            "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"
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
