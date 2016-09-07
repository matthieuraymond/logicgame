package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.XmlReader;
import com.bob.game.inputs.Block;

public class ReadLevel extends Level {

    public ReadLevel(XmlReader.Element root) {
        super(root);
        this.rules = extractRules(root.getChildByName("rules"));
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

    private Block[][] extractRules(XmlReader.Element rules) {
        int n = rules.getChildCount();
        Block[][] res = new Block[n][];

        for(int i = 0; i < n; i++) {
            res[i] = extractBlocks(rules.getChild(i));
        }

        return res;
    }
}
