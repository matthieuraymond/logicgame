package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class RuleCell {

    private Image containImage;
    private Block block;
    private DragAndDrop.Target target;
    private int targetX;
    private int targetY;

    public RuleCell (final Group group, int startingY, int index, final Skin skin) {

        Image bkgImage = new Image(skin, "target");

        targetX = 1475 + index * 60;
        targetY = startingY;
        bkgImage.setBounds(targetX, targetY, 50, 50);

        group.addActor(bkgImage);

        target = new DragAndDrop.Target(bkgImage) {
            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }

            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.CLEAR);
            }

            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                block = (Block)payload.getObject();
                updateDisplay(group, skin);
            }
        };

        target.getActor().setColor(Color.CLEAR);
    }

    public void updateDisplay(Group group, Skin skin) {

        if (containImage != null) {
            containImage.remove();
        }

        containImage = new Image(skin, block.getImageName());
        containImage.setBounds(targetX, targetY, 50, 50);

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

        for (DragAndDrop.Target t: InputsLayer.targets) {
            dragAndDrop.addTarget(t);
        }

        group.addActor(containImage);
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
}
