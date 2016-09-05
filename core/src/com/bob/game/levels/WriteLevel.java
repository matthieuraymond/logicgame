package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bob.game.inputs.Block;

public class WriteLevel extends Level {

    public WriteLevel(String map, int x, int y, int noRules, Block[] inputs, String text) {
        this(map, x, y, noRules, inputs, text, new String[]{}, new String[]{});
    }

    public WriteLevel(String map, int x, int y, int noRules, Block[] inputs, String text, String[] hint) {
        this(map, x, y, noRules, inputs, text, hint, new String[]{});
    }

    public WriteLevel(String map, int x, int y, int noRules, Block[] inputs, String text, String[] hint, String[] tutorialImages) {
        super(map, x, y, text, hint, tutorialImages);
        this.noRules = noRules;
        this.inputs = inputs;
    }

    @Override
    public void save() {
        Preferences prefs = Gdx.app.getPreferences("Progress");
        prefs.putInteger("writeProgress", LevelFactory.WRITE.indexOf(this));
        prefs.flush();
    }
}
