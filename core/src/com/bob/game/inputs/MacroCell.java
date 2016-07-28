package com.bob.game.inputs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.bob.game.Layer;

public class MacroCell extends Target {

    public MacroCell() {

    }

    public MacroCell(Layer layer, int startingX, int startingY, Image bkgImage, Skin skin) {
        initView(layer, startingX, startingY, bkgImage, skin);
    }

    @Override
    public void setImage(boolean isDragable) {
        if (payload != null) {
            final Macro m = (Macro)payload;

            actor = new Label(m.getTitle(), skin, "macro_style") {
                @Override
                public void draw(Batch batch, float parentAlpha) {
                    this.setText(m.getTitle());
                    super.draw(batch, parentAlpha);
                }
            };
            actor.setBounds(targetX, targetY, 230, 50);
            ((Label)actor).setEllipsis(true);
            ((Label)actor).setAlignment(Align.center);

            layer.addActor(actor);

            if (isDragable) {
                setMoveAbility();
            }
        }
    }

    public String getLPSString(int macroIndex) {
        if (payload != null) {
            return ((Macro)payload).getLPSString(macroIndex);
        } else {
            return "";
        }
    }

    public void clear() {
        if (actor != null) {
            actor.setVisible(false);
            actor = null;
        }
        payload = null;
    }
}
