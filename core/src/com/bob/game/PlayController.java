package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class PlayController {

    private PlayView view;

    // Level
    private Level currentLevel;
    private InputsManager inputsManager;
    private WorldManager worldManager;

    public PlayController(Skin skin) {

        view = new PlayView();
        view.initInterface(skin, this);
        view.hide();

        inputsManager = new InputsManager(view);
        worldManager = new WorldManager();
    }

    public void reset() {
        inputsManager.resetRules();
        worldManager.resetBob(currentLevel.getX(), currentLevel.getY());
    }

    public void startNewLevel() {
        if (currentLevel == WriteLevel.values()[0]) {
            view.showTutorial();
        }

        inputsManager.setupInputs(currentLevel);
        worldManager.setupWorld(currentLevel);

        view.changeText(currentLevel.getText());
    }

    public void render(float deltaTime) {

        view.disableSubmit(!inputsManager.checkRules());

        worldManager.render(deltaTime);

        if (worldManager.isLevelWon()) {
            view.showWinningScreen(true);
        }

    }

    public void setLevel(WriteLevel level) {
        this.currentLevel = level;
    }

    public void loadNextLevel() {
        currentLevel = currentLevel.next();
        startNewLevel();
        view.showWinningScreen(false);
    }

    public void submit() {
        worldManager.startAnimation(currentLevel, inputsManager.getRulesString());
    }

    public void linkStage(Stage stage) {
        view.setStage(stage);
    }

    public void show() {
        view.show();
    }

    public boolean isVisible() {
        return view.isVisible();
    }

    public void updateSpeed(float value) {
        worldManager.updateSpeed(value);
    }
}
