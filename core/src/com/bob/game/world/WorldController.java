package com.bob.game.world;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bob.game.inputs.Block;
import com.bob.game.levels.Level;
import com.bob.lps.model.Goal;
import com.bob.lps.model.GoalsList;

import java.util.*;

public class WorldController {

    //view
    private boolean isAnimPlaying;
    private float speedFactor;
    private int nbWon;

    // Bob
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private final Entity bob;

    // Objects
    private List<Entity> objects;

    // Map and LPS
    private MapManager mapManager;
    private LPSHandler lpsHandler;
    private int currentRuleIndex;

    // Golden cells
    private Stage stage;
    private final List<ClickListener> goldListener;

    public WorldController() {
        goldListener = new LinkedList<>();
        isAnimPlaying = false;
        speedFactor = 2f;
        nbWon = 0;
        currentRuleIndex = -1;
        bob = new Entity(0, 0);
        objects = new ArrayList<Entity>();
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setupWorld(Level level) {
        mapManager = new MapManager(level.getMap());
        resetStage(level.getX(), level.getY());
    }

    public void initRender() {
        mapManager.initRender();
        setGoldListeners();
        batch = new SpriteBatch();
    }

    private void setGoldListeners() {
        clearListener();
        List<WorldCoordinates> questions = mapManager.getCoordinatesList("Floor", "question");

        for (WorldCoordinates coord: questions) {
            addQuestionListener(coord);
        }

    }

    private void clearListener() {
        for (ClickListener listener : goldListener) {
            stage.removeListener(listener);
        }
    }

    public void resetLights() {
        List<WorldCoordinates> lights = mapManager.getCoordinatesList("Objects", "light_bulb");
        objects = new ArrayList<>();

        for (WorldCoordinates l: lights) {
            Entity light = new Entity(l.getWorldX(), l.getWorldY());
            light.updateState(EntityState.LIGHT);

            objects.add(light);
        }

    }

    public void resetBob(float x, float y) {
        nbWon = 0;
        currentRuleIndex = -1;
        bob.setPosition(x, y);
        isAnimPlaying = false;
    }

    public void resetStage(float x, float y) {
        resetBob(x, y);
        resetLights();
        currentRuleIndex = -1;
    }

    public void startLPSAnimation(Level level, String rules) {
        lpsHandler = new LPSHandler(mapManager.getLPSDescription(), mapManager.getLightsString(), rules, level.getX(), level.getY());
        isAnimPlaying = true;
    }

    public void startMockAnimation(LinkedList<Block> blockStack) {
        lpsHandler = new MockLPSHandler(blockStack);
        isAnimPlaying = true;
    }

    public void render(float deltaTime) {
        float deltaTimeAdjusted = isAnimPlaying ? deltaTime * speedFactor: 0;

        // UPDATE OF ENTITY
        updateWorld(deltaTimeAdjusted);

        //Map
        if (mapManager != null) mapManager.draw(deltaTimeAdjusted);

        // Batch
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (bob != null) bob.draw(batch);


        for (Entity object: objects) {
            object.increaseTime(deltaTimeAdjusted);
            object.draw(batch);
        }


        batch.end();
    }

    public void updateWorld(float deltaTimeAdjusted) {
        updateBob(deltaTimeAdjusted);
        cleanObjects();
    }

    private void cleanObjects() {
        List<Entity> toDelete = new LinkedList<>();
        for (Entity object: objects) {
            if (object.getCoord().collide(bob.getCoord())) {
                toDelete.add(object); // todo anim
            }
        }

        for (Entity o: toDelete) {
            objects.remove(o);
        }
    }

    public void updateBob(float deltaTime) {
        bob.increaseTime(deltaTime);

        if (bob.needInstructions()) {
            retrieveInstructions();
            updateGameState();
        }
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
        if (mapManager.chekIfWon(coord, objects.size())) {
            bob.updateState(EntityState.WON);
            nbWon++;
        }

        // Update current rule
        currentRuleIndex = -1;
        Set<Goal> set = GoalsList.getInstance().getActiveGoals();

        Iterator<Goal> it = set.iterator();

        while (it.hasNext()) {
            Goal g = it.next();
            switch (g.getGoal().getTerm(0).toString()) {
                case "goRight":
                case "goLeft":
                case "goUp":
                case "goDown":
                case "wait":
                    currentRuleIndex = Integer.parseInt(g.getGoal().getTerm(1).getName());
                    break;
            }
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

    private void addQuestionListener(final WorldCoordinates coord) {

        ClickListener listener = new ClickListener(Input.Buttons.LEFT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Math.abs(x - coord.getScreenX()) < 50 && Math.abs(y - coord.getScreenY()) < 30) {
                    if (!isAnimPlaying) {
                        mapManager.setGold((int) coord.getWorldX(), (int) coord.getWorldY());
                    }
                }
            }
        };

        goldListener.add(listener);
        stage.addListener(listener);
    }

    public int getCurrentRuleIndex() {
        return currentRuleIndex;
    }

    public int getNoObjects() {
        return objects.size();
    }

    public int getMaxObjects() {
        return mapManager.getCoordinatesList("Objects", "light_bulb").size();
    }

    public boolean isBobConfused() {
        return bob.isConfused() && isAnimPlaying;
    }
}
