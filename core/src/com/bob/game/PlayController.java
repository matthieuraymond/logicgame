package com.bob.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class PlayController {

    // Bob
    SpriteBatch batch;
    Entity bob;

    // Map and LPS
    MapManager mapManager;
    LPSHandler lpsHandler;

    // View
    private boolean isAnimPlaying = false;
    private float speedFactor = 2f;
    private PlayView view;
    private int nbWon = 0;

    // Level
    private Level currentLevel;
    private InputsManager inputsManager;

    public PlayController(Skin skin) {

        view = new PlayView();
        view.initInterface(skin, this);
        view.hide();

        inputsManager = new InputsManager(view);

        batch = new SpriteBatch();
    }

    public void resetWorld() {

        mapManager = new MapManager(currentLevel.getMap());

        bob = new Entity(currentLevel.getX(), currentLevel.getY());

        view.changeText(currentLevel.getText());

        lpsHandler = new LPSHandler(mapManager, inputsManager.getRulesString(), currentLevel.getX(), currentLevel.getY());
        nbWon = 0;
    }


    public void resetLevel() {
        inputsManager.resetRules();
        resetWorld();
    }

    public void startNewLevel() {
        if (currentLevel == WriteLevel.values()[0]) {
            view.showTutorial();
        }

        // Strategy ?
        if (currentLevel instanceof WriteLevel) {
            inputsManager.resetInputs(((WriteLevel)currentLevel).getColors(), ((WriteLevel)currentLevel).isPrevAuthorised());
                    inputsManager.lockRules(currentLevel);
            inputsManager.resetRules();
        } else {
            inputsManager.resetInputs();
            //resetRules(((ReadLevel)currentLevel).getRules());
        }

        resetWorld();

        isAnimPlaying = false;
    }

    public void render(float deltaTime) {

        // Inputs
        view.disableSubmit(!inputsManager.checkRules());

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
