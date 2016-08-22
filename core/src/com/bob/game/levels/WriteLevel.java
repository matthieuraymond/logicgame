package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bob.game.inputs.Block;

public class WriteLevel extends Level {

    public WriteLevel(String map, int x, int y, int noRules, Block[] inputs, String text) {
        this(map, x, y, noRules, inputs, text, new String[]{});
    }

    public WriteLevel(String map, int x, int y, int noRules, Block[] inputs, String text, String[] tutorialImages) {
        super();
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
        this.inputs = inputs;
        this.text = text;
        this.tutorialImages = tutorialImages;
    }

    @Override
    public void save() {
        Preferences prefs = Gdx.app.getPreferences("Progress");
        prefs.putInteger("writeProgress", LevelFactory.WRITE.indexOf(this));
        prefs.flush();
    }
}
