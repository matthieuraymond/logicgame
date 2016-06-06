package com.bob.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import com.bob.game.inputs.Block;
import com.bob.game.inputs.Rule;
import com.bob.game.inputs.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayView {

    private Layer winningLayer;
    private final Group backgroundGroup = new Group();
    private final Group levelUIGroup = new Group();
    public static List<DragAndDrop.Target> targets; // todo fix public
    private List<Block> blocks;
    Image[] locking;

    private boolean isVisible;
    private Tutorial tutorial;

    Button submitButton;
    Label text;
    Skin skin;

    public PlayView(Skin skin, final PlayController playController) {

        this.skin = skin;

        this.winningLayer = new WinningLayer(skin, playController);

        tutorial = new Tutorial(skin);
        targets = new ArrayList<>();

        // Bkg
        Image foreground = new Image(new Texture("screens/foreground.png"));
        backgroundGroup.addActor(foreground);

        // Thumbs
        Image currentThumb = new Image(new Texture("thumbs/bob.png"));
        currentThumb.setBounds(25, 1080 - 148, currentThumb.getWidth(), currentThumb.getHeight());
        backgroundGroup.addActor(currentThumb);

        //Inputs
        blocks = new ArrayList<>();

        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        skin.add("imply", new Texture("blocks/imply.png"));
        skin.add("and", new Texture("blocks/and.png"));
        skin.add("not", new Texture("blocks/not.png"));

        String[] colors = {"red", "orange", "yellow", "green", "purple", "white"};
        String[] directions = {"Left", "Right", "Up", "Down"};

        for (String color : colors) {
            skin.add(color, new Texture("blocks/" + color + ".png"));
            skin.add(color + "_prev", new Texture("blocks/" + color + "_prev.png"));
        }
        for (String direction : directions) {
            skin.add(direction, new Texture("blocks/" + direction.toLowerCase() + ".png"));
        }

        // Rules
        skin.add("red_light", new Texture("lights/red.png"));
        skin.add("green_light", new Texture("lights/green.png"));
        skin.add("target", new Texture("blocks/target.png"));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();


        // DRAG N DROP
        TextTooltip.TextTooltipStyle tooltipStyle = new TextTooltip.TextTooltipStyle();
        skin.add("tooltip_bkg", new Texture("blocks/tooltip.png"));
        tooltipStyle.label = labelStyle;

        tooltipStyle.background = skin.getDrawable("tooltip_bkg");

        skin.add("tooltipStyle", tooltipStyle);

        // Label
        text = new Label("", labelStyle);
        text.setBounds(240, 945, 575, 125);
        levelUIGroup.addActor(text);

        // Buttons

        // QUIT BUTTON
        TextButton quitButton = new TextButton("MENU", skin, "blue_button");
        quitButton.setBounds(10, 15, 200, 50);
        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                hide();
            }
        });
        levelUIGroup.addActor(quitButton);

        // ----------
        // SUBMIT BUTTON
        submitButton = new TextButton("SUBMIT", skin, "green_button");
        submitButton.setBounds(1700, 10, 200, 60);
        submitButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                if (!submitButton.isDisabled()) {
                    playController.submit();
                }
            }
        });
        levelUIGroup.addActor(submitButton);

        // ----------
        // RESET BUTTON
        TextButton resetButton = new TextButton("RESET", skin, "blue_button");
        resetButton.setBounds(1450, 10, 200, 60);
        resetButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                playController.reset();
            }
        });

        levelUIGroup.addActor(resetButton);
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
                playController.updateSpeed(slider.getValue());
            }
        });

        levelUIGroup.addActor(slider);
        // -------
    }

    public void initRules(Rule[] rules) {
        locking = new Image[rules.length];

        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule(levelUIGroup, skin);
            DragAndDrop.Target[] rules_targets = rules[i].getTargets();

            Collections.addAll(targets, rules_targets);

            locking[i] = new Image(new Texture("blocks/locked.png"));
            locking[i].setBounds(1400, 573 - i * 70, 500, 70);
            levelUIGroup.addActor(locking[i]);
        }
    }

    public void show() {
        backgroundGroup.setVisible(true);
        levelUIGroup.setVisible(true);
        winningLayer.setVisibility(false);
        isVisible = true;
    }

    public void hide() {
        backgroundGroup.setVisible(false);
        levelUIGroup.setVisible(false);
        winningLayer.setVisibility(false);
        isVisible = false;
    }

    public void setStage(Stage stage) {
        stage.addActor(backgroundGroup);
        stage.addActor(levelUIGroup);
        winningLayer.setStage(stage);
        tutorial.setStage(stage);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void showTutorial() {
        tutorial.show();
    }

    public void changeText(String text) {
        this.text.setText(text);
    }

    public void disableSubmit(boolean disabled) {
        this.submitButton.setDisabled(disabled);
    }

    public void createInput(String lps, String name, Type type, String tooltip) {
        Block block = new Block(levelUIGroup, skin, lps, name, type, tooltip);

        for (DragAndDrop.Target t: targets) {
            block.addTarget(t);
        }

        blocks.add(block);
    }

    public void clearInputs() {
        for (Block i: blocks) {
            i.clear();
        }
        blocks.clear();
    }

    public void lockRules(int noRules) {
        for (int i = 0; i < locking.length; ++i) {
            if (i >= noRules) {
                locking[i].setVisible(true);
            } else {
                locking[i].setVisible(false);
            }
        }
    }

    public void showWinningScreen(boolean visible) {
        winningLayer.setVisibility(visible);
    }
}