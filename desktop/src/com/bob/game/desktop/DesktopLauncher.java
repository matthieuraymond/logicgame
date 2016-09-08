package com.bob.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bob.game.levels.Level;
import com.bob.game.levels.LevelFactory;
import com.bob.main.GameState;
import com.bob.main.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;

		if (arg.length > 0) {

			final String path = arg[0];
			final Level customLevel = LevelFactory.loadExternaLevel(path);

			new LwjglApplication(new Main() {
				@Override
				public void create() {

					super.create();
					menu.hide();
					startLevel(customLevel);
					this.gameState = GameState.PLAYING;
				}
			}, config);

			return;

		}

		new LwjglApplication(new Main(), config);

	}
}
