package com.bob.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bob.game.levels.Level;

import java.util.LinkedList;
import java.util.List;

public class WorldManager {

    //view
    private boolean isAnimPlaying;
    private float speedFactor;
    private int nbWon;

    // Bob
    SpriteBatch batch;
    Entity bob;

    // Map and LPS
    MapManager mapManager;
    LPSHandler lpsHandler;

    // Golden cells
    Stage stage;
    List<ClickListener> goldListener;

    public WorldManager() {
        batch = new SpriteBatch();
        goldListener = new LinkedList<>();
        isAnimPlaying = false;
        speedFactor = 2f;
        nbWon = 0;
        bob = new Entity(0, 0);
    }

    public void setupWorld(Level level) {
        mapManager = new MapManager(level.getMap());
        setGoldListeners();
        resetBob(level.getX(), level.getY());
    }

    private void setGoldListeners() {
        clearListener();
        List<WorldCoordinates> questions = mapManager.getQuestionCoordinates();

        for (WorldCoordinates coord: questions) {
            addQuestionListener(coord);
        }

    }

    private void clearListener() {
        for (ClickListener listener : goldListener) {
            stage.removeListener(listener);
        }
    }

    public void resetBob(float x, float y) {
        nbWon = 0;
        bob.setPosition(x, y);
        isAnimPlaying = false;
    }

    public void startAnimation(Level level, String rules) {
        lpsHandler = new LPSHandler(mapManager, rules, level.getX(), level.getY());
        isAnimPlaying = true;
    }

    public void render(float deltaTime) {
        // UPDATE OF ENTITY
        bob.increaseTime(isAnimPlaying ? deltaTime * speedFactor : 0);

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

        // Adds delay to show winning screen
        if (mapManager.chekIfWon(coord)) {
            bob.updateState(EntityState.WON);
            nbWon++;
        }
    }

    public boolean isLevelWon() {
        return nbWon > 2;
    }

    public void updateSpeed(float newValue) {
        speedFactor = newValue;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addQuestionListener(final WorldCoordinates coord) {

        ClickListener listener = new ClickListener(Input.Buttons.LEFT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Math.abs(x - coord.getScreenX()) < 50 && Math.abs(y - coord.getScreenY()) < 30) {
                    Gdx.app.exit();
                }
            }
        };

        goldListener.add(listener);
        stage.addListener(listener);
    }
}
