package com.lps.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class Brick {
    private static int currentX = 1415;
    private static int currentY = 1080 - 165;

    private LogicBrick logicBrick;
    private DragAndDrop dragAndDrop;

    public Brick(Stage stage, final Skin skin, String LPSString, String image) {

        logicBrick = new LogicBrick(LPSString, image);

        final Image sourceImage = new Image(skin, image);
        final Image dragImage = new Image(skin, image);

        sourceImage.setBounds(currentX, currentY, 50, 50);

        increaseCoords();

        stage.addActor(sourceImage);

        dragAndDrop = new DragAndDrop();
        dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(logicBrick);

                payload.setDragActor(dragImage);

                return payload;
            }
        });
    }

    public void addTarget(DragAndDrop.Target target) {
        dragAndDrop.addTarget(target);
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
