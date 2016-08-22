package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MacroLevel extends Level {

    public MacroLevel(String map, int x, int y, String text) {
       this(map,x,y,text,new String[]{});
    }

    public MacroLevel(String map, int x, int y, String text, String[] tutorialImages) {
        super();
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.text = text;
        this.tutorialImages = tutorialImages;
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
