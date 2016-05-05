package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

public class Entity {
    private WorldCoordinates coord;
    private float stateTime;
    private State currentState;
    private boolean isAlive;
    private MapManager map;

    public Entity(MapManager map, float x, float y) {
        this.coord = new WorldCoordinates(x, y);
        this.stateTime = 0f;
        this.currentState = State.IDLE_RIGHT;
        this.isAlive = true;
        this.map = map;
    }

    public void draw(Batch batch, float deltaTime) {


        stateTime += deltaTime;


        this.coord.increaseX(currentState.getDx() * deltaTime/Utils.roundDuration);
        this.coord.increaseY(currentState.getDy() * deltaTime/Utils.roundDuration);
        /*
        if (stateTime >= Utils.roundDuration) {
            //round coordinates as stateTime might be !=
            coord.round();
        }*/

        TextureRegion currentFrame = currentState.getFrame(stateTime);
        batch.draw(currentFrame, this.coord.getScreenX() - currentFrame.getRegionWidth()/2, this.coord.getScreenY());
    }

    public void updateState(State newState) {
        if (isAlive) {
            this.currentState = newState;
            stateTime = 0;
        }
    }

    public void checkIfDead() {
        int currentTile = map.getFloorType((int)coord.getWorldX(), (int)coord.getWorldY());
        if (currentTile == Utils.lavaID) {
            this.isAlive = false;
            currentState = State.DEAD;
        }
    }

    public enum State {
        IDLE_RIGHT (Textures.BOB_W_RIGHT, true, 0, 0),
        IDLE_DOWN (Textures.BOB_W_DOWN, true, 0, 0),
        IDLE_LEFT (Textures.BOB_W_LEFT, true, 0, 0),
        IDLE_UP (Textures.BOB_W_UP, true, 0, 0),
        WALK_RIGHT (Textures.BOB_W_RIGHT, false, 1, 0),
        WALK_DOWN (Textures.BOB_W_DOWN, false, 0, 1),
        WALK_LEFT (Textures.BOB_W_LEFT, false, -1, 0),
        WALK_UP (Textures.BOB_W_UP, false, 0, -1),
        DEAD (Textures.BOB_BURNING, false, 0, 0);

        private Animation animation;
        private float dx;
        private float dy;
        private boolean isIDLE;

        State(Textures text, boolean isIDLE, float dx, float dy) {
            this.animation = new Animation(text.getSpeed(), isIDLE ? text.getTexture().findRegions("0001") : text.getTexture().getRegions());
            this.dx = dx;
            this.dy = dy;
            this.isIDLE = isIDLE;
        }

        public TextureRegion getFrame(float stateTime) {
            return this.animation.getKeyFrame(stateTime, true);
        }

        public float getDx() {
            return this.dx;
        }

        public float getDy() {
            return this.dy;
        }

        public boolean isIDLE() {
            return this.isIDLE;
        }
    };
}
