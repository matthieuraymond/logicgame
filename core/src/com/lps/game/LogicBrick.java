package com.lps.game;

public class LogicBrick {
    private String LPSString;
    private String imageName;


    public LogicBrick(String lps, String image) {
        LPSString = lps;
        imageName = image;
    }

    public String getLPSString() {
        return LPSString;
    }

    public String getImageName() {
        return imageName;
    }
}
