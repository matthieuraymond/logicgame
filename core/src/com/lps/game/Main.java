package com.lps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends ApplicationAdapter {

    // BOB
    SpriteBatch batch;
    Entity bob;

    // UI
    Stage stage;
    Skin skin;
	BitmapFont font;
	BitmapFont impactFont;
    Button submitButton;

	// Rules
    HashMap<String, Brick> inputs;
	ArrayList<DragAndDrop.Target> targets;
    Rule[] rules;
	Image[] locking;

	// Map and LPS
    MapManager mapManager;
    LPSHandler lpsHandler;

	// Levels and counters
    float roundTime;
	Level currentLevel;
	GameState gameState;
	float roundDuration;
	float gameStateTime;

	//Groups
	Group backgroundGroup;
	Group levelUIGroup;
	Group winningGroup;
	Group menuGroup;
	Group levelsGroup;
	Group settingsGroup;



	@Override
	public void create() {

        // Initialisations
        batch = new SpriteBatch();
        stage = new Stage();
        skin = new Skin();
        font = new BitmapFont();
		impactFont = new BitmapFont();
        inputs = new HashMap<>();
        targets = new ArrayList<>(80);
        rules = new Rule[8];
		locking = new Image[8];
		backgroundGroup = new Group();
		levelUIGroup = new Group();
		winningGroup = new Group();
		menuGroup = new Group();
		settingsGroup = new Group();
		levelsGroup = new Group();

		stage.addActor(backgroundGroup);
		stage.addActor(levelUIGroup);
		stage.addActor(winningGroup);
		stage.addActor(menuGroup);
		stage.addActor(settingsGroup);
		stage.addActor(levelsGroup);

		levelsGroup.setVisible(false);
		settingsGroup.setVisible(false);

		currentLevel = Level.level1;
		gameStateTime = 0;

		Gdx.input.setInputProcessor(stage);

		// Menu
		Image menuBkg = new Image(new Texture("screens/menu.png"));
		menuBkg.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		menuGroup.addActor(menuBkg);

		// Menu button
		String[] menu = {"new_game", "levels", "settings", "quit"};
		HashMap<String, Button> menuButtons = new HashMap<>();
		int menuButtonY = 430;

		for (int i = 0; i < menu.length; i++) {
			skin.add(menu[i], new Texture("menu_buttons/" + menu[i] + ".png"));
			skin.add(menu[i] + "_clicked", new Texture("menu_buttons/" + menu[i] + "_clicked.png"));

			Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
			buttonStyle.up = skin.getDrawable(menu[i]);
			buttonStyle.down = skin.getDrawable(menu[i] + "_clicked");

			Button button = new Button(buttonStyle);
			button.setBounds(760, menuButtonY, 400, 100);

			menuButtons.put(menu[i], button);
			menuGroup.addActor(button);

			menuButtonY -= 125;
		}

		menuButtons.get("new_game").addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				menuGroup.setVisible(false);
			}
		});

		menuButtons.get("quit").addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				Gdx.app.exit();
			}
		});

		menuButtons.get("levels").addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				levelsGroup.setVisible(true);
			}
		});

		// Levels Menu
		Image levelsBkg = new Image(new Texture("screens/menu.png"));
		levelsBkg.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		levelsGroup.addActor(levelsBkg);

		skin.add("level", new Texture("menu_buttons/level.png"));
		skin.add("level_clicked", new Texture("menu_buttons/level_clicked.png"));

		TextButton.TextButtonStyle levelButtonStyle = new TextButton.TextButtonStyle();
		levelButtonStyle.up = skin.getDrawable("level");
		levelButtonStyle.down = skin.getDrawable("level_clicked");
		levelButtonStyle.font = new BitmapFont();
		levelButtonStyle.font.getData().scale(4f);

		int noLevels = Level.values().length;
		int levelsButtonX = 660;
		int levelsButtonY = 430;

		for (int i = 0; i < noLevels; i++) {
			final int j = i;
			TextButton button = new TextButton(Integer.toString(i + 1), levelButtonStyle);
			button.setBounds(levelsButtonX, levelsButtonY, 100, 100);

			button.setName(Integer.toString(i));

			button.addListener(new ClickListener() {
				public void clicked(InputEvent ie, float x, float y) {
					currentLevel = Level.values()[j];
					resetLevel();
					menuGroup.setVisible(false);
					levelsGroup.setVisible(false);
				}
			});


			levelsGroup.addActor(button);

			levelsButtonX += 125;

			if (levelsButtonX >= 1360) {
				levelsButtonY -= 125;
				levelsButtonX = 560;
			}
		}

		skin.add("back", new Texture("menu_buttons/back.png"));
		skin.add("back_clicked", new Texture("menu_buttons/back_clicked.png"));

		Button.ButtonStyle backStyle = new Button.ButtonStyle();
		backStyle.up = skin.getDrawable("back");
		backStyle.down = skin.getDrawable("back_clicked");

		Button backButton = new Button(backStyle);
		backButton.setBounds(10, 15, 200, 50);
		backButton.addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				levelsGroup.setVisible(false);
			}
		});

		levelsGroup.addActor(backButton);

		// Bkg
		Image foreground = new Image(new Texture("screens/foreground.png"));
		backgroundGroup.addActor(foreground);

		// Thumbs
		Image currentThumb = new Image(new Texture("thumbs/bob.png"));
		currentThumb.setBounds(25, 1080 - 148, currentThumb.getWidth(), currentThumb.getHeight());
		backgroundGroup.addActor(currentThumb);

		// Rules
		skin.add("red_light", new Texture("lights/red.png"));
		skin.add("green_light", new Texture("lights/green.png"));
		skin.add("target", new Texture("inputs/target.png"));

		for (int i = 0; i < rules.length; i++) {
			rules[i] = new Rule(levelUIGroup, skin);
			DragAndDrop.Target[] targets = rules[i].getTargets();
			for (DragAndDrop.Target t: targets) {
				this.targets.add(t);
			}

			locking[i] = new Image(new Texture("inputs/locked.png"));
			locking[i].setBounds(1400, 573 - i * 70, 500, 70);
			levelUIGroup.addActor(locking[i]);
		}

		// DRAG N DROP
		TextTooltip.TextTooltipStyle tooltipStyle = new TextTooltip.TextTooltipStyle();
		skin.add("tooltip_bkg", new Texture("inputs/tooltip.png"));
		tooltipStyle.label = new Label.LabelStyle();
		tooltipStyle.label.font = font;
		tooltipStyle.background = skin.getDrawable("tooltip_bkg");

		skin.add("tooltipStyle", tooltipStyle);

		skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
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


		for (String color : colors) {
			addInput(color + "(X,Y)", Type.FLUENT, color, "If Bob is on a " + color + " cell");
			addInput(color + "(U,V)", Type.FLUENT, color + "_prev", "If Bob was previously on a " + color + " cell");
		}

		for (String direction : directions) {
			addInput("go" + direction + "()", Type.CONSEQUENT, direction, "Bob should go " + direction);
		}

		addInput("&", Type.AND,  "and", "AND, to be used in: if a AND b");
		addInput("->", Type.IMPLY,  "imply", "IMPLY/THEN, to be used in: if a THEN b");
		addInput("!", Type.NOT,  "not", "NOT, to be used in: NOT a");


		// Buttons

		// QUIT BUTTON
		skin.add("menu", new Texture("buttons/menu.png"));
		skin.add("menu_clicked", new Texture("buttons/menu_clicked.png"));

		Button.ButtonStyle quitStyle = new Button.ButtonStyle();
		quitStyle.up = skin.getDrawable("menu");
		quitStyle.down = skin.getDrawable("menu_clicked");

		Button quitButton = new Button(quitStyle);
		quitButton.setBounds(10, 15, 120, 50);
		quitButton.addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				menuGroup.setVisible(true);
			}
		});

		levelUIGroup.addActor(quitButton);

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

		levelUIGroup.addActor(submitButton);
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

		levelUIGroup.addActor(resetButton);
		// ----------

        // SLIDER
        skin.add("slider_bkg", new Texture("buttons/slider_bkg.png"));
        skin.add("slider_knob", new Texture("buttons/slider_knob.png"));
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("slider_knob");
        sliderStyle.background = skin.getDrawable("slider_bkg");
        final Slider slider = new Slider(0, 2, 0.01f, false, sliderStyle);
        slider.setBounds(150, 30, 200, 25);

        slider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
				float previousTime = roundTime/roundDuration; // to avoid jump
                roundDuration = 2.2f - slider.getValue();
				roundTime = previousTime * roundDuration;
            }
        });
        slider.setValue(1.5f);

        roundDuration = 2.2f - slider.getValue();
        levelUIGroup.addActor(slider);
        // -------


        // Winning screen
		Image winningScreen = new Image(new Texture("screens/winning.png"));

		winningGroup.addActor(winningScreen);

		skin.add("next", new Texture("buttons/next.png"));
		skin.add("next_clicked", new Texture("buttons/next_clicked.png"));
		final Button.ButtonStyle nextStyle = new Button.ButtonStyle();
		nextStyle.up = skin.getDrawable("next");
		nextStyle.down = skin.getDrawable("next_clicked");
		Button nextButton = new Button(nextStyle);
		nextButton.setBounds(860, 400, 200, 60);
		nextButton.addListener(new ClickListener() {
			public void clicked(InputEvent ie, float x, float y) {
				currentLevel = currentLevel.next();
				resetLevel();
				resetRules();
				showWinningScreen(false);
			}
		});

		winningGroup.addActor(nextButton);

		showWinningScreen(false);
		resetLevel();
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();

		for (Textures t: Textures.values()) {
			t.dispose();
		}

		font.dispose();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render() {

		float deltaTime = Gdx.graphics.getDeltaTime();
		roundTime += deltaTime;
        gameStateTime += deltaTime;

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
		mapManager.draw((gameState != GameState.INPUT_HANDLING) ? deltaTime : 0);

		// Batch
		batch.begin();
		bob.draw(batch, (gameState != GameState.INPUT_HANDLING) ? deltaTime : 0, roundDuration);
		batch.end();

		// Stage
		stage.act(deltaTime);
		stage.draw();
		//stage.setDebugAll(true);

		if (endOfRound) roundTime = 0;
	}

    private void checkRules() {
        boolean allValid = true;

        for (int i = 0; i < rules.length; ++i) {
            allValid &= (rules[i].isValid(skin));
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
			changeGameState(GameState.INPUT_HANDLING);
		}

		if (bob.chekIfWon()) {
			changeGameState(GameState.WINNING_SCREEN);
			if (gameStateTime > 1) {
				showWinningScreen(true);
			}
		}
    }

	private void showWinningScreen(boolean visible) {
		winningGroup.setVisible(visible);
	}

	private void changeGameState(GameState newState) {
		if (gameState != newState) {
			gameStateTime = 0;
		}
		gameState = newState;
	}

	private void resetLevel() {

		mapManager = new MapManager(currentLevel.getMap());
		bob = new Entity(mapManager, currentLevel.getX(), currentLevel.getY());

		StringBuilder inputs = new StringBuilder();
		for (int i = 0; i < rules.length; i++) {
			inputs.append(rules[i].getString());

			if (i >= currentLevel.getNoRules()) {
				locking[i].setVisible(true);
			} else {
				locking[i].setVisible(false);
			}
		}

		lpsHandler = new LPSHandler(mapManager, inputs.toString(), currentLevel.getX(), currentLevel.getY());
		roundTime = 0;
		gameState = GameState.INPUT_HANDLING;

	}

	private void addInput(String lps, Type type, String name, String tooltip) {
		Brick brick = new Brick(levelUIGroup, skin, lps, name, type, tooltip);
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
