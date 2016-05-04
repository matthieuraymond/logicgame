package com.lps.game;

import java.util.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture foreground;
	List<Entity> entities = new ArrayList<Entity>();
	MapManager mapManager;


	@Override
	public void create() {
		batch = new SpriteBatch();

		foreground = new Texture("foreground.png");

		mapManager = new MapManager("maps/tmx/map1.tmx");
		entities.add(new Entity(mapManager, 1, 0));

	}

	@Override
	public void dispose() {
		batch.dispose();

		for (Textures t: Textures.values()) {
			t.dispose();
		}

		foreground.dispose();
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// QUIT
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if (Gdx.input.getX() >= 10 && Gdx.input.getX() <= 70 && Gdx.input.getY() >= 1010 && Gdx.input.getY() <= 1070) {
				Gdx.app.exit();
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			entities.add(new Entity(mapManager, 1, 0));
		}

		mapManager.draw();

		batch.begin();

		for (Entity e: entities) {
			e.draw(batch);
		}

		batch.draw(foreground, 0, 0);
		batch.end();
	}

}
