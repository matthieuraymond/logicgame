package com.bob.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.bob.main.Main;

import java.io.File;
import java.io.FileInputStream;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;

		if (arg.length > 0) {

			final String path = arg[0];

			XmlReader xmlReader = new XmlReader();

			try {

				FileHandle localFile = new FileHandle("maps/tmx/custom.tmx");
				localFile.write(new FileInputStream(new File(path)), false);

				XmlReader.Element root = xmlReader.parse(localFile);

				XmlReader.Element levelNode = root.getChildByName("level");
				XmlReader.Element bobNode = levelNode.getChildByName("bob");

				int startX = bobNode.getInt("x");
				int startY = bobNode.getInt("y");
				int noRules = levelNode.getChildByName("rules").getIntAttribute("available");
				/*
				final Level customLevel = new Level("maps/tmx/custom.tmx", startX, startY, noRules, new Block[]{}, "", new String[]{}) {
					@Override
					public Level getNext() {
						return this;
					}

					@Override
					public void save() {

					}
				};

				new LwjglApplication(new Main() {
					@Override
					public void create() {

						super.create();
						menu.hide();
						startLevel(customLevel);
						this.gameState = GameState.PLAYING;
					}
				}, config);
				*/
				return;
			} catch (Exception e) {
				// Empty as doing the same as when no files are found
				e.printStackTrace();
			}
		}

		new LwjglApplication(new Main(), config);

	}
}
