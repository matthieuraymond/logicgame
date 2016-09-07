package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.XmlReader;
import com.bob.game.inputs.Block;

public class WriteLevel extends Level {

    public WriteLevel(XmlReader.Element root) {
        super(root);

        this.noRules = root.getChildByName("rules").getIntAttribute("available");
        this.inputs = extractBlocks(root.getChildByName("inputs"));
    }

    @Override
    public void save() {
        Preferences prefs = Gdx.app.getPreferences("Progress");
        prefs.putInteger("writeProgress", LevelFactory.WRITE.indexOf(this));
        prefs.flush();
    }
}
