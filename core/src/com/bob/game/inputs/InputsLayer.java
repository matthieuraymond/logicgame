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
import com.bob.game.inputs.Block;
import com.bob.game.inputs.Rule;
import com.bob.game.inputs.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputsLayer extends Layer {

    public static List<DragAndDrop.Target> targets;
    private List<Block> blocks;
    private Image[] locking;
    private Skin skin;

    public InputsLayer(Skin skin) {
        targets = new ArrayList<>();
        blocks = new ArrayList<>();

        this.skin = skin;

        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        skin.add("imply", new Texture("blocks/imply.png"));
        skin.add("and", new Texture("blocks/and.png"));
        skin.add("not", new Texture("blocks/not.png"));

        String[] colors = {"red", "orange", "yellow", "green", "purple", "white"};
        String[] directions = {"Left", "Right", "Up", "Down"};

        for (String color : colors) {
            skin.add(color, new Texture("blocks/" + color + ".png"));
            skin.add(color + "_prev", new Texture("blocks/" + color + "_prev.png"));
        }
        for (String direction : directions) {
            skin.add(direction, new Texture("blocks/" + direction.toLowerCase() + ".png"));
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

    public void initRules(Rule[] rules) {
        locking = new Image[rules.length];

        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule(group, skin);
            DragAndDrop.Target[] rules_targets = rules[i].getTargets();

            Collections.addAll(targets, rules_targets);

            locking[i] = new Image(new Texture("blocks/locked.png"));
            locking[i].setBounds(1400, 573 - i * 70, 500, 70);
            group.addActor(locking[i]);
        }
    }

    public void createInput(String lps, String name, Type type, String tooltip) {
        Block block = new Block(group, skin, lps, name, type, tooltip);

        for (DragAndDrop.Target t: targets) {
            block.addTarget(t);
        }

        blocks.add(block);
    }

    public void clearInputs() {
        for (Block i: blocks) {
            i.clear();
        }
        blocks.clear();
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
