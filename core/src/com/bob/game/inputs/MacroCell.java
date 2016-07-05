package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.bob.game.Layer;

public class MacroCell extends Target {

    public MacroCell(Layer layer, int startingX, int startingY, Image bkgImage, Skin skin) {
        initView(layer, startingX, startingY, bkgImage, skin);
    }

    @Override
    public void setImage(boolean isDragable) {
        if (payload != null) {
            Macro m = (Macro) payload;

            actor = new Label(m.getTitle(), skin, "macro_style");
            actor.setBounds(targetX, targetY, 230, 50);
            ((Label)actor).setEllipsis(true);
            ((Label)actor).setAlignment(Align.center);

            layer.addActor(actor);

            if (isDragable) {
                setMoveAbility();
            }
        }
    }
}
