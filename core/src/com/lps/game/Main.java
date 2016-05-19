package com.lps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends ApplicationAdapter {

    // BOB
    SpriteBatch batch = new SpriteBatch();
    Entity bob;
    Texture foreground = new Texture("foreground.png");

    // UI
    Stage stage = new Stage();
    Skin skin = new Skin();
    BitmapFont font = new BitmapFont();
    Button submitButton;

    // Thumb image
    HashMap<String, Texture> images = new HashMap<>();
    Texture currentThumb;

    // Rules
    HashMap<String, Brick> inputs = new HashMap<>();
    ArrayList<DragAndDrop.Target> targets = new ArrayList<>(80);
    Rule[] rules = new Rule[8];

    // Map and LPS
    MapManager mapManager;
    LPSHandler lpsHandler;

    // Levels and counters
    float roundTime;
    Level currentLevel = Level.level1;
    GameState gameState;
    float roundDuration;
    int roundWithoutMove;

	@Override
	public void create() {

		Gdx.input.setInputProcessor(stage);

		// Thumbs
		images.put("bob", new Texture("thumbs/bob.png"));
		currentThumb = images.get("bob");

		// DRAG N DROP
		skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("target", new Texture("inputs/target.png"));
		skin.add("imply", new Texture("inputs/imply.png"));
		skin.add("and", new Texture("inputs/and.png"));
		skin.add("not", new Texture("inputs/not.png"));

		String[] colors = {"red", "orange", "yellow", "green", "purple", "white"};
		String[] directions = {"Left", "Right", "Up", "Down"};

		for (String color : colors) {
			skin.add(color, new Texture("inputs/" + color + ".png"));
			skin.add(color + "_prev", new Texture("inputs/" + color + "_prev.png"));
		}
		for (String direction : directions) {
			skin.add(direction, new Texture("inputs/" + direction.toLowerCase() + ".png"));
		}

		for (int i = 0; i < rules.length; i++) {
			rules[i] = new Rule(stage, skin);
			DragAndDrop.Target[] targets = rules[i].getTargets();
			for (DragAndDrop.Target t: targets) {
				this.targets.add(t);
			}
		}

		for (String color : colors) {
			addInput(color + "(X,Y) & isIn(X,Y)", Type.FLUENT, color);
			addInput(color + "(U,V) & wasIn(U,V)", Type.FLUENT, color + "_prev");
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
		// RESET BUTTON

		// SUBMIT BUTTON
		skin.add("reset", new Texture("buttons/reset.png"));
		skin.add("reset_clicked", new Texture("buttons/reset_clicked.png"));
		final Button.ButtonStyle resetStyle = new Button.ButtonStyle();
		resetStyle.up = skin.getDrawable("reset");
		resetStyle.down = skin.getDrawable("reset_clicked");
		Button resetButton = new Button(resetStyle);
		resetButton.setBounds(1450, 10, 200, 60);
		resetButton.addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				resetRules();
			}
		});

		stage.addActor(resetButton);
		// ----------

        // SLIDER
        skin.add("slider_bkg", new Texture("buttons/slider_bkg.png"));
        skin.add("slider_knob", new Texture("buttons/slider_knob.png"));
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("slider_knob");
        sliderStyle.background = skin.getDrawable("slider_bkg");
        final Slider slider = new Slider(0, 2, 0.01f, false, sliderStyle);
        slider.setBounds(100, 30, 200, 25);

        slider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                roundDuration = 2.2f - slider.getValue();
            }
        });
        slider.setValue(1.5f);

        roundDuration = 2.2f - slider.getValue();
        stage.addActor(slider);
        // -------

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
		font.dispose();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render() {

		float deltaTime = (gameState == GameState.ANIM_PLAYING) ? Gdx.graphics.getDeltaTime() : 0;
		roundTime += deltaTime;

        boolean endOfRound = roundTime >= roundDuration;

		Gdx.gl.glClearColor(39, 156, 255, 1); //for water
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// UPDATE OF ENTITY
        if (endOfRound) {
            updateEntity();
        }

        // Update of rules
        checkRules();

		//Map
		mapManager.draw(deltaTime);

		// Batch
		batch.begin();
		bob.draw(batch, deltaTime, roundDuration);
		batch.draw(foreground, 0, 0);
		batch.draw(currentThumb, 25, 1080 - 148);
		font.draw(batch, currentLevel.getText(), 250, 1080 - 25);

        for (int i = 0; i < rules.length; ++i) {
            rules[i].drawLight(batch, i);
        }

		batch.end();

		// Stage
		stage.act(deltaTime);
		stage.draw();

		if (endOfRound) roundTime = 0;
	}

    private void checkRules() {
        boolean allValid = true;

        for (int i = 0; i < rules.length; ++i) {
            allValid &= (rules[i].isValid());
        }

        if (!allValid) {
            submitButton.setDisabled(true);
        } else {
            submitButton.setDisabled(false);
        }
    }

    private void updateEntity() {
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
                //currentLevel = currentLevel.next();
                //gameState = GameState.INPUT_HANDLING;
                //resetLevel();
                resetRules();
            }
    }

    private void resetLevel() {

		mapManager = new MapManager(currentLevel.getMap());
		bob = new Entity(mapManager, currentLevel.getX(), currentLevel.getY());

		StringBuilder inputs = new StringBuilder();
		for (Rule r: rules) {
			inputs.append(r.getString());
		}

		lpsHandler = new LPSHandler(mapManager, inputs.toString(), currentLevel.getX(), currentLevel.getY());
		roundTime = 0;
		gameState = GameState.INPUT_HANDLING;

	}

	private void addInput(String lps, Type type, String name) {
		Brick brick = new Brick(stage, skin, lps, name, type);
		for (DragAndDrop.Target t: this.targets) {
			brick.addTarget(t);
		}

		inputs.put(name, brick);
	}

	private void resetRules() {
		for (Rule r: rules) {
			r.reset();
		}
	}
}
