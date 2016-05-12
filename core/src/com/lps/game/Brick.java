package com.lps.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class Brick {
    private String LPSString;
    private static int currentX = 1415;
    private static int currentY = 1080 - 165;

    public Brick(Stage stage, final Skin skin, String LPSString, final String image) {
        final Image sourceImage = new Image(skin, image);
        final Image dragImage = new Image(skin, image);
        sourceImage.setBounds(currentX, currentY, 50, 50);

        increaseCoords();

        stage.addActor(sourceImage);

        Image validTargetImage = new Image(skin, image);
        validTargetImage.setBounds(200, 50, 50, 50);
        stage.addActor(validTargetImage);

        DragAndDrop dragAndDrop = new DragAndDrop();
        dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(dragImage);

                payload.setDragActor(dragImage);

                Image validImage = new Image(skin, image);
                validImage.setColor(0, 1, 0, 1);
                payload.setValidDragActor(validImage);

                return payload;
            }
        });
        dragAndDrop.addTarget(new DragAndDrop.Target(validTargetImage) {
            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }

            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.WHITE);
            }

            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
            }
        });
    }

    private static void increaseCoords() {
        if (currentX < 1835) {
            currentX += 60;
        } else {
            currentX = 1415;
            currentY -= 60;
        }
    }
}
