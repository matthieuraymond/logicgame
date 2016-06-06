package com.bob.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ControlsLayer extends Layer {

    Button submitButton;

    public ControlsLayer(Skin skin, final PlayController controller) {
        TextButton quitButton = new TextButton("MENU", skin, "blue_button");
        quitButton.setBounds(10, 15, 200, 50);
        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                controller.hide();
            }
        });
        group.addActor(quitButton);

        // ----------
        // SUBMIT BUTTON
        submitButton = new TextButton("SUBMIT", skin, "green_button");
        submitButton.setBounds(1700, 10, 200, 60);
        submitButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                if (!submitButton.isDisabled()) {
                    controller.submit();
                }
            }
        });
        group.addActor(submitButton);

        // ----------
        // RESET BUTTON
        TextButton resetButton = new TextButton("RESET", skin, "blue_button");
        resetButton.setBounds(1450, 10, 200, 60);
        resetButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                controller.reset();
            }
        });

        group.addActor(resetButton);
        // ----------

        // SLIDER
        skin.add("slider_bkg", new Texture("buttons/slider_bkg.png"));
        skin.add("slider_knob", new Texture("buttons/slider_knob.png"));
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("slider_knob");
        sliderStyle.background = skin.getDrawable("slider_bkg");
        final Slider slider = new Slider(0, 4, 0.01f, false, sliderStyle);
        slider.setBounds(250, 30, 200, 25);
        slider.setValue(2);

        slider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                controller.updateSpeed(slider.getValue());
            }
        });

        group.addActor(slider);
    }

    public void disableSubmit(boolean disabled) {
        this.submitButton.setDisabled(disabled);
    }
}
