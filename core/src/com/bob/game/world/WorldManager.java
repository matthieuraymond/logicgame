package com.bob.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bob.game.Level;

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

    public WorldManager() {
        batch = new SpriteBatch();
        isAnimPlaying = false;
        speedFactor = 2f;
        nbWon = 0;
        bob = new Entity(0, 0);
    }

    public void setupWorld(Level level) {
        mapManager = new MapManager(level.getMap());
        resetBob(level.getX(), level.getY());
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
}
