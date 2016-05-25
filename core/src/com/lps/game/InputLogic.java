package com.lps.game;

public class InputLogic {
    private String LPSString;
    private String imageName;
    private Type type;


    public InputLogic(String lps, String image, Type type) {
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
