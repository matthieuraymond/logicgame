package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.bob.game.inputs.InputsLayer;
import com.bob.game.inputs.InputsManager;
import com.bob.game.levels.Level;
import com.bob.game.world.WorldController;

public class GameController {

    private LayerGroup layerGroup;

    private Level currentLevel;

    private InputsManager inputsManager;
    private WorldController worldController;

    public GameController(Skin skin) {

        layerGroup = new LayerGroup();

        layerGroup.add("background", new BackgroundLayer());
        layerGroup.add("controls", new ControlsLayer(skin, this));
        layerGroup.add("inputs", new InputsLayer(skin));
        layerGroup.add("winning", new WinningLayer(skin, this));
        layerGroup.add("tutorial", new Tutorial(skin));

        inputsManager = new InputsManager();
        worldController = new WorldController();

        inputsManager.setLayer((InputsLayer)layerGroup.get("inputs"));
        inputsManager.initRuleView();
    }

    public void reset() {
        inputsManager.resetRules();
        worldController.resetBob(currentLevel.getX(), currentLevel.getY());
    }

    public void startNewLevel() {
        /*if (currentLevel == WriteLevel.values()[0].getLevel()) {
            layerGroup.setVisibility("tutorial", true);
        }*/
        inputsManager.setupRules(currentLevel);
        inputsManager.setupInputs(currentLevel);
        worldController.setupWorld(currentLevel);
        worldController.initRender();

        ((BackgroundLayer)layerGroup.get("background")).changeText(currentLevel.getText());
    }

    public void render(float deltaTime) {

        ((ControlsLayer)layerGroup.get("controls")).disableSubmit(!inputsManager.checkRules());

        inputsManager.toggleLights();
        inputsManager.lightOnRule(worldController.getCurrentRuleIndex());

        worldController.render(deltaTime);

        if (checkIfWon()) {
            layerGroup.setVisibility("winning", true);
        }

    }

    public boolean checkIfWon() {
        return worldController.isLevelWon();
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
        startAnim(inputsManager.getRulesString());
    }

    public void startAnim(String LPS) {
        worldController.startAnimation(currentLevel, LPS);
    }

    public void updateSpeed(float value) {
        worldController.updateSpeed(value);
    }

    public void linkStage(Stage stage) {
        layerGroup.setStage(stage);
        worldController.setStage(stage);
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
