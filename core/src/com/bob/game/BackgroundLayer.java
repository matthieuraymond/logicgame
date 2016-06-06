package com.bob.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class BackgroundLayer extends Layer {

    Label text;

    public BackgroundLayer() {
        // Bkg
        Image foreground = new Image(new Texture("screens/foreground.png"));
        group.addActor(foreground);

        // Thumbs
        Image currentThumb = new Image(new Texture("thumbs/bob.png"));
        currentThumb.setBounds(25, 1080 - 148, currentThumb.getWidth(), currentThumb.getHeight());
        group.addActor(currentThumb);

        // Text
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        text = new Label("", labelStyle);
        text.setBounds(240, 945, 575, 125);
        group.addActor(text);
    }

    public void changeText(String text) {
        this.text.setText(text);
    }
}
