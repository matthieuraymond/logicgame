package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bob.main.TextureFactory;

public class ConfusedLayer extends Layer {
    public ConfusedLayer(Skin skin, final GameController gameController) {
        initialVisibility = false;
        group.setVisible(initialVisibility);

        Image modal = new Image(TextureFactory.createTexture("screens/confused.png"));
        modal.setBounds(710, 540, 500, 300);
        group.addActor(modal);

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
}
