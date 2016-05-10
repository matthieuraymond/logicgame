package com.lps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture foreground;
	List<Entity> entities;
	MapManager mapManager;
	LPSHandler lpsHandler;
	float roundTime;


	@Override
	public void create() {
		batch = new SpriteBatch();

		foreground = new Texture("foreground.png");

		mapManager = new MapManager("maps/tmx/map1.tmx");

		entities = new ArrayList<Entity>();
		entities.add(new Entity(mapManager, 2, 0));

		lpsHandler = new LPSHandler("bob");
		roundTime = 0;
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

		float deltaTime = Gdx.graphics.getDeltaTime();
		roundTime += deltaTime;

        boolean endOfRound = roundTime >= Config.roundDuration;

		Gdx.gl.glClearColor(39, 156, 255, 1); //for water
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// QUIT
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if (Gdx.input.getX() >= 10 && Gdx.input.getX() <= 70 && Gdx.input.getY() >= 1010 && Gdx.input.getY() <= 1070) {
				Gdx.app.exit();
			}
		}

		// LPS UPDATE TEMPORARY
		Entity firstBob = entities.get(0);

		if (endOfRound) {
            lpsHandler.update();
			EntityState newState = lpsHandler.getNewState();

			if (newState != null) {
				firstBob.updateState(newState);
			} else {
				firstBob.makeIDLE();
			}
		}
		// -----------

        if (endOfRound) {
            for (Entity e: entities) {
                e.checkIfWet();
            }
        }

		mapManager.draw();

		batch.begin();

		for (Entity e: entities) {
			e.draw(batch, deltaTime);
		}

		batch.draw(foreground, 0, 0);
		batch.end();

		if (endOfRound) roundTime = 0;
	}

}
