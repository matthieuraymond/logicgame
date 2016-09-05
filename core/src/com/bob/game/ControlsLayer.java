package com.bob.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bob.main.TextureFactory;

class ControlsLayer extends Layer {

    private final Button submitButton;
    private final Button resetButton;

    public ControlsLayer(Skin skin, final GameController controller) {
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
        resetButton = new TextButton("RESET ALL", skin, "yellow_button");
        resetButton.setBounds(1260, 10, 210, 60);
        resetButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                controller.reset();
            }
        });

        group.addActor(resetButton);
        // ----------

        // RESET BOB

        TextButton resetBobButton = new TextButton("RESET BOB", skin, "yellow_button");
        resetBobButton.setBounds(1480, 10, 210, 60);
        resetBobButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                controller.resetWorld();
            }
        });

        group.addActor(resetBobButton);
        // ----------

        // SLIDER
        int xSlider = 300;
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/impact.fnt"));
        font.getData().scale(-0.6f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        Label speedLabel = new Label("Speed", labelStyle);
        speedLabel.setBounds(xSlider, 50, 100, 25);
        group.addActor(speedLabel);

        Image turtle = new Image(TextureFactory.createTexture("buttons/turtle.png"));
        turtle.setBounds(xSlider -10, 10, 35, 25);
        group.addActor(turtle);

        Image rabbit = new Image(TextureFactory.createTexture("buttons/rabbit.png"));
        rabbit.setBounds(xSlider + 175, 10, 35, 25);
        group.addActor(rabbit);

        skin.add("slider_bkg", TextureFactory.createTexture("buttons/slider_bkg.png"));
        skin.add("slider_knob", TextureFactory.createTexture("buttons/slider_knob.png"));
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("slider_knob");
        sliderStyle.background = skin.getDrawable("slider_bkg");
        final Slider slider = new Slider(0, 4, 0.01f, false, sliderStyle);
        slider.setBounds(xSlider, 30, 200, 25);
        slider.setValue(2);

        slider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                controller.updateSpeed(slider.getValue());
            }
        });

        group.addActor(slider);

        // Pause
        TextButton pauseButton = new TextButton("||", skin, "yellow_button");
        pauseButton.setBounds(220, 20, 30, 40);
        pauseButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                slider.setValue(0);
            }
        });

        group.addActor(pauseButton);

        // Play
        TextButton playButton = new TextButton(">", skin, "yellow_button");
        playButton.setBounds(260, 20, 30, 40);
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                slider.setValue(2);
            }
        });

        group.addActor(playButton);

    }

    public void disableSubmit(boolean disabled) {
        this.submitButton.setDisabled(disabled);
    }

    public void disableReset(boolean disabled) {
        this.resetButton.setVisible(disabled);
    }
}
