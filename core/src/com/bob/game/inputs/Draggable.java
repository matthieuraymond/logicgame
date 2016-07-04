package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.bob.game.Layer;

class Draggable {
    private final Actor sourceActor;
    private final DragAndDrop dragAndDrop;
    private final Object payLoad;

    public Draggable(Layer layer, Skin skin, Actor sourceActor, Object payloadObject) {
        this(layer, skin, sourceActor, sourceActor, payloadObject);
    }

    public Draggable(Layer layer, final Skin skin, Actor sourceActor, final Actor dragActor, final Object payloadObject) {

        this.sourceActor = sourceActor;
        this.payLoad = payloadObject;

        layer.addActor(sourceActor);

        dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragActorPosition(-(sourceActor.getWidth()/2), sourceActor.getHeight()/2);
        dragAndDrop.addSource(new DragAndDrop.Source(sourceActor) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(payloadObject);

                payload.setDragActor(dragActor);

                return payload;
            }
        });
    }

    public void addTarget(DragAndDrop.Target target) {
        dragAndDrop.addTarget(target);
    }

    public void clear() {
        sourceActor.remove();
    }

    public Actor getsourceActor() {
        return sourceActor;
    }

    public void setTooltip(Skin skin, String tooltipText) {
        TextTooltip tooltip = new TextTooltip("  " + tooltipText + "  ", skin, "tooltipStyle");
        tooltip.setInstant(true);
        sourceActor.addListener(tooltip);
    }

    public Object getPayLoad() {
        return payLoad;
    }
}
