package com.lps.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum EntityState {
    IDLE_RIGHT (Textures.BOB_W_RIGHT, true, 0, 0),
    IDLE_DOWN (Textures.BOB_W_DOWN, true, 0, 0),
    IDLE_LEFT (Textures.BOB_W_LEFT, true, 0, 0),
    IDLE_UP (Textures.BOB_W_UP, true, 0, 0),
    WALK_RIGHT (Textures.BOB_W_RIGHT, false, 1, 0),
    WALK_DOWN (Textures.BOB_W_DOWN, false, 0, 1),
    WALK_LEFT (Textures.BOB_W_LEFT, false, -1, 0),
    WALK_UP (Textures.BOB_W_UP, false, 0, -1),
    WET (Textures.BOB_WET, false, 0, 0),
    DANCE (Textures.BOB_DANCE , false, 0, 0);

    private Animation animation;
    private float dx;
    private float dy;
    private boolean isIDLE;

    EntityState(Textures text, boolean isIDLE, float dx, float dy) {
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
}
