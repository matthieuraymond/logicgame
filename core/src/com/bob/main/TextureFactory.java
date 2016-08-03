package com.bob.main;

import com.badlogic.gdx.graphics.Texture;
import java.util.List;
import java.util.LinkedList;

public class TextureFactory {

	private static List<Texture> allTextures = new LinkedList<Texture>();

	public static Texture createTexture(String path) {
		Texture t = new Texture(path);
		t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		allTextures.add(t);

		return t;
	}

	//TODO dispose textures;
}