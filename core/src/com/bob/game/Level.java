package com.bob.game;

public interface Level {
    String getMap();

    int getX();

    int getY();

    String getText();

    Level next();
}
