package com.lps.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import java.util.*;

public class PlayInterface {

    private final Group backgroundGroup = new Group();
    private final Group levelUIGroup = new Group();
    private final Group winningGroup = new Group();

    SpriteBatch batch;
    Entity bob;

    Button submitButton;
    Label text;

    // Map and LPS
    MapManager mapManager;
    LPSHandler lpsHandler;
    HashMap<String, Input> inputs;

    static ArrayList<DragAndDrop.Target> targets;
    Rule[] rules;
    Image[] locking;


    private float roundTime;
    private float roundDuration;
    private int wonRound;
    private boolean isVisible;
    private boolean isAnimPlaying;
    private Level currentLevel;
    private Skin skin;
    private Tutorial tutorial;


    public PlayInterface(Skin skin) {
        initInterface(skin);
        hide();
        isAnimPlaying = false;
        currentLevel = Level.level1;
        wonRound = 0;
        tutorial = new Tutorial(skin);
    }

    private void initInterface(Skin skin) {

        rules = new Rule[8];
        targets = new ArrayList<>();
        locking = new Image[rules.length];
        inputs = new HashMap<>();
        batch = new SpriteBatch();


        // TODO REMOVE
        this.skin = skin;

        // Bkg
        Image foreground = new Image(new Texture("screens/foreground.png"));
        backgroundGroup.addActor(foreground);

        // Thumbs
        Image currentThumb = new Image(new Texture("thumbs/bob.png"));
        currentThumb.setBounds(25, 1080 - 148, currentThumb.getWidth(), currentThumb.getHeight());
        backgroundGroup.addActor(currentThumb);

        // Rules
        skin.add("red_light", new Texture("lights/red.png"));
        skin.add("green_light", new Texture("lights/green.png"));
        skin.add("target", new Texture("inputs/target.png"));

        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule(levelUIGroup, skin);
            DragAndDrop.Target[] rules_targets = rules[i].getTargets();

            Collections.addAll(targets, rules_targets);

            locking[i] = new Image(new Texture("inputs/locked.png"));
            locking[i].setBounds(1400, 573 - i * 70, 500, 70);
            levelUIGroup.addActor(locking[i]);
        }

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();


        // DRAG N DROP
        TextTooltip.TextTooltipStyle tooltipStyle = new TextTooltip.TextTooltipStyle();
        skin.add("tooltip_bkg", new Texture("inputs/tooltip.png"));
        tooltipStyle.label = labelStyle;

        tooltipStyle.background = skin.getDrawable("tooltip_bkg");

        skin.add("tooltipStyle", tooltipStyle);

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

        // Label
        text = new Label("", labelStyle);
        text.setBounds(240, 945, 575, 125);
        levelUIGroup.addActor(text);

        // Buttons

        // QUIT BUTTON
        skin.add("menu", new Texture("buttons/menu.png"));
        skin.add("menu_clicked", new Texture("buttons/menu_clicked.png"));

        Button.ButtonStyle quitStyle = new Button.ButtonStyle();
        quitStyle.up = skin.getDrawable("menu");
        quitStyle.down = skin.getDrawable("menu_clicked");

        Button quitButton = new Button(quitStyle);
        quitButton.setBounds(10, 15, 120, 50);
        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                hide();
            }
        });

        levelUIGroup.addActor(quitButton);

        // ----------
        // SUBMIT BUTTON
        skin.add("submit", new Texture("buttons/submit.png"));
        skin.add("submit_clicked", new Texture("buttons/submit_clicked.png"));
        skin.add("submit_disabled", new Texture("buttons/submit_disabled.png"));

        final Button.ButtonStyle submitStyle = new Button.ButtonStyle();
        submitStyle.disabled = skin.getDrawable("submit_disabled");
        submitStyle.up = skin.getDrawable("submit");
        submitStyle.down = skin.getDrawable("submit_clicked");

        submitButton = new Button(submitStyle);
        submitButton.setBounds(1700, 10, 200, 60);
        submitButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                if (!submitButton.isDisabled()) {
                    resetWorld();
                    isAnimPlaying = true;
                }
            }
        });

        levelUIGroup.addActor(submitButton);

        // ----------
        // RESET BUTTON
        skin.add("reset", new Texture("buttons/reset.png"));
        skin.add("reset_clicked", new Texture("buttons/reset_clicked.png"));
        final Button.ButtonStyle resetStyle = new Button.ButtonStyle();
        resetStyle.up = skin.getDrawable("reset");
        resetStyle.down = skin.getDrawable("reset_clicked");
        Button resetButton = new Button(resetStyle);
        resetButton.setBounds(1450, 10, 200, 60);
        resetButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                resetRules();
            }
        });

        levelUIGroup.addActor(resetButton);
        // ----------

        // SLIDER
        skin.add("slider_bkg", new Texture("buttons/slider_bkg.png"));
        skin.add("slider_knob", new Texture("buttons/slider_knob.png"));
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("slider_knob");
        sliderStyle.background = skin.getDrawable("slider_bkg");
        final Slider slider = new Slider(0, 1, 0.01f, false, sliderStyle);
        slider.setBounds(150, 30, 200, 25);

        slider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                float previousTime = roundTime/roundDuration; // to avoid jump
                roundDuration = 1.2f - slider.getValue();
                roundTime = previousTime * roundDuration;
            }
        });
        slider.setValue(0.7f);

        roundDuration = 1.2f - slider.getValue();
        levelUIGroup.addActor(slider);
        // -------


        // Winning screen
        winningGroup.addActor(new Image(new Texture("screens/winning.png")));

        skin.add("next", new Texture("buttons/next.png"));
        skin.add("next_clicked", new Texture("buttons/next_clicked.png"));
        final Button.ButtonStyle nextStyle = new Button.ButtonStyle();
        nextStyle.up = skin.getDrawable("next");
        nextStyle.down = skin.getDrawable("next_clicked");
        Button nextButton = new Button(nextStyle);
        nextButton.setBounds(860, 400, 200, 60);
        nextButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                currentLevel = currentLevel.next();
                startNewLevel();
                showWinningScreen(false);
            }
        });

        winningGroup.addActor(nextButton);

        showWinningScreen(false);
    }

    public void show() {
        backgroundGroup.setVisible(true);
        levelUIGroup.setVisible(true);
        winningGroup.setVisible(false);
        isVisible = true;
    }

    public void hide() {
        backgroundGroup.setVisible(false);
        levelUIGroup.setVisible(false);
        winningGroup.setVisible(false);
        isVisible = false;
    }

    public void setStage(Stage stage) {
        stage.addActor(backgroundGroup);
        stage.addActor(levelUIGroup);
        stage.addActor(winningGroup);
        tutorial.setStage(stage);
    }


    private void checkRules() {
        boolean allValid = true;

        for (Rule rule : rules) {
            allValid &= (rule.isValid(skin));
        }

        if (!allValid) {
            submitButton.setDisabled(true);
        } else {
            submitButton.setDisabled(false);
        }
    }


    private void addInput(String lps, Type type, String name, String tooltip) {
        Input input = new Input(levelUIGroup, skin, lps, name, type, tooltip);
        for (DragAndDrop.Target t: targets) {
            input.addTarget(t);
        }

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
        bob = new Entity(mapManager, currentLevel.getX(), currentLevel.getY());

        StringBuilder inputs = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            inputs.append(rules[i].getString());

            if (i >= currentLevel.getNoRules()) {
                locking[i].setVisible(true);
            } else {
                locking[i].setVisible(false);
            }
        }

        text.setText(currentLevel.getText());

        lpsHandler = new LPSHandler(mapManager, inputs.toString(), currentLevel.getX(), currentLevel.getY());
        roundTime = 0;
        isAnimPlaying = false;
    }

    public void startNewLevel() {
        if (currentLevel == Level.values()[0]) {
            tutorial.show();
        }


        resetWorld();
        resetInputs();
        resetRules();
    }

    public void drawWorld(float deltaTime) {

        roundTime += deltaTime;
        boolean endOfRound = (roundTime >= roundDuration);

        // UPDATE OF ENTITY
        if (endOfRound) {
            updateEntity();
            updateGameState();
        }


        // Update of rules
        checkRules();

        //Map
        if (mapManager != null) mapManager.draw(isAnimPlaying ? deltaTime : 0);

        // Batch
        batch.begin();
        if (bob != null) bob.draw(batch, (isAnimPlaying? deltaTime : 0), roundDuration);
        batch.end();

        if (endOfRound) roundTime = 0;
    }

    private void updateEntity() {
        lpsHandler.update();
        bob.updateState(lpsHandler.getNewState());
    }

    private void updateGameState() {

        if (bob.checkIfWet()) {
            isAnimPlaying = false;
        }

        if (bob.chekIfWon()) {
            wonRound++;
            if (wonRound > 2) {
                wonRound = 0;
                showWinningScreen(true);
            }
        }
    }

    private void showWinningScreen(boolean visible) {
        winningGroup.setVisible(visible);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }
}
