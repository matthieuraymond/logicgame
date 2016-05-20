package com.lps.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class Brick {
    private static final int refXFluent = 1415;
    private static final int refXLogic = refXFluent + 270;
    private static final int refXConsequent = refXLogic + 90;
    private static final int refY = 1080 - 165;
    private static int noFluent = 0;
    private static int noLogic = 0;
    private static int noConsequent = 0;

    private LogicBrick logicBrick;
    private DragAndDrop dragAndDrop;

    public Brick(Group group, final Skin skin, String LPSString, String image, Type type, String tooltipText) {

        logicBrick = new LogicBrick(LPSString, image, type);

        final Image sourceImage = new Image(skin, image);
        final Image dragImage = new Image(skin, image);
        TextTooltip tooltip = new TextTooltip(tooltipText, skin, "tooltipStyle");
        tooltip.setInstant(true);

        int[] coord = getCoordinates(type);

        sourceImage.setBounds(coord[0], coord[1], 50, 50);
        sourceImage.addListener(tooltip);

        group.addActor(sourceImage);


        dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragActorPosition(-(sourceImage.getWidth()/2), sourceImage.getHeight()/2);
        dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(logicBrick);

                payload.setDragActor(dragImage);

                return payload;
            }
        });
    }

    private int[] getCoordinates(Type type) {
        int[] res = new int[2];
        if (type == Type.FLUENT) {
            res[0] = refXFluent + (noFluent % 4) * 60;
            res[1] = refY - (noFluent / 4) * 60;

            noFluent++;
        } else if (type == Type.CONSEQUENT) {
            res[0] = refXConsequent + (noConsequent % 2) * 60;
            res[1] = refY - (noConsequent / 2) * 60;

            noConsequent++;
        } else {
            res[0] = refXLogic;
            res[1] = refY - noLogic * 60;

            noLogic++;
        }

        return res;
    }

    public void addTarget(DragAndDrop.Target target) {
        dragAndDrop.addTarget(target);
    }
}
