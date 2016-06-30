package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.bob.game.Layer;

class RuleCell {

    private Image containImage;
    private Block block;
    private DragAndDrop.Target target;
    private int targetX;
    private int targetY;
    private InputsLayer layer;
    private Skin skin;

    public RuleCell() {

    }

    public void initView(Layer layer, int startingX, int startingY, final Skin skin) {

        this.layer = (InputsLayer)layer;
        this.skin = skin;

        Image bkgImage = new Image(skin, "target");

        targetX = startingX;
        targetY = startingY;

        bkgImage.setBounds(targetX, targetY, 50, 50);

        layer.addActor(bkgImage);

        target = new DragAndDrop.Target(bkgImage) {
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }

            public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.CLEAR);
            }

            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                setBlock((Block) payload.getObject());
                setImage(true);
            }
        };

        target.getActor().setColor(Color.CLEAR);
    }

    public String getLPSString() {
        return block != null ? block.getLPSString() : "";
    }

    public DragAndDrop.Target getTarget() {
        return target;
    }

    public Type getType() {
        return block != null ? block.getType() : null;
    }

    public void reset() {
        if (containImage != null) {
            containImage.remove();
        }
        block = null;
    }

    public void setBlock(Block block) {
        reset();
        this.block = block;
    }

    public void setImage(boolean isDragable) {
        if (block != null) {
            containImage = new Image(skin, block.getImageName());
            containImage.setBounds(targetX, targetY, 50, 50);
            layer.addActor(containImage);

            if (isDragable) {
                setMoveAbility();
            }
        }
    }

    private void setMoveAbility() {
        containImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( getTapCount() == 2) {
                    reset();
                }
            }
        });

        DragAndDrop dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragActorPosition(-(containImage.getWidth()/2), containImage.getHeight()/2);
        dragAndDrop.addSource(new DragAndDrop.Source(containImage) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(block);
                payload.setDragActor(containImage);
                block = null;

                return payload;
            }
        });

        for (DragAndDrop.Target t: layer.getTargets()) {
            dragAndDrop.addTarget(t);
        }
    }

    public Block getBlock() {
        return block;
    }
}
