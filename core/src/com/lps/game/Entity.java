package com.lps.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Entity {
    private WorldCoordinates coord;
    private float stateTime;
    private EntityState currentState;
    private boolean isAlive;
    private MapManager map;

    public Entity(MapManager map, float x, float y) {
        this.coord = new WorldCoordinates(x, y);
        this.stateTime = 0f;
        this.currentState = EntityState.IDLE_RIGHT;
        this.isAlive = true;
        this.map = map;
    }

    public void draw(Batch batch, float deltaTime, float roundDuration) {

        stateTime += deltaTime;

        this.coord.increaseX(currentState.getDx() * deltaTime / roundDuration);
        this.coord.increaseY(currentState.getDy() * deltaTime / roundDuration);

        if (stateTime >= roundDuration) {
            //round coordinates as stateTime might be !=
            coord.round();
        }

        TextureRegion currentFrame = currentState.getFrame(stateTime);
        float drawingX = this.coord.getScreenX() - currentFrame.getRegionWidth()/2;
        float drawingY = this.coord.getScreenY() + (currentState == EntityState.WON ? (float)Math.sin(3.1415f*stateTime/roundDuration) * 50: 0);

        batch.draw(currentFrame, drawingX, drawingY);
    }

    public void updateState(EntityState newState) {
        if (isAlive) {
            this.currentState = newState;
            stateTime = 0;
        }
    }

    public void makeIDLE() {
        if (isAlive) {
            stateTime = 0;
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
    }

    public boolean checkIfWet() {
        String type = map.getType((int)coord.getWorldX(), (int)coord.getWorldY());

        if (type.equals("water")) {
            this.isAlive = false;
            currentState = EntityState.WET;
            return true;
        }
        return false;
    }

    public boolean chekIfWon() {
        String type = map.getType((int)coord.getWorldX(), (int)coord.getWorldY());

        if (type.equals("gold")) {
            this.isAlive = true;
            currentState = EntityState.WON;
            return true;
        }

        return false;
    }
}
