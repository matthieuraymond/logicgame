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
import java.util.Map;

public class Main extends ApplicationAdapter {

	// Game State
	GameState gameState;
	float gameStateTime;

	Skin skin;
	Stage stage;

	Menu menu;
	PlayingInterface playingInterface;


	@Override
	public void create() {

        skin = new Skin();
		stage = new Stage();

		playingInterface = new PlayingInterface(skin);
		menu = new Menu(skin);

		skin.add("font", new BitmapFont());

		playingInterface.setStage(stage);
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
				playingInterface.setLevel(menu.getLevelSelected());
				playingInterface.resetWorld();
				gameState = GameState.PLAYING;
				playingInterface.show();
			}
		}

		if (gameState == GameState.PLAYING) {
			playingInterface.drawWorld(deltaTime);

			if (!playingInterface.isVisible()) {
				menu.show();
				gameState = GameState.MENU;
			}
		}

		// Stage
		stage.act(deltaTime);
		stage.draw();
		//stage.setDebugAll(true);
	}
}
