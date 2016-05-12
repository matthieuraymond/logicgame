package com.lps.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class Rule {
    private static int startingY = 1080 - 445;
    private RuleCell[] cells;

    public Rule(Stage stage, Skin skin) {
        cells = new RuleCell[8];
        for (int i=0; i < 8; ++i) {
            cells[i] = new RuleCell(stage, startingY, i, skin);
        }

        startingY -= 60;
    }

    public DragAndDrop.Target[] getTargets() {
        DragAndDrop.Target[] targets = new DragAndDrop.Target[cells.length];

        for(int i = 0; i < cells.length; ++i) {
            targets[i] = cells[i].getTarget();
        }

        return targets;
    }

    public String getString() {

        StringBuilder sb = new StringBuilder();
        boolean notEmpty = false;

        for (RuleCell c: cells) {
            if (!c.getLPSString().isEmpty()) {
                sb.append(" " + c.getLPSString());
                notEmpty = true;
            }
        }

        sb.append(".");

        return notEmpty ? sb.toString() : "";
    }
}
