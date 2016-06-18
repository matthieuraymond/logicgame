package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

class InputView {
    private static final int refXFluent = 1415;
    private static final int refXLogic = refXFluent + 270;
    private static final int refXConsequent = refXLogic + 90;
    private static final int refY = 1080 - 165;
    private static int noFluent = 0;
    private static int noLogic = 0;
    private static int noConsequent = 0;
    private final Image sourceImage;

    private final Block block;
    private final DragAndDrop dragAndDrop;

    public InputView(Group group, final Skin skin, final Block block) {

        this.block = block;

        sourceImage = new Image(skin, block.getImageName());
        final Image dragImage = new Image(skin, block.getImageName());
        TextTooltip tooltip = new TextTooltip("  " + block.getTooltip() + "  ", skin, "tooltipStyle");
        tooltip.setInstant(true);

        int[] coord = getCoordinates(block.getType());

        sourceImage.setBounds(coord[0], coord[1], 50, 50);
        sourceImage.addListener(tooltip);

        group.addActor(sourceImage);

        dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragActorPosition(-(sourceImage.getWidth()/2), sourceImage.getHeight()/2);
        dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(block);

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

    public void clear() {
        sourceImage.remove();
        if (block != null) {
            if (block.getType() == Type.FLUENT) {
                noFluent--;
            } else if (block.getType() == Type.CONSEQUENT) {
                noConsequent--;
            } else {
                noLogic--;
            }
        }
    }
}
