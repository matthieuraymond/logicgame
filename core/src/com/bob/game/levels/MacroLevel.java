package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MacroLevel extends Level {

    public MacroLevel(String map, int x, int y, String text) {
       this(map,x,y,text,new String[]{}, new String[]{});
    }

    public MacroLevel(String map, int x, int y, String text, String[] hints) {
        this(map,x,y,text,hints, new String[]{});
    }
    public MacroLevel(String map, int x, int y, String text, String[] hints, String[] tutorialImages) {
        super(map, x, y, text, hints, tutorialImages);
    }

    @Override
    public Boolean allowMacro() {
        return true;
    }

    @Override
    public void save() {
        Preferences prefs = Gdx.app.getPreferences("Progress");
        prefs.putInteger("macroProgress", LevelFactory.MACRO.indexOf(this));
        prefs.flush();
    }
}
