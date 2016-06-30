package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

class InputView {
    private final Image sourceImage;

    private final Block block;
    private final DragAndDrop dragAndDrop;

    public InputView(Group group, final Skin skin, final Block block, int x, int y) {

        this.block = block;

        sourceImage = new Image(skin, block.getImageName());
        final Image dragImage = new Image(skin, block.getImageName());
        TextTooltip tooltip = new TextTooltip("  " + block.getTooltip() + "  ", skin, "tooltipStyle");
        tooltip.setInstant(true);

        sourceImage.setBounds(x, y, 50, 50);
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

    public void addTarget(DragAndDrop.Target target) {
        dragAndDrop.addTarget(target);
    }

    public void clear() {
        sourceImage.remove();
    }
}
