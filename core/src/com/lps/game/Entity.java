package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Entity {
    private WorldCoordinates coord;
    private float stateTime;
    private State currentState;

    public Entity(float x, float y) {
        this.coord = new WorldCoordinates(x, y);
        this.stateTime = 0f;
        currentState = State.IDLE_RIGHT;
    }

    public void draw(Batch batch) {

        float deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;

        if (!currentState.isIDLE()) {

            if (stateTime >= Utils.roundDuration) {

                //deltaTime -= (stateTime - Utils.roundDuration); // Correct if round duration is already gone
                stateTime = 0;

                if (currentState == State.WALK_RIGHT && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    currentState = State.IDLE_RIGHT;
                } else if (currentState == State.WALK_DOWN && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    currentState = State.IDLE_DOWN;
                } else if (currentState == State.WALK_LEFT && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    currentState = State.IDLE_LEFT;
                } else if (currentState == State.WALK_UP && !Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    currentState = State.IDLE_UP;
                }
            }

            this.coord.increaseX(currentState.getDx() * deltaTime/Utils.roundDuration);
            this.coord.increaseY(currentState.getDy() * deltaTime/Utils.roundDuration);

            System.out.println(coord.getWorldX());

        } else {
            stateTime = 0;

            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                currentState = State.WALK_DOWN;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                currentState = State.WALK_RIGHT;
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                currentState = State.WALK_UP;
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                currentState = State.WALK_LEFT;
            }
        }
        TextureRegion currentFrame = currentState.getAnimation().getKeyFrame(stateTime, true);
        batch.draw(currentFrame, this.coord.getScreenX() - currentFrame.getRegionWidth()/2, this.coord.getScreenY());
    }

    public enum State {
        IDLE_RIGHT (Textures.BOB_W_RIGHT, true, 0, 0),
        IDLE_DOWN (Textures.BOB_W_DOWN, true, 0, 0),
        IDLE_LEFT (Textures.BOB_W_LEFT, true, 0, 0),
        IDLE_UP (Textures.BOB_W_UP, true, 0, 0),
        WALK_RIGHT (Textures.BOB_W_RIGHT, false, 1, 0),
        WALK_DOWN (Textures.BOB_W_DOWN, false, 0, 1),
        WALK_LEFT (Textures.BOB_W_LEFT, false, -1, 0),
        WALK_UP (Textures.BOB_W_UP, false, 0, -1);

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

        public Animation getAnimation() {
            return this.animation;
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
