package com.lps.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Tutorial {
    private final Group group = new Group();
    private final Image[] images = new Image[4];
    private int current = 0;


    public Tutorial(Skin skin) {

        for (int i = 0; i < images.length; i++) {
            images[i] = new Image(new Texture("screens/tut" + Integer.toString(1 + i) + ".png"));
            group.addActor(images[i]);
            images[i].setVisible(false);
        }

        images[0].setVisible(true);

        TextButton nextButton = new TextButton("NEXT", skin, "grey_button");
        nextButton.setBounds(900, 1080 - 65, 200, 50);
        nextButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                next();
            }
        });

        group.addActor(nextButton);

        group.setVisible(false);
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
                if (keycode == com.badlogic.gdx.Input.Keys.ENTER || keycode == com.badlogic.gdx.Input.Keys.SPACE) {
                    next();
                } else if (keycode == Input.Keys.ESCAPE) {
                    hide();
                }
                return false;
            }
        });
    }

    public void show() {
        group.setVisible(true);
    }

    public void hide() {
        current = 0;
        images[images.length - 1].setVisible(false);
        images[0].setVisible(true);
        group.setVisible(false);
    }
}
