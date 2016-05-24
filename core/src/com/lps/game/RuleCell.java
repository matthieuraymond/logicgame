package com.lps.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class RuleCell {

    private Image bkgImage;
    private Image containImage;
    private String LPSString;
    private DragAndDrop.Target target;
    private Type type;
    private int targetX;
    private int targetY;

    public RuleCell (final Group group, int startingY, int index, final Skin skin) {

        bkgImage = new Image(skin, "target");
        LPSString = "";

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
                updateContainImage(group, skin, (LogicBrick)payload.getObject());
            }
        };

        target.getActor().setColor(Color.CLEAR);
    }

    public void updateContainImage(Group group, Skin skin, final LogicBrick logicBrick) {

        if (containImage != null) {
            containImage.remove();
        }

        //TODO refactor to keep logicBrick
        LPSString = logicBrick.getLPSString();
        containImage = new Image(skin, logicBrick.getImageName());
        containImage.setBounds(targetX, targetY, 50, 50);
        type = logicBrick.getType();

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
                payload.setObject(logicBrick);
                payload.setDragActor(containImage);

                LPSString = "";
                type = null;

                return payload;
            }
        });

        for (DragAndDrop.Target t: PlayingInterface.targets) {
            dragAndDrop.addTarget(t);
        }

        group.addActor(containImage);
    }

    public String getLPSString() {
        return LPSString;
    }

    public DragAndDrop.Target getTarget() {
        return target;
    }

    public Type getType() {
        return type;
    }

    public void reset() {
        if (containImage != null) {
            containImage.remove();
        }
        LPSString = "";
        type = null;
    }
}
