package com.bob.game.inputs;

public class BlockCoordinatesGenerator {
    private final int refXFluent;
    private final int refXLogic;
    private final int refXConsequent;
    private final int refY;
    private int noFluent = 0;
    private int noLogic = 0;
    private int noConsequent = 0;

    public BlockCoordinatesGenerator(int refX, int refY) {
        this.refXFluent = refX;
        this.refXLogic = refXFluent + 270;
        this.refXConsequent = refXLogic + 90;
        this.refY = refY;
    }

    public int[] getCoordinates(Type type) {
        int[] res = new int[2];
        if (type == Type.FLUENT) {
            res[0] = refXFluent + (noFluent % 4) * 60;
            res[1] = refY - (noFluent / 4) * 60;

            noFluent++;
        } else if (type == Type.CONSEQUENT) {
            res[0] = refXConsequent + (noConsequent % 2) * 60;
            res[1] = refY - (noConsequent / 2) * 60;

            noConsequent++;
        } else {
            res[0] = refXLogic;
            res[1] = refY - noLogic * 60;

            noLogic++;
        }

        return res;
    }
}
