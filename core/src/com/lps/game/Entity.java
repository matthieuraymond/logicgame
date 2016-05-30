package com.lps.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Entity {
    private WorldCoordinates coord;
    private float stateTime;
    private float globalTime;
    private EntityState currentState;
    private boolean isAlive;
    private MapManager map;
    private float movedX;
    private float movedY;
    private boolean firstUpdateRequired;

    public Entity(MapManager map, float x, float y) {
        this.coord = new WorldCoordinates(x, y);
        this.movedX = 0;
        this.movedY = 0;
        this.stateTime = 0;
        this.firstUpdateRequired = true;
        this.currentState = EntityState.IDLE_RIGHT;
        this.isAlive = true;
        this.map = map;
    }

    public void draw(Batch batch) {

        TextureRegion currentFrame = currentState.getFrame(stateTime);

        float drawingX = this.coord.getScreenX() - currentFrame.getRegionWidth()/2;
        float drawingY = this.coord.getScreenY() + (currentState == EntityState.WON ? (float)Math.abs(Math.sin(3.1415f*stateTime)) * 50: 0);

        batch.draw(currentFrame, drawingX, drawingY);
    }

    public void updatePosition(float deltaTime) {
        movedX += currentState.getDx() * deltaTime;
        movedY += currentState.getDy() * deltaTime;

        this.coord.increaseX(currentState.getDx() * deltaTime);
        this.coord.increaseY(currentState.getDy() * deltaTime);
    }

    public void updateState(EntityState newState) {
        if (isAlive) {
            stateTime = 0;
            if (newState != null) {
                this.currentState = newState;
            } else {
                makeIDLE();
            }
        }
    }

    public void makeIDLE() {
        switch (currentState) {
            case WALK_RIGHT:
                currentState = EntityState.IDLE_RIGHT;
                break;
            case WALK_LEFT:
                currentState = EntityState.IDLE_LEFT;
                break;
            case WALK_UP:
                currentState = EntityState.IDLE_UP;
                break;
            case WALK_DOWN:
                currentState = EntityState.IDLE_DOWN;
                break;
        }
    }

    public boolean checkIfWet() {
        String type = map.getType(Math.round(coord.getWorldX()), Math.round(coord.getWorldY()));

        if (type.equals("water")) {
            this.isAlive = false;
            currentState = EntityState.WET;
            return true;
        }
        return false;
    }

    public boolean chekIfWon() {
        String type = map.getType(Math.round(coord.getWorldX()), Math.round(coord.getWorldY()));

        if (type.equals("gold")) {
            this.isAlive = true;
            currentState = EntityState.WON;
            return true;
        }

        return false;
    }

    public void increaseTime(float deltaTime) {
        stateTime += deltaTime;
        updatePosition(deltaTime);
    }

    public boolean needInstructions() {

        if (Math.abs(movedX) >= 1 || Math.abs(movedY) >= 1) {
            movedX = Math.signum(movedX) * (Math.abs(movedX) - (int)Math.abs(movedX));
            movedY = Math.signum(movedY) * (Math.abs(movedY) - (int)Math.abs(movedY));

            return true;
        }

        if (firstUpdateRequired) {
            firstUpdateRequired = false;
            return true;
        }

        return false;
    }
}
