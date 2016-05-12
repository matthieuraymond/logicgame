package com.lps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;

public class Main extends ApplicationAdapter {
	Stage stage;
	SpriteBatch batch;
	Texture foreground;
	Entity bob;
	HashMap<String, Brick> inputs;
	MapManager mapManager;
	LPSHandler lpsHandler;
	float roundTime;
	GameState gameState;
	Skin skin;


	@Override
	public void create() {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		foreground = new Texture("foreground.png");

		inputs = new HashMap<>();

		// DRAG N DROP STUFF
		skin = new Skin();
		skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

		String[] colors = {"red", "orange", "yellow", "green", "purple"};


		for (String color : colors) {
			skin.add(color, new Texture("inputs/"+color+".png"));
			inputs.put(color, new Brick(stage, skin, "isIn(X, Y) & "+color+"(X,Y)", color));
		}

		resetLevel();
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();

		for (Textures t: Textures.values()) {
			t.dispose();
		}

		foreground.dispose();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
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

		// UPDATE OF ENTITY
		boolean isWet = bob.checkIfWet();

		if (endOfRound) {
            lpsHandler.update();
			EntityState newState = lpsHandler.getNewState();

			if (newState != null) {
				bob.updateState(newState);
			} else {
				bob.makeIDLE();
			}
		}

		//Map
		mapManager.draw(deltaTime);

		// Batch
		batch.begin();
		bob.draw(batch, deltaTime);
		batch.draw(foreground, 0, 0);
		batch.end();

		// Stage
		stage.act(deltaTime);
		stage.draw();

		if (isWet) gameState = GameState.INPUT_HANDLING;
		if (endOfRound) roundTime = 0;
	}

	private void resetLevel() {
		gameState = GameState.INPUT_HANDLING;
		mapManager = new MapManager("maps/tmx/map2.tmx");
		bob = new Entity(mapManager, 2, 0);
		lpsHandler = new LPSHandler(mapManager);
		roundTime = 0;
	}

}
