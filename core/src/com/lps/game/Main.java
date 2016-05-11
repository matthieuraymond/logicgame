package com.lps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture foreground;
	Entity bob;
	MapManager mapManager;
	LPSHandler lpsHandler;
	float roundTime;
	GameState gameState = GameState.INPUT_HANDLING;


	@Override
	public void create() {
		batch = new SpriteBatch();

		foreground = new Texture("foreground.png");

		resetLevel();
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

		float deltaTime = (gameState == GameState.ANIM_PLAYING) ? Gdx.graphics.getDeltaTime() : 0;
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

		// SUBMIT
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if (Gdx.input.getX() >= 1630 && Gdx.input.getX() <= 1835 && Gdx.input.getY() >= 1010 && Gdx.input.getY() <= 1070) {
				resetLevel();
				gameState = GameState.ANIM_PLAYING;
			}
		}

		if (endOfRound) {
            lpsHandler.update();
			EntityState newState = lpsHandler.getNewState();

			if (newState != null) {
				bob.updateState(newState);
			} else {
				bob.makeIDLE();
			}
		}

		boolean isWet = bob.checkIfWet();

		mapManager.draw(deltaTime);

		batch.begin();

		bob.draw(batch, deltaTime);

		batch.draw(foreground, 0, 0);
		batch.end();

		if (isWet) gameState = GameState.INPUT_HANDLING;
		if (endOfRound) roundTime = 0;
	}

	private void resetLevel() {
		mapManager = new MapManager("maps/tmx/map1.tmx");
		bob = new Entity(mapManager, 2, 0);
		lpsHandler = new LPSHandler(mapManager);
		roundTime = 0;
	}

}
