package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.bob.main.TextureFactory;

import java.util.LinkedList;

public class Rule {

    private Image light;
    private Image lock;
    private Image arrow;
    private final RuleCell[] cells;
    private Drawable greenLight;
    private Drawable redLight;

    public Rule() {
        cells = new RuleCell[7];
        for (int i=0; i < cells.length; ++i) {
            cells[i] = new RuleCell();
        }
    }

    public void initView(InputsLayer layer, Skin skin, int startingX, int startingY){
        //Cells
        for (int i=0; i < cells.length; ++i) {
            Image bkgImage = new Image(skin, "target");
            bkgImage.setBounds(startingX + i * 60, startingY, 50, 50);

            cells[i].initView(layer, startingX + i * 60, startingY, bkgImage, skin);
        }

        // Red light, green light
        greenLight = skin.getDrawable("green_light");
        redLight = skin.getDrawable("red_light");

        light = new Image(greenLight);
        light.setBounds(startingX - 25, startingY, light.getWidth(), light.getHeight());
        layer.addActor(light);

        // Locking pane
        lock = new Image(TextureFactory.createTexture("blocks/locked.png"));
        lock.setBounds(startingX - 75, startingY - 12, 500, 70);
        lock.setVisible(false);
        layer.addActor(lock);

        // Arrow for current rule
        arrow = new Image(TextureFactory.createTexture("lights/arrow.png"));
        arrow.setBounds(startingX - 100, startingY - 2, 60, 45);
        arrow.setVisible(false);
        layer.addActor(arrow);
    }

    public DragAndDrop.Target[] getTargets() {
        DragAndDrop.Target[] targets = new DragAndDrop.Target[cells.length];

        for(int i = 0; i < cells.length; ++i) {
            targets[i] = cells[i].getTarget();
        }

        return targets;
    }

    public String getString(int ruleIndex) {
        return getString(ruleIndex, 0);
    }

    public String getString(int ruleIndex, int noLights) {

        StringBuilder sb = new StringBuilder("isIn(X,Y) & wasIn(U,V) & lights(" + noLights + ") & !lightBulb(X,Y) & ");
        boolean notEmpty = false;

        for (RuleCell c: cells) {
            if (!c.getLPSString().isEmpty()) {
                sb.append(c.getLPSString());
                notEmpty = true;
            }
        }

        sb.append("("+ruleIndex+").");

        return notEmpty ? sb.toString() : "";
    }

    public boolean isValid() {
        Type[] types = new Type[cells.length];

        for (int i = 0; i < cells.length; i++) {
            types[i] = cells[i].getType();
        }

        return Type.isValid(types);
    }

    public void reset() {
        for (RuleCell cell : cells) {
            cell.reset();
        }
    }

    public void setRuleBlocks(Block[] newRule) {
        for (int i = 0; i < newRule.length && i < cells.length; ++i) {
            cells[i].setPayload(newRule[i]);
        }
    }

    public void toggleLights() { //Duplication of verification, could be cached if needed
        light.setDrawable(isValid() ? greenLight : redLight);
    }

    public void lock(boolean isLocked) {
        lock.setVisible(isLocked);
    }

    public void displayImages() {
        for (RuleCell c: cells) {
            c.setImage(false);
        }
    }

    public void lightOn() {
        arrow.setVisible(true);
    }

    public void lightOff() {
        arrow.setVisible(false);
    }

    public boolean onlyConsequentUsed() {
        boolean res = true;
        for(RuleCell c: cells) {
            res &= (c.getType() == null) || (c.getType() == Type.CONSEQUENT);
        }

        return res;
    }

    public LinkedList<Block> getBlockStack() {
        LinkedList<Block> blockStack = new LinkedList<>();

        for (RuleCell c : cells) {
            Block b = c.getBlock();
            if (b != null) {
                blockStack.add(b);
            }
        }

        return blockStack;
    }
}
