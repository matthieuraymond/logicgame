package com.lps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Main extends ApplicationAdapter {

	// Game State
	GameState gameState;
	float gameStateTime;

	Skin skin;
	Stage stage;

	Menu menu;
	PlayController playController;


	@Override
	public void create() {

        skin = new Skin();
		stage = new Stage();

		skin.add("font", new BitmapFont());

		addButtonStyle();

		playController = new PlayController(skin);
		menu = new Menu(skin);

		playController.linkStage(stage);
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
				playController.setLevel(menu.getLevelSelected());
				playController.startNewLevel();
				gameState = GameState.PLAYING;
				playController.show();
			}
		}

		if (gameState == GameState.PLAYING) {
			playController.render(deltaTime);

			if (!playController.isVisible()) {
				menu.show();
				gameState = GameState.MENU;
			}
		}

		// Stage
		stage.act(deltaTime);
		stage.draw();
		//stage.setDebugAll(true);
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
