package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bob.game.inputs.Block;

public class ReadLevel extends Level {

    public ReadLevel(String map, int x, int y, Block[][] rules, String text) {
        this(map, x, y, rules, text, new String[]{});
    }

    public ReadLevel(String map, int x, int y, Block[][] rules, String text, String[] tutorialImages) {
        super();
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.rules = rules;
        this.text = text;
        this.tutorialImages = tutorialImages;
    }

    @Override
    public void save() {
        Preferences prefs = Gdx.app.getPreferences("Progress");
        prefs.putInteger("readProgress", LevelFactory.READ.indexOf(this));
        prefs.flush();
    }

    @Override
    public Boolean allowRuleReset() {
        return false;
    }
}
