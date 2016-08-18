package com.bob.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.LinkedList;

public class TextureFactory {

	private static List<Texture> allTextures = new LinkedList<Texture>();

	public static Texture createTexture(String path) {
		Texture t = new Texture(path);
		t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		allTextures.add(t);

		if (Config.printNoTexture) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println( sdf.format(cal.getTime()) + " - " + allTextures.size());
		}
		return t;
	}

	public static void dispose() {
		for(Texture texture: allTextures) {
			texture.dispose();
		}
	}
}