package com.bob.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackgroundLayer extends Layer {

    public BackgroundLayer() {
        // Bkg
        Image foreground = new Image(new Texture("screens/foreground.png"));
        group.addActor(foreground);

        // Thumbs
        Image currentThumb = new Image(new Texture("thumbs/bob.png"));
        currentThumb.setBounds(25, 1080 - 148, currentThumb.getWidth(), currentThumb.getHeight());
        group.addActor(currentThumb);
    }
}
