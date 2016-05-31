package com.lps.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import java.util.*;

public class PlayController {

    SpriteBatch batch;
    Entity bob;

    // Map and LPS
    MapManager mapManager;
    LPSHandler lpsHandler;
    HashMap<String, Input> inputs;
    private boolean isAnimPlaying = false;

    Rule[] rules;
    Image[] locking;
    private float timer;

    private Level currentLevel;
    private PlayView view;
    private float speedFactor = 2f;
    private int nbWon = 0;

    public PlayController(Skin skin) {

        rules = new Rule[8];
        locking = new Image[rules.length];
        inputs = new HashMap<>();

        view = new PlayView();
        view.initInterface(skin, this);
        view.hide();
        currentLevel = Level.level1;
        batch = new SpriteBatch();

        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        skin.add("imply", new Texture("inputs/imply.png"));
        skin.add("and", new Texture("inputs/and.png"));
        skin.add("not", new Texture("inputs/not.png"));

        String[] colors = {"red", "orange", "yellow", "green", "purple", "white"};
        String[] directions = {"Left", "Right", "Up", "Down"};

        for (String color : colors) {
            skin.add(color, new Texture("inputs/" + color + ".png"));
            skin.add(color + "_prev", new Texture("inputs/" + color + "_prev.png"));
        }
        for (String direction : directions) {
            skin.add(direction, new Texture("inputs/" + direction.toLowerCase() + ".png"));
        }

        for (String direction : directions) {
            addInput("go" + direction + "()", Type.CONSEQUENT, direction, "Bob should go " + direction);
        }

        addInput("&", Type.AND,  "and", "AND, to be used in: if a AND b");
        addInput("->", Type.IMPLY,  "imply", "IMPLY/THEN, to be used in: if a THEN b");
        addInput("!", Type.NOT,  "not", "NOT, to be used in: NOT a");

    }

    private boolean checkRules() {
        boolean allValid = true;

        for (Rule rule : rules) {
            allValid &= (rule.isValid());
        }

        return allValid;
    }


    private void addInput(String lps, Type type, String name, String tooltip) {
        Input input = view.createInput(lps, name, type, tooltip);

        inputs.put(name, input);
    }

    public void resetRules() {
        for (Rule r: rules) {
            r.reset();
        }
    }

    public void resetInputs() {

        Iterator<Map.Entry<String,Input>> it = inputs.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String,Input> e = it.next();
            if (e.getValue().isFluent()) {
                e.getValue().clear();
                it.remove();
            }

        }

        addInputs(currentLevel.getColors(), currentLevel.isPrevAuthorised());
    }

    private void addInputs(String[] colors, boolean prevAuthorised) {
        for (String color : colors) {
            addInput(color + "(X,Y)", Type.FLUENT, color, "If Bob is on a " + color + " cell");
            if (prevAuthorised) {
                addInput(color + "(U,V)", Type.FLUENT, color + "_prev", "If Bob was previously on a " + color + " cell");
            }
        }
    }


    public void resetWorld() {

        mapManager = new MapManager(currentLevel.getMap());

        bob = new Entity(currentLevel.getX(), currentLevel.getY());

        StringBuilder inputs = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            inputs.append(rules[i].getString());

            if (i >= currentLevel.getNoRules()) {
                locking[i].setVisible(true);
            } else {
                locking[i].setVisible(false);
            }
        }

        view.changeText(currentLevel.getText());

        lpsHandler = new LPSHandler(mapManager, inputs.toString(), currentLevel.getX(), currentLevel.getY());
        nbWon = 0;
    }


    public void resetLevel() {
        resetRules();
        resetWorld();
    }

    public void startNewLevel() {
        if (currentLevel == Level.values()[0]) {
            view.showTutorial();
        }

        resetInputs();
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

    public void setLevel(Level level) {
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
