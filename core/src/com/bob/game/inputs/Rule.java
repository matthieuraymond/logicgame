package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Rule {

    private static int startingY = 1080 - 495;
    private Image light;
    private RuleCell[] cells;
    private Drawable greenLight;
    private Drawable redLight;

    public Rule(Group group, Skin skin) {
        greenLight = skin.getDrawable("green_light");
        redLight = skin.getDrawable("red_light");

        light = new Image(greenLight);
        light.setBounds(1450, startingY, light.getWidth(), light.getHeight());
        group.addActor(light);

        cells = new RuleCell[7];
        for (int i=0; i < 7; ++i) {
            cells[i] = new RuleCell(group, startingY, i, skin);
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

        boolean isValid = Type.isValid(types);

        setLightOn(isValid);

        return isValid;
    }

    public void setLightOn(boolean isOn) {
        if (isOn) {
            light.setDrawable(greenLight);
        } else {
            light.setDrawable(redLight);
        }
    }

    public void reset() {
        for (RuleCell cell : cells) {
            cell.reset();
        }
    }
}
