package com.lps.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class PlayController {

    SpriteBatch batch;
    Entity bob;

    // Map and LPS
    MapManager mapManager;
    LPSHandler lpsHandler;
    private boolean isAnimPlaying = false;

    Rule[] rules;

    private WriteLevel currentLevel;
    private PlayView view;
    private float speedFactor = 2f;
    private int nbWon = 0;

    public PlayController(Skin skin) {

        rules = new Rule[8];

        view = new PlayView();
        view.initInterface(skin, this);
        view.hide();
        currentLevel = WriteLevel.level1;
        batch = new SpriteBatch();
    }

    private boolean checkRules() {
        boolean allValid = true;

        for (Rule rule : rules) {
            allValid &= (rule.isValid());
        }

        return allValid;
    }

    public void resetRules() {
        for (Rule r: rules) {
            r.reset();
        }
    }

    private void resetInputs(String[] colors, boolean prevAuthorised) {
        view.clearInputs();

        for (String color : colors) {
            view.createInput(color + "(X,Y)", color, Type.FLUENT, "If Bob is on a " + color + " cell");
            if (prevAuthorised) {
                view.createInput(color + "(U,V)", color + "_prev", Type.FLUENT, "If Bob was previously on a " + color + " cell");
            }
        }

        for (String direction : new String[]{"Left", "Right", "Up", "Down"}) {
            view.createInput("go" + direction + "()", direction, Type.CONSEQUENT, "Bob should go " + direction);
        }

        view.createInput("&", "and", Type.AND, "AND, to be used in: if a AND b");
        view.createInput("->", "imply", Type.IMPLY, "IMPLY/THEN, to be used in: if a THEN b");
        view.createInput("!", "not", Type.NOT, "NOT, to be used in: NOT a");
    }


    public void resetWorld() {

        mapManager = new MapManager(currentLevel.getMap());

        bob = new Entity(currentLevel.getX(), currentLevel.getY());

        StringBuilder inputs = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            inputs.append(rules[i].getString());
        }

        view.lockRules(currentLevel.getNoRules());

        view.changeText(currentLevel.getText());

        lpsHandler = new LPSHandler(mapManager, inputs.toString(), currentLevel.getX(), currentLevel.getY());
        nbWon = 0;
    }


    public void resetLevel() {
        resetRules();
        resetWorld();
    }

    public void startNewLevel() {
        if (currentLevel == WriteLevel.values()[0]) {
            view.showTutorial();
        }

        resetInputs(currentLevel.getColors(), currentLevel.isPrevAuthorised());
        resetRules();
        resetWorld();

        isAnimPlaying = false;
    }
    
    public void render(float deltaTime) {

        // Inputs
        view.disableSubmit(!checkRules());

        // UPDATE OF ENTITY
        bob.increaseTime(deltaTime * speedFactor);

        if (bob.needInstructions()) {
            retrieveInstructions();
            updateGameState();
        }

        //Map
        if (mapManager != null) mapManager.draw(isAnimPlaying ? deltaTime * speedFactor : 0);

        // Batch
        batch.begin();
        if (bob != null) bob.draw(batch);
        batch.end();
    }
        
    private void retrieveInstructions() {
        lpsHandler.update();
        bob.updateState(lpsHandler.getNewState());
    }

    private void updateGameState() {

        WorldCoordinates coord = bob.getCoord();

        if (mapManager.checkIfWet(coord)) {
            bob.updateState(EntityState.WET);
            isAnimPlaying = false;
        }

        if (mapManager.chekIfWon(coord)) {
            bob.updateState(EntityState.WON);
            nbWon++;
            if (nbWon > 2){
                view.showWinningScreen(true);
            }
        }
    }

    public void setLevel(WriteLevel level) {
        this.currentLevel = level;
    }

    public void updateSpeed(float newValue) {
        speedFactor = newValue;
    }

    public void loadNextLevel() {
        currentLevel = currentLevel.next();
        startNewLevel();
        view.showWinningScreen(false);
    }

    public void submit() {
        resetWorld();
        isAnimPlaying = true;
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
}
