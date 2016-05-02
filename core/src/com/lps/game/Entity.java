package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class Entity {
    private float x;
    private float y;
    private float stateTime;
    private State currentState;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.stateTime = 0f;
        currentState = State.IDLE_RIGHT;
    }

    public void draw(Batch batch) {

        stateTime += Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (currentState != State.WALK_DOWN) {
                stateTime = 0f;
                currentState = State.WALK_DOWN;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (currentState != State.WALK_RIGHT) {
                stateTime = 0f;
                currentState = State.WALK_RIGHT;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (currentState != State.WALK_UP) {
                stateTime = 0f;
                currentState = State.WALK_UP;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (currentState != State.WALK_LEFT) {
                stateTime = 0f;
                currentState = State.WALK_LEFT;
            }
        } else {
            if (currentState == State.WALK_RIGHT) {
                stateTime = 0f;
                currentState = State.IDLE_RIGHT;
            } else if (currentState == State.WALK_DOWN) {
                stateTime = 0f;
                currentState = State.IDLE_DOWN;
            } else if (currentState == State.WALK_LEFT) {
                stateTime = 0f;
                currentState = State.IDLE_LEFT;
            } else if (currentState == State.WALK_UP) {
                stateTime = 0f;
                currentState = State.IDLE_UP;
            }
        }

        this.x += currentState.getDx();
        this.y += currentState.getDy();

        batch.draw(currentState.getAnimation().getKeyFrame(stateTime, true), this.x, this.y);
    }

    public enum State {
        IDLE_RIGHT (Textures.BOB_W_RIGHT, true, 0, 0),
        IDLE_DOWN (Textures.BOB_W_DOWN, true, 0, 0),
        IDLE_LEFT (Textures.BOB_W_LEFT, true, 0, 0),
        IDLE_UP (Textures.BOB_W_UP, true, 0, 0),
        WALK_RIGHT (Textures.BOB_W_RIGHT, false, 1, -0.5f),
        WALK_DOWN (Textures.BOB_W_DOWN, false, -1, -0.5f),
        WALK_LEFT (Textures.BOB_W_LEFT, false, -1, 0.5f),
        WALK_UP (Textures.BOB_W_UP, false, 1, 0.5f);

        private Animation animation;
        private float dx;
        private float dy;

        State(Textures text, boolean isIDLE, float dx, float dy) {
            this.animation = new Animation(text.getSpeed(), isIDLE ? text.getTexture().findRegions("0001") : text.getTexture().getRegions());
            this.dx = dx;
            this.dy = dy;
        }

        public Animation getAnimation() {
            return this.animation;
        }

        public float getDx() {
            return this.dx;
        }

        public float getDy() {
            return this.dy;
        }
    };
}
