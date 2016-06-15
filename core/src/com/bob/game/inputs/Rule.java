package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.bob.game.Layer;

public class Rule {

    private static int startingY = 1080 - 495;
    private Image light;
    private Image lock;
    private RuleCell[] cells;
    private Drawable greenLight;
    private Drawable redLight;

    public Rule() {
        cells = new RuleCell[7];
        for (int i=0; i < cells.length; ++i) {
            cells[i] = new RuleCell();
        }
    }

    public void initView(InputsLayer layer){
        Skin skin = layer.getSkin();

        greenLight = skin.getDrawable("green_light");
        redLight = skin.getDrawable("red_light");

        light = new Image(greenLight);
        light.setBounds(1450, startingY, light.getWidth(), light.getHeight());
        layer.addActor(light);

        lock = new Image(new Texture("blocks/locked.png"));
        lock.setBounds(1400, startingY - 12, 500, 70);
        lock.setVisible(false);
        layer.addActor(lock);


        for (int i=0; i < cells.length; ++i) {
            cells[i].initView(layer, startingY, i, skin);
        }

        startingY -= 70;
    }

    public DragAndDrop.Target[] getTargets() {
        DragAndDrop.Target[] targets = new DragAndDrop.Target[cells.length];

        for(int i = 0; i < cells.length; ++i) {
            targets[i] = cells[i].getTarget();
        }

        return targets;
    }

    public String getString() {

        StringBuilder sb = new StringBuilder("isIn(X,Y) & wasIn(U,V) & ");
        boolean notEmpty = false;

        for (RuleCell c: cells) {
            if (!c.getLPSString().isEmpty()) {
                sb.append(c.getLPSString());
                notEmpty = true;
            }
        }

        sb.append(".");

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
            cells[i].setBlock(newRule[i]);
        }
    }

    public void toggleLights() { //Duplication of verification, could be cached if needed
        light.setDrawable(isValid() ? greenLight : redLight);
    }

    public void lock() {
        lock.setVisible(true);
    }
}
