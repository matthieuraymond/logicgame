package com.bob.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.bob.game.GameController;
import com.bob.game.levels.Level;
import com.bob.game.world.Textures;

public class Main extends ApplicationAdapter {

	// Game State
	protected GameState gameState;
	float gameStateTime;

	Skin skin;
	Stage stage;

	protected Menu menu;
	GameController gameController;

	@Override
	public void create() {

        skin = new Skin();
		stage = new Stage();

		skin.add("font", new BitmapFont());

		addButtonStyle();

		gameController = new GameController(skin);
		menu = new Menu(skin);

		gameController.linkStage(stage);
		menu.setStage(stage);

		gameStateTime = 0;
		gameState = GameState.MENU;

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		//batch.dispose();
		stage.dispose();

		for (Textures t: Textures.values()) {
			t.dispose();
		}
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render() {

		float deltaTime = Gdx.graphics.getDeltaTime();
        gameStateTime += deltaTime;

		Gdx.gl.glClearColor(39, 156, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (gameState == GameState.MENU) {
			if (!menu.isVisible()) {
				startLevel(menu.getLevelSelected());
			}
		}

		if (gameState == GameState.PLAYING) {
			gameController.render(deltaTime);

			if (!gameController.isVisible()) {
				menu.show();
				gameState = GameState.MENU;
			}
		}

		// Stage
		stage.act(deltaTime);
		stage.draw();
		//stage.setDebugAll(true);
	}

	protected void startLevel(Level lvl) {
		gameController.setLevel(lvl);
		gameController.show();
		gameController.startNewLevel();
		gameState = GameState.PLAYING;
	}

	private void addButtonStyle() {
		skin.add("impact", new BitmapFont(Gdx.files.internal("font/impact.fnt")));

		BitmapFont smallFont = new BitmapFont(Gdx.files.internal("font/impact.fnt"));
		smallFont.getData().scale(-0.3f);
		skin.add("impact_small", smallFont);

		String[] buttonColor = {"grey", "grey_square", "big_grey", "orange", "red", "green", "blue"};
		String[] buttonFont = {"impact_small", "impact", "impact", "impact_small", "impact_small", "impact_small", "impact_small"};

		for (int i = 0; i < buttonColor.length; i++) {
			String color = buttonColor[i];
			skin.add(color + "_button", new Texture("buttons/" + color + ".png"));
			skin.add(color + "_clicked", new Texture("buttons/" + color + "_clicked.png"));
			TextButton.TextButtonStyle colorButtonStyle = new TextButton.TextButtonStyle();
			colorButtonStyle.up = skin.getDrawable(color + "_button");
			colorButtonStyle.down = skin.getDrawable(color + "_clicked");
			colorButtonStyle.disabled = skin.getDrawable("grey_button");
			colorButtonStyle.font = skin.getFont(buttonFont[i]);
			skin.add(color + "_button", colorButtonStyle);
		}
	}
}
