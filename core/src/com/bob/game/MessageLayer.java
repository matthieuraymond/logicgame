package com.bob.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.bob.main.TextureFactory;

public class MessageLayer extends Layer {

    private final Label text;

    public MessageLayer(Skin skin, final GameController gameController) {
        initialVisibility = false;
        group.setVisible(initialVisibility);

        Image modal = new Image(TextureFactory.createTexture("screens/modal.png"));
        modal.setBounds(710, 540, 500, 300);
        group.addActor(modal);

        // TEXT

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("white");
        labelStyle.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        text = new Label("", labelStyle);
        text.setWrap(true);
        text.setBounds(710, 540, 500, 300);
        text.setAlignment(Align.top);
        group.addActor(text);

        TextButton nextButton = new TextButton("Understood", skin, "grey_button");
        nextButton.setBounds(810, 560, 300, 75);
        nextButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                gameController.resetWorld();
                group.setVisible(false);
            }
        });
        group.addActor(nextButton);
    }

    public void changeText(String text) {
        this.text.setText(text);
    }
}
