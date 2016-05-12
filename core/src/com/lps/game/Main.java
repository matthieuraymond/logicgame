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
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import java.util.ArrayList;
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
	Rule[] rules;
	ArrayList<DragAndDrop.Target> targets;


	@Override
	public void create() {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		foreground = new Texture("foreground.png");

		inputs = new HashMap<>();
		targets = new ArrayList<>(80);

		// DRAG N DROP STUFF
		skin = new Skin();
		skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("target", new Texture("inputs/target.png"));
		skin.add("imply", new Texture("inputs/imply.png"));

		String[] colors = {"red", "orange", "yellow", "green", "purple", "white"};
		String[] directions = {"Left", "Right", "Up", "Down"};

		for (String color : colors) {
			skin.add(color, new Texture("inputs/" + color + ".png"));
		}
		for (String direction : directions) {
			skin.add(direction, new Texture("inputs/" + direction.toLowerCase() + ".png"));
		}


		rules = new Rule[10];
		for (int i = 0; i < 10; i++) {
			rules[i] = new Rule(stage, skin);
			DragAndDrop.Target[] targets = rules[i].getTargets();
			for (DragAndDrop.Target t: targets) {
				this.targets.add(t);
			}
		}

		for (String color : colors) {
			addInput("isIn(X, Y) & " + color + "(X,Y)", color);
		}

		for (String direction : directions) {
			addInput("go" + direction + "()", direction);
		}

		addInput("->", "imply");

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

		StringBuilder inputs = new StringBuilder();
		for (Rule r: rules) {
			inputs.append(r.getString());
		}

		lpsHandler = new LPSHandler(mapManager, inputs.toString());
		roundTime = 0;
	}

	private void addInput(String lps, String name) {
		Brick brick = new Brick(stage, skin, lps, name);
		for (DragAndDrop.Target t: this.targets) {
			brick.addTarget(t);
		}
		inputs.put(name, brick);
	}

}
