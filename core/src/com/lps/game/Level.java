package com.lps.game;

public interface Level {
    String getMap();

    int getX();

    int getY();

    String getText();

    Level next();

    int getNoRules();
}
