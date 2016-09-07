package com.bob.game.inputs;

public enum Block {

    // Colors
    WHITE("white(X,Y)", "white", Type.FLUENT, "If Bob is on a white cell"),
    RED("red(X,Y)", "red", Type.FLUENT, "If Bob is on a red cell"),
    YELLOW("yellow(X,Y)", "yellow", Type.FLUENT, "If Bob is on a yellow cell"),
    GREEN("green(X,Y)", "green", Type.FLUENT, "If Bob is on a green cell"),
    ORANGE("orange(X,Y)", "orange", Type.FLUENT, "If Bob is on a orange cell"),
    PURPLE("purple(X,Y)", "purple", Type.FLUENT, "If Bob is on a purple cell"),

    //Colors PREV
    WHITE_PREV("white(U,V)", "white_prev", Type.FLUENT, "If Bob was previously on a white cell"),
    RED_PREV("red(U,V)", "red_prev", Type.FLUENT, "If Bob was previously on a red cell"),
    YELLOW_PREV("yellow(U,V)", "yellow_prev", Type.FLUENT, "If Bob was previously on a yellow cell"),
    GREEN_PREV("green(U,V)", "green_prev", Type.FLUENT, "If Bob was previously on a green cell"),
    ORANGE_PREV("orange(U,V)", "orange_prev", Type.FLUENT, "If Bob was previously on a orange cell"),
    PURPLE_PREV("purple(U,V)", "purple_prev", Type.FLUENT, "If Bob was previously on a purple cell"),

    // Directions
    LEFT("goLeft", "left", Type.CONSEQUENT, "Bob should go West"),
    RIGHT("goRight", "right", Type.CONSEQUENT, "Bob should go East"),
    UP("goUp", "up", Type.CONSEQUENT, "Bob should go North"),
    DOWN("goDown", "down", Type.CONSEQUENT, "Bob should go South"),

    // Other instructions
    WAIT("wait", "pause", Type.CONSEQUENT, "Bob should wait"),

    // Connectors
    AND("&", "and", Type.AND, "AND, to be used in: if a AND b"),
    IMPLY(" -> ", "imply", Type.IMPLY, "IMPLY/THEN, to be used in: if a THEN b"),
    NOT("!", "not", Type.NOT, "NOT, to be used in: NOT a");

    private final String LPSString;
    private final String imageName;
    private final Type type;
    private final String tooltip;

    Block(String lps, String image, Type type, String tooltip) {
        this.LPSString = lps;
        this.imageName = image;
        this.type = type;
        this.tooltip = tooltip;
    }

    public String getLPSString() {
        return LPSString;
    }

    public String getImageName() {
        return imageName;
    }

    public Type getType() {
        return type;
    }

    public String getTooltip() {
        return tooltip;
    }

    public static Block getBlock(String name) {
        for (Block b: Block.values()) {
            if (b.getImageName().equals(name)) return b;
        }
        return null;
    }
}
