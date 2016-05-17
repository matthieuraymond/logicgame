package com.lps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
	Button submitButton;


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
		skin.add("and", new Texture("inputs/and.png"));
		skin.add("not", new Texture("inputs/not.png"));

		String[] colors = {"red", "orange", "yellow", "green", "purple", "white"};
		String[] directions = {"Left", "Right", "Up", "Down"};

		for (String color : colors) {
			skin.add(color, new Texture("inputs/" + color + ".png"));
		}
		for (String direction : directions) {
			skin.add(direction, new Texture("inputs/" + direction.toLowerCase() + ".png"));
		}


		rules = new Rule[8];
		for (int i = 0; i < 8; i++) {
			rules[i] = new Rule(stage, skin);
			DragAndDrop.Target[] targets = rules[i].getTargets();
			for (DragAndDrop.Target t: targets) {
				this.targets.add(t);
			}
		}

		for (String color : colors) {
			addInput(color + "(X,Y)", Type.FLUENT, color);
		}

		for (String direction : directions) {
			addInput("go" + direction + "()", Type.CONSEQUENT, direction);
		}

		addInput("&", Type.AND,  "and");
		addInput("->", Type.IMPLY,  "imply");
		addInput("!", Type.NOT,  "not");

		// Buttons

		// QUIT BUTTON
		skin.add("quit", new Texture("buttons/quit.png"));
		skin.add("quit_clicked", new Texture("buttons/quit_clicked.png"));

		Button.ButtonStyle quitStyle = new Button.ButtonStyle();
		quitStyle.up = skin.getDrawable("quit");
		quitStyle.down = skin.getDrawable("quit_clicked");

		Button quitButton = new Button(quitStyle);
		quitButton.setBounds(10, 10, 60, 60);
		quitButton.addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				Gdx.app.exit();
			}
		});

		stage.addActor(quitButton);
		// ----------
		// SUBMIT BUTTON
		skin.add("submit", new Texture("buttons/submit.png"));
		skin.add("submit_clicked", new Texture("buttons/submit_clicked.png"));
		skin.add("submit_disabled", new Texture("buttons/submit_disabled.png"));

		final Button.ButtonStyle submitStyle = new Button.ButtonStyle();
		submitStyle.disabled = skin.getDrawable("submit_disabled");
		submitStyle.up = skin.getDrawable("submit");
		submitStyle.down = skin.getDrawable("submit_clicked");

		submitButton = new Button(submitStyle);
		submitButton.setBounds(1700, 10, 200, 60);
		submitButton.addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				if (!submitButton.isDisabled()) {
					resetLevel();
					gameState = GameState.ANIM_PLAYING;
				}
				}
		});

		stage.addActor(submitButton);

		// ----------


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

		// UPDATE OF ENTITY
		if (endOfRound) {
            lpsHandler.update();
			EntityState newState = lpsHandler.getNewState();

			if (newState != null) {
				bob.updateState(newState);
			} else {
				bob.makeIDLE();
			}

			if (bob.checkIfWet()) {
				gameState = GameState.INPUT_HANDLING;
			}

			if (bob.chekIfWon()) {
				System.out.println("WIN");
			}
		}

		//Map
		mapManager.draw(deltaTime);

		// Batch
		batch.begin();
		bob.draw(batch, deltaTime);
		batch.draw(foreground, 0, 0);

		boolean allValid = true;
		for (int i = 0; i < rules.length; ++i) {
			rules[i].drawLight(batch, i);
			allValid &= (rules[i].isValid());
		}
		if (!allValid) {
			submitButton.setDisabled(true);
		} else {
			submitButton.setDisabled(false);
		}

		batch.end();

		// Stage
		stage.act(deltaTime);
		stage.draw();

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

	private void addInput(String lps, Type type, String name) {
		Brick brick = new Brick(stage, skin, lps, name, type);
		for (DragAndDrop.Target t: this.targets) {
			brick.addTarget(t);
		}

		inputs.put(name, brick);
	}

}
