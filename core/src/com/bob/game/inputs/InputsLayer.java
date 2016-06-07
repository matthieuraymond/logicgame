package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import com.bob.game.Layer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputsLayer extends Layer {

    private List<DragAndDrop.Target> targets;
    private List<InputView> inputViews;
    private Image[] locking;
    private Skin skin;

    public InputsLayer(Skin skin) {
        targets = new ArrayList<>();
        inputViews = new ArrayList<>();

        this.skin = skin;

        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        // Inputs
        for (Block b: Block.values()) {
            skin.add(b.getImageName(), new Texture("blocks/"+ b.getImageName() +".png"));
        }

        // Rules
        skin.add("red_light", new Texture("lights/red.png"));
        skin.add("green_light", new Texture("lights/green.png"));
        skin.add("target", new Texture("blocks/target.png"));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        // DRAG N DROP
        TextTooltip.TextTooltipStyle tooltipStyle = new TextTooltip.TextTooltipStyle();
        skin.add("tooltip_bkg", new Texture("blocks/tooltip.png"));
        tooltipStyle.label = labelStyle;

        tooltipStyle.background = skin.getDrawable("tooltip_bkg");

        skin.add("tooltipStyle", tooltipStyle);
    }

    public List<DragAndDrop.Target> getTargets() {
        return targets;
    }

    public void initRules(Rule[] rules) {
        locking = new Image[rules.length];

        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule(this, skin);
            DragAndDrop.Target[] rules_targets = rules[i].getTargets();

            Collections.addAll(targets, rules_targets);

            locking[i] = new Image(new Texture("blocks/locked.png"));
            locking[i].setBounds(1400, 573 - i * 70, 500, 70);
            group.addActor(locking[i]);
        }
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

    public void lockRules(int noRules) {
        for (int i = 0; i < locking.length; ++i) {
            if (i >= noRules) {
                locking[i].setVisible(true);
            } else {
                locking[i].setVisible(false);
            }
        }
    }
}