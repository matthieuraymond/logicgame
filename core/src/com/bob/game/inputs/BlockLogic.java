package com.bob.game.inputs;

public class BlockLogic {
    private String LPSString;
    private String imageName;
    private Type type;

    public BlockLogic(String lps, String image, Type type) {
        this.LPSString = lps;
        this.imageName = image;
        this.type = type;
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
}
