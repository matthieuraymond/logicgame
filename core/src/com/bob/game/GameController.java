package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.bob.game.inputs.InputsLayer;
import com.bob.game.inputs.InputsManager;
import com.bob.game.world.WorldManager;

public class GameController {

    private LayerGroup layerGroup;

    private Level currentLevel;

    private InputsManager inputsManager;
    private WorldManager worldManager;

    public GameController(Skin skin) {

        layerGroup = new LayerGroup();

        layerGroup.add("background", new BackgroundLayer());
        layerGroup.add("controls", new ControlsLayer(skin, this));
        layerGroup.add("inputs", new InputsLayer(skin));
        layerGroup.add("winning", new WinningLayer(skin, this));
        layerGroup.add("tutorial", new Tutorial(skin));

        inputsManager = new InputsManager((InputsLayer)layerGroup.get("inputs"));
        worldManager = new WorldManager();
    }

    public void reset() {
        inputsManager.resetRules();
        worldManager.resetBob(currentLevel.getX(), currentLevel.getY());
    }

    public void startNewLevel() {
        if (currentLevel == WriteLevel.values()[0].getLevel()) {
            layerGroup.setVisibility("tutorial", true);
        }

        inputsManager.setupInputs(currentLevel);
        worldManager.setupWorld(currentLevel);

        ((BackgroundLayer)layerGroup.get("background")).changeText(currentLevel.getText());
    }

    public void render(float deltaTime) {

        ((ControlsLayer)layerGroup.get("controls")).disableSubmit(!inputsManager.checkRules());

        worldManager.render(deltaTime);

        if (worldManager.isLevelWon()) {
            layerGroup.setVisibility("winning", true);
        }

    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public void loadNextLevel() {
        currentLevel = currentLevel.next();
        startNewLevel();
        layerGroup.setVisibility("winning", false);
    }

    public void submit() {
        worldManager.startAnimation(currentLevel, inputsManager.getRulesString());
    }

    public void updateSpeed(float value) {
        worldManager.updateSpeed(value);
    }

    public void linkStage(Stage stage) {
        layerGroup.setStage(stage);
    }

    public void show() {
        layerGroup.show();
    }

    public void hide() {
        layerGroup.hide();
    }

    public boolean isVisible() {
        return layerGroup.isVisible();
    }
}
