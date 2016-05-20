package com.lps.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class Rule {

    private static int startingY = 1080 - 495;
    private Image light;
    private RuleCell[] cells;

    public Rule(Group group, Skin skin) {
        light = new Image(skin.getDrawable("green_light"));
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

    public boolean isValid(Skin skin) {
        Type[] types = new Type[cells.length];

        for(int i=0; i < cells.length; i++) {
            types[i] = cells[i].getType();
        }

        boolean isValid = Type.isValid(types);
        if (isValid) {
            light.setDrawable(skin, "green_light");
        } else {
            light.setDrawable(skin, "red_light");
        }

        return isValid;
    }

    public void reset() {
        for(int i=0; i < cells.length; i++) {
            cells[i].reset();
        }
    }
}
