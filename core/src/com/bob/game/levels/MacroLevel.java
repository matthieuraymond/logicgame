package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.XmlReader;

public class MacroLevel extends Level {

    public MacroLevel(XmlReader.Element root) {
        super(root);
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
