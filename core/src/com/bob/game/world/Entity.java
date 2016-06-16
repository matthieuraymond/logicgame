package com.bob.game.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Entity {

    private WorldCoordinates coord;
    private float stateTime;
    private EntityState currentState;
    private float movedX;
    private float movedY;
    private float roundTime;

    public Entity(float x, float y) {
        setPosition(x, y);
    }

    public void setPosition(float x, float y) {
        this.coord = new WorldCoordinates(x, y);
        this.movedX = 0;
        this.movedY = 0;
        this.stateTime = 0;
        this.currentState = EntityState.IDLE_RIGHT;
    }

    public WorldCoordinates getCoord() {
        return coord;
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
        roundTime += deltaTime;

        this.coord.increaseX(currentState.getDx() * deltaTime);
        this.coord.increaseY(currentState.getDy() * deltaTime);
    }

    public void updateState(EntityState newState) {
        stateTime = 0;
        if (newState != null) {
            this.currentState = newState;
        } else {
            makeIDLE();
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

    public void increaseTime(float deltaTime) {
        stateTime += deltaTime;
        updatePosition(deltaTime);
    }

    public boolean needInstructions() {

        // Movement induced update
        if (Math.abs(movedX) >= 1 || Math.abs(movedY) >= 1) {
            movedX = Math.signum(movedX) * (Math.abs(movedX) - (int)Math.abs(movedX));
            movedY = Math.signum(movedY) * (Math.abs(movedY) - (int)Math.abs(movedY));
            roundTime = 0;
            return true;
        }

        // Time induced update IF NOT CLOSE TO MOVEMENT INDUCED ONE
        if (roundTime >= 1 && Math.round(Math.abs(movedX)) != 1 && Math.round(Math.abs(movedY)) != 1) {
            roundTime = 0;
            return true;
        }

        return false;
    }

    public EntityState getState() {
        return currentState;
    }
}
