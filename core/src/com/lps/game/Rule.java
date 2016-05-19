package com.lps.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class Rule {

    private static final Texture greenLight = new Texture("lights/green.png");
    private static final Texture redLight = new Texture("lights/red.png");
    private static int startingY = 1080 - 495;
    private RuleCell[] cells;

    public Rule(Stage stage, Skin skin) {
        cells = new RuleCell[7];
        for (int i=0; i < 7; ++i) {
            cells[i] = new RuleCell(stage, startingY, i, skin);
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

    public void drawLight(SpriteBatch batch, int i) {
        batch.draw(isValid() ? greenLight : redLight, 1450, 27 + (8 - i) * 70);
    }

    public boolean isValid() {
        Type[] types = new Type[cells.length];

        for(int i=0; i < cells.length; i++) {
            types[i] = cells[i].getType();
        }

        return Type.isValid(types);
    }

    public void reset() {
        for(int i=0; i < cells.length; i++) {
            cells[i].reset();
        }
    }
}
