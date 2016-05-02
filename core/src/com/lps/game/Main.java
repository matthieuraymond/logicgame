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
	private TiledMap map;
	private IsometricTiledMapRenderer renderer;
	private OrthographicCamera camera;

	@Override
	public void create() {
		batch = new SpriteBatch();
		entities.add(new Entity(40, Gdx.graphics.getHeight() - 60));

		foreground = new Texture("foreground.png");

		map = new TmxMapLoader().load("maps/tmx/map1.tmx");
		renderer = new IsometricTiledMapRenderer(map);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(128 * 14f - 10, 107, 0);
	}

	@Override
	public void dispose() {
		batch.dispose();

		for (Textures t: Textures.values()) {
			t.dispose();
		}

	}

	@Override
	public void render() {
		//elapsedTime += Gdx.graphics.getDeltaTime();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		renderer.setView(camera);
		renderer.render();

		batch.begin();

		for (Entity e: entities) {
			e.draw(batch);
		}
		batch.draw(foreground, 0, 0);
		batch.end();
	}

}
