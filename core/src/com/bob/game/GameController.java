package com.bob.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.bob.game.inputs.*;
import com.bob.game.levels.Level;
import com.bob.game.world.WorldController;

import java.util.LinkedList;

public class GameController {

    private final LayerGroup layerGroup;

    private Level currentLevel;

    private final InputsManager inputsManager;
    private final MacroManager macroManager;
    private final WorldController worldController;

    public GameController(Skin skin) {

        layerGroup = new LayerGroup();

        layerGroup.add("background", new BackgroundLayer());
        layerGroup.add("controls", new ControlsLayer(skin, this));
        layerGroup.add("inputs", new InputsLayer(skin));
        layerGroup.add("macro", new MacroLayer(skin));
        layerGroup.add("modal_inputs", new ModalLayer(skin));
        layerGroup.add("winning", new WinningLayer(skin, this));
        layerGroup.add("tutorial", new Tutorial(skin));

        inputsManager = new InputsManager();
        macroManager = new MacroManager();
        worldController = new WorldController();

        inputsManager.setLayer((InputsLayer)layerGroup.get("inputs"));
        inputsManager.initRuleView(skin, 1475, 1080 - 495);

        macroManager.setLayers((MacroLayer)layerGroup.get("macro"), (ModalLayer)layerGroup.get("modal_inputs"));
        macroManager.initView(skin);
        macroManager.addButtons(skin);
    }

    public void reset() {
        inputsManager.resetRules();
        worldController.resetBob(currentLevel.getX(), currentLevel.getY());
        worldController.resetLights();
    }

    public void startNewLevel() {
        /*if (currentLevel == WriteLevel.values()[0].getLevel()) {
            layerGroup.setVisibility("tutorial", true);
        }*/
        if (currentLevel.allowMacro()) {
            layerGroup.setVisibility("macro", true);
            layerGroup.setVisibility("inputs", false);

            /* TODO : Reset level */
        } else {
            layerGroup.setVisibility("macro", false);
            layerGroup.setVisibility("inputs", true);

            inputsManager.setupRules(currentLevel.getNoRules(), currentLevel.getRules());
            inputsManager.setupInputs(currentLevel.getInputs(), 1415, 1080 - 165);
        }

        worldController.setupWorld(currentLevel);
        worldController.initRender();
        ((BackgroundLayer)layerGroup.get("background")).changeText(currentLevel.getText());
    }

    public void render(float deltaTime) {

        ((ControlsLayer)layerGroup.get("controls")).disableSubmit(!inputsManager.checkRules());

        inputsManager.toggleLights();
        inputsManager.lightOnRule(worldController.getCurrentRuleIndex());

        macroManager.toggleLights();

        worldController.render(deltaTime);

        if (checkIfWon()) {
            layerGroup.setVisibility("winning", true);
        }

    }

    private boolean checkIfWon() {
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
        //Reset Bob?
        worldController.resetBob(currentLevel.getX(), currentLevel.getY());
        worldController.resetLights();

        if (currentLevel.allowMacro()) {
            startLPSAnim(macroManager.getRulesString());
        } else {
            if (inputsManager.onlyConsequentUsed()) {
                startMockAnim(inputsManager.getBlockStack());
            } else {
                startLPSAnim(inputsManager.getRulesString());
            }
        }
    }

    private void startMockAnim(LinkedList<Block> blockStack) {
        worldController.startMockAnimation(blockStack);
    }

    private void startLPSAnim(String LPS) {
        worldController.startLPSAnimation(currentLevel, LPS);
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
