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

    public void draw(Batch batch, float deltaTime) {


        stateTime += deltaTime;


        this.coord.increaseX(currentState.getDx() * deltaTime/ Config.roundDuration);
        this.coord.increaseY(currentState.getDy() * deltaTime/ Config.roundDuration);

        if (stateTime >= Config.roundDuration) {
            //round coordinates as stateTime might be !=
            coord.round();
        }

        TextureRegion currentFrame = currentState.getFrame(stateTime);
        batch.draw(currentFrame, this.coord.getScreenX() - currentFrame.getRegionWidth()/2, this.coord.getScreenY());
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
        if (map.isWater((int)coord.getWorldX(), (int)coord.getWorldY())) {
            this.isAlive = false;
            currentState = EntityState.WET;
            return true;
        }
        return false;
    }

    ;
}
