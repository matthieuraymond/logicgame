package com.lps.game;

public class WorldCoordinates {
    private float x;
    private float y;

    public WorldCoordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getWorldX() {
        return this.x;
    }

    public float getWorldY() {
        return this.y;
    }

    public float getScreenX() {
        return 10 + (this.x - this.y) * Utils.tileWidth / 2;
    }

    public float getScreenY() {
        return 848 - (this.x + this.y) * Utils.tileHeight/2;
    }

    public void increaseX(float dx) {
        this.x += dx;
    }

    public void increaseY(float dy) {
        this.y += dy;
    }
}
