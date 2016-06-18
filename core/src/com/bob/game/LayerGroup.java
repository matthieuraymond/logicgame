package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.LinkedHashMap;

class LayerGroup {
    private final LinkedHashMap<String, Layer> layers = new LinkedHashMap<>();
    private boolean isVisible = false;

    public void add(String name, Layer layer) {
        layers.put(name, layer);
    }

    public void show() {
        for (Layer l: layers.values()) {
            l.setInitialVisibility();
        }
        isVisible = true;
    }

    public void setVisibility(String key, boolean visible) {
        layers.get(key).setVisibility(visible);
    }

    public void hide() {
        for (Layer l: layers.values()) {
            l.setVisibility(false);
        }
        isVisible = false;
    }

    public void setStage(Stage stage) {
        for (Layer l: layers.values()) {
            l.setStage(stage);
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Layer get(String key) {
        return layers.get(key);
    }
}
