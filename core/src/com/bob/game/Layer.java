package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Layer {
    protected final Group group = new Group();

    public void setVisibility(boolean visible) {
        group.setVisible(visible);
    }

    public void setStage(Stage stage) {
        stage.addActor(group);
    }
}
