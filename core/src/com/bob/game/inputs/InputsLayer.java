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
import com.bob.main.TextureFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputsLayer extends Layer {

    protected final List<DragAndDrop.Target> targets;
    private final List<Draggable> draggables;
    protected Skin skin;

    public InputsLayer(){
        initialVisibility = false;
        targets = new ArrayList<>();
        draggables = new ArrayList<>();
    }

    public InputsLayer(Skin skin) {
        this();
        this.skin = skin;

        BitmapFont font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        skin.add("default", new Label.LabelStyle(font, Color.WHITE));

        // Inputs
        for (Block b: Block.values()) {
            skin.add(b.getImageName(), TextureFactory.createTexture("blocks/"+ b.getImageName() +".png"));
        }
        skin.add("macro_block", TextureFactory.createTexture("blocks/macro.png"));

        // Rules
        skin.add("red_light", TextureFactory.createTexture("lights/red.png"));
        skin.add("green_light", TextureFactory.createTexture("lights/green.png"));
        skin.add("target", TextureFactory.createTexture("blocks/target.png"));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        // DRAG N DROP
        TextTooltip.TextTooltipStyle tooltipStyle = new TextTooltip.TextTooltipStyle();
        skin.add("tooltip_bkg", TextureFactory.createTexture("blocks/tooltip.png"));
        tooltipStyle.label = labelStyle;

        tooltipStyle.background = skin.getDrawable("tooltip_bkg");

        skin.add("tooltipStyle", tooltipStyle);
    }

    public List<DragAndDrop.Target> getTargets() {
        return targets;
    }

    public void addTargets(DragAndDrop.Target[] targets) {
        Collections.addAll(this.targets, targets);
    }

    public void addTarget(DragAndDrop.Target target) {
        this.targets.add(target);
    }

    public void createInput(Block block, int refX, int refY) {

        Image dragImage = new Image(skin, block.getImageName());
        Image draggingImage = new Image(skin, block.getImageName());
        dragImage.setBounds(refX, refY, 50, 50);

        Draggable draggable = new Draggable(this, dragImage, draggingImage, block);
        draggable.setTooltip(skin, block.getTooltip());

        for (DragAndDrop.Target t: targets) {
            draggable.addTarget(t);
        }

        draggables.add(draggable);
    }

    public void clearInputs() {
        for (Draggable i: draggables) {
            i.clear();
        }
        draggables.clear();
    }

    public Skin getSkin() {
        return skin;
    }
}