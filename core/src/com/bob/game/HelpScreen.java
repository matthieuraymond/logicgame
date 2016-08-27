package com.bob.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bob.main.TextureFactory;

public class HelpScreen extends Layer {
    private Image[] images;
    private Group imageGroup;
    private int current = 0;


    public HelpScreen(Skin skin) {
        initialVisibility = false;

        imageGroup = new Group();
        group.addActor(imageGroup);

        TextButton nextButton = new TextButton("NEXT", skin, "big_grey_button");
        nextButton.setBounds(810, 15, 300, 100);
        nextButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                next();
            }
        });
        group.addActor(nextButton);

        group.setVisible(false);
    }


    public void setImage(String path) {
        setImages(new String[]{path});
    }

    public void setImages(String[] imagePaths) {
        imageGroup.clear();
        images = new Image[imagePaths.length];

        for (int i = 0; i < images.length; i++) {
            images[i] = new Image(TextureFactory.createTexture(imagePaths[i]));
            imageGroup.addActor(images[i]);
            images[i].setVisible(false);
        }

        images[0].setVisible(true);
    }

    private void next() {
        if (current + 1 >= images.length) {
            hide();
        } else {
            images[current].setVisible(false);
            current++;
            images[current].setVisible(true);
        }
    }

    public void setStage(Stage stage) {
        stage.addActor(group);

        stage.addListener(new InputListener(){
            public boolean keyDown(InputEvent ie, int keycode) {
                if (group.isVisible() && (keycode == com.badlogic.gdx.Input.Keys.ENTER || keycode == com.badlogic.gdx.Input.Keys.SPACE)) {
                    next();
                } else if (keycode == Input.Keys.ESCAPE) {
                    hide();
                }
                return false;
            }
        });
    }

    private void hide() {
        current = 0;
        images[images.length - 1].setVisible(false);
        images[0].setVisible(true);
        setVisibility(false);
    }
}
