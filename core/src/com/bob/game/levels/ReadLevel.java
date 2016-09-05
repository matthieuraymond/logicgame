package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bob.game.inputs.Block;

public class ReadLevel extends Level {

    public ReadLevel(String map, int x, int y, Block[][] rules, String text) {
        this(map, x, y, rules, text, new String[]{}, new String[]{});
    }

    public ReadLevel(String map, int x, int y, Block[][] rules, String text, String[] hints) {
        this(map, x, y, rules, text, hints, new String[]{});
    }

    public ReadLevel(String map, int x, int y, Block[][] rules, String text, String[] hints, String[] tutorialImages) {
        super(map, x, y, text, hints, tutorialImages);
        this.rules = rules;
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
