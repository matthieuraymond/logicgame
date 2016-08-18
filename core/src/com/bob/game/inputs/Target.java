package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.bob.game.Layer;

public abstract class Target {

    protected Actor actor;
    protected Object payload;

    private DragAndDrop.Target target;
    protected int targetX;
    protected int targetY;
    protected InputsLayer layer;
    protected Skin skin;

    public void initView(Layer layer, int startingX, int startingY, Actor bkgImage, final Skin skin) {

        this.layer = (InputsLayer)layer;
        this.skin = skin;

        targetX = startingX;
        targetY = startingY;


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
                setPayload(payload.getObject());
                setImage(true);
            }
        };

        target.getActor().setColor(Color.CLEAR);
    }

    public DragAndDrop.Target getTarget() {
        return target;
    }

    public void reset() {
        if (actor != null) {
            actor.remove();
        }
        payload = null;
    }

    public void setPayload(Object payload) {
        reset();
        this.payload = payload;
    }

    protected void setMoveAbility() {
        actor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( getTapCount() == 2) {
                    reset();
                }
            }
        });

        DragAndDrop dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragActorPosition(-(actor.getWidth()/2), actor.getHeight()/2);
        dragAndDrop.addSource(new DragAndDrop.Source(actor) {
                public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setObject(Target.this.payload);
                    payload.setDragActor(actor);
                    Target.this.payload = null;
                    return payload;
                }
        });

        for (DragAndDrop.Target t: layer.getTargets()) {
            dragAndDrop.addTarget(t);
        }
    }

    public abstract void setImage(boolean isDragable);
}
