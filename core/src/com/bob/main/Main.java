package com.bob.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bob.game.GameController;
import com.bob.game.levels.Level;
import com.bob.game.levels.LevelFactory;
import com.bob.game.world.Textures;

public class Main extends ApplicationAdapter {

	// Game State
	protected GameState gameState;
	private float gameStateTime;

	private Skin skin;
	private Stage stage;

	protected Menu menu;
	private GameController gameController;

	@Override
	public void create() {

        skin = new Skin();
		stage = new Stage(new FitViewport(1920, 1080));

		skin.add("font", new BitmapFont());

		addButtonStyle();

		OrthographicCamera camera = new OrthographicCamera();
		Viewport viewport = new FitViewport(1920,1080,camera);
		camera.position.set(960,540,0);
		viewport.apply();

		gameController = new GameController(skin, camera);
		menu = new Menu(skin);

		gameController.linkStage(stage);
		menu.setStage(stage);

		gameStateTime = 0;
		gameState = GameState.MENU;

		LevelFactory.initialiseLevels();

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		//batch.dispose();
		stage.dispose();

		for (Textures t: Textures.values()) {
			t.dispose();
		}

		TextureFactory.dispose();
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

		BitmapFont impactFont = new BitmapFont(Gdx.files.internal("font/impact.fnt"));
		impactFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		skin.add("impact", impactFont);

		BitmapFont smallFont = new BitmapFont(Gdx.files.internal("font/impact.fnt"));
		smallFont.getData().scale(-0.3f);
		smallFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		skin.add("impact_small", smallFont);

		String[] buttonColor = {"grey", "grey_square", "big_grey", "orange", "red", "green", "blue", "yellow"};
		String[] buttonFont = {"impact_small", "impact", "impact", "impact_small", "impact_small", "impact_small", "impact_small", "impact_small"};

		for (int i = 0; i < buttonColor.length; i++) {
			String color = buttonColor[i];
			skin.add(color + "_button", TextureFactory.createTexture("buttons/" + color + ".png"));
			skin.add(color + "_clicked", TextureFactory.createTexture("buttons/" + color + "_clicked.png"));
			TextButton.TextButtonStyle colorButtonStyle = new TextButton.TextButtonStyle();
			colorButtonStyle.up = skin.getDrawable(color + "_button");
			colorButtonStyle.down = skin.getDrawable(color + "_clicked");
			colorButtonStyle.disabled = skin.getDrawable("grey_button");
			colorButtonStyle.font = skin.getFont(buttonFont[i]);
			skin.add(color + "_button", colorButtonStyle);
		}
	}
}
