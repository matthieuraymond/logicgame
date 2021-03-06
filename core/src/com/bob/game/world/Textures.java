package com.bob.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public enum Textures {
    BOB_W_RIGHT ("bob/bob_walk_right", 1/24f),
    BOB_W_DOWN ("bob/bob_walk_down", 1/24f),
    BOB_W_LEFT ("bob/bob_walk_left", 1/24f),
    BOB_W_UP ("bob/bob_walk_up", 1/24f),
    BOB_WET ("bob/bob_wet", 1/24f),
    BOB_WON ("bob/bob_won", 1/24f),
    LIGHT("bob/light_bulb", 1/24f),
    BOB_CONFUSED("bob/confused", 1/10f);

    private final TextureAtlas texture;
    private final float speed;

    Textures(String path, float speed) {
        this.texture = new TextureAtlas(Gdx.files.internal(path + ".atlas"));
        this.speed = speed;
    }

    public void dispose() {
        this.texture.dispose();
    }

    public TextureAtlas getTexture() {
        return this.texture;
    }

    public float getSpeed() {
        return this.speed;
    }
}
