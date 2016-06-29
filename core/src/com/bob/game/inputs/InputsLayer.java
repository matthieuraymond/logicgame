package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.bob.game.Layer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputsLayer extends Layer {

    private final List<DragAndDrop.Target> targets;
    private final List<InputView> inputViews;
    private Group macroGroup;
    private Skin skin;

    private InputsLayer(){
        targets = new ArrayList<>();
        inputViews = new ArrayList<>();
    }

    public InputsLayer(Skin skin) {
        this();
        this.skin = skin;

        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        // Inputs
        for (Block b: Block.values()) {
            skin.add(b.getImageName(), new Texture("resources/blocks/"+ b.getImageName() +".png"));
        }

        // Rules
        skin.add("red_light", new Texture("resources/lights/red.png"));
        skin.add("green_light", new Texture("resources/lights/green.png"));
        skin.add("target", new Texture("resources/blocks/target.png"));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        // DRAG N DROP
        TextTooltip.TextTooltipStyle tooltipStyle = new TextTooltip.TextTooltipStyle();
        skin.add("tooltip_bkg", new Texture("resources/blocks/tooltip.png"));
        tooltipStyle.label = labelStyle;

        tooltipStyle.background = skin.getDrawable("tooltip_bkg");

        skin.add("tooltipStyle", tooltipStyle);
    }

    public List<DragAndDrop.Target> getTargets() {
        return targets;
    }

    public void initRuleTargets(Rule rule) {
        DragAndDrop.Target[] rules_targets = rule.getTargets();
        Collections.addAll(targets, rules_targets);
    }

    public void createInput(Block block) {
        InputView inputView = new InputView(group, skin, block);

        for (DragAndDrop.Target t: targets) {
            inputView.addTarget(t);
        }

        inputViews.add(inputView);
    }

    public void clearInputs() {
        for (InputView i: inputViews) {
            i.clear();
        }
        inputViews.clear();
    }

    public Skin getSkin() {
        return skin;
    }

    public void setUpMacroPanel() {
        macroGroup = new Group();
        addActor(macroGroup);

        Image topCache = new Image(new Texture("resources/screens/macro_top.png"));
        topCache.setBounds(1400, 720, 500, 310);
        macroGroup.addActor(topCache);

        Image botCache = new Image(new Texture("resources/screens/macro_bot.png"));
        botCache.setBounds(1400, 80, 500, 615);
        macroGroup.addActor(botCache);

        TextButton button = new TextButton("+", skin, "green_button");
        button.setBounds(1840, 970, 50, 50);

        macroGroup.addActor(button);
    }

    public void deleteMacroPanel() {
        if (macroGroup != null) {
            macroGroup.clear();
        }
    }
}