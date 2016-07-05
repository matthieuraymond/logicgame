package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

class RuleCell extends Target{

    public RuleCell() {

    }

    public String getLPSString() {
        return object != null ? ((Block)object).getLPSString() : "";
    }

    public Type getType() {
        return object != null ? ((Block)object).getType() : null;
    }

    public void setImage(boolean isDragable) {
        if (object != null) {
            containImage = new Image(skin, ((Block)object).getImageName());
            containImage.setBounds(targetX, targetY, 50, 50);
            layer.addActor(containImage);

            if (isDragable) {
                setMoveAbility();
            }
        }
    }

    public Block getBlock() {
        return ((Block)object);
    }
}
