package com.lps.game;

import com.badlogic.gdx.graphics.Color;
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
    private int targetX;
    private int targetY;

    public RuleCell (final Stage stage, int startingY, int index, final Skin skin) {
        bkgImage = new Image(skin, "target");
        LPSString = "";

        targetX = 1415 + index * 60;
        targetY = startingY;
        bkgImage.setBounds(targetX, targetY, 50, 50);

        stage.addActor(bkgImage);

        target = new DragAndDrop.Target(bkgImage) {
            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }

            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.CLEAR);
            }

            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                updateContainImage(stage, skin, (LogicBrick)payload.getObject());
            }
        };

        target.getActor().setColor(Color.CLEAR);
    }

    public void updateContainImage(Stage stage, Skin skin, LogicBrick logicBrick) {

        if (containImage != null) {
            containImage.remove();
        }

        LPSString = logicBrick.getLPSString();
        containImage = new Image(skin, logicBrick.getImageName());
        containImage.setBounds(targetX, targetY, 50, 50);

        containImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( getTapCount() == 2) {
                    containImage.remove();
                    LPSString = "";
                }
            };
        });

        stage.addActor(containImage);
    }

    public String getLPSString() {
        return LPSString;
    }

    public DragAndDrop.Target getTarget() {
        return target;
    }
}
