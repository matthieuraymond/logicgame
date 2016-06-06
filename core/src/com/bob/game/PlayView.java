package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.bob.game.inputs.InputsLayer;

public class PlayView {

    private Layer winningLayer;
    private BackgroundLayer backgroundLayer;
    private ControlsLayer controlsLayer;
    private InputsLayer inputsLayer;

    private boolean isVisible;
    private Tutorial tutorial;

    public PlayView(Skin skin, final PlayController playController) {
        this.winningLayer = new WinningLayer(skin, playController);
        this.backgroundLayer = new BackgroundLayer();
        this.inputsLayer = new InputsLayer(skin);
        this.controlsLayer = new ControlsLayer(skin, playController);

        tutorial = new Tutorial(skin);
    }

    public void show() {
        backgroundLayer.setInitialVisibility();
        controlsLayer.setInitialVisibility();
        inputsLayer.setInitialVisibility();
        winningLayer.setInitialVisibility();
        isVisible = true;
    }

    public void hide() {
        backgroundLayer.setVisibility(false);
        controlsLayer.setVisibility(false);
        inputsLayer.setVisibility(true);
        winningLayer.setVisibility(false);
        isVisible = false;
    }

    public void setStage(Stage stage) {
        backgroundLayer.setStage(stage);
        controlsLayer.setStage(stage);
        inputsLayer.setStage(stage);
        winningLayer.setStage(stage);
        tutorial.setStage(stage);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void showTutorial() {
        tutorial.show();
    }

    public void showWinningScreen(boolean visible) {
        winningLayer.setVisibility(visible);
    }

    public void changeText(String text) {
        backgroundLayer.changeText(text);
    }

    public void disableSubmit(boolean disabled) {
        controlsLayer.disableSubmit(disabled);
    }

    public InputsLayer getInputsLayer() {
        return inputsLayer;
    }
}