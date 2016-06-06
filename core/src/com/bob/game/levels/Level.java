package com.bob.game.levels;

public interface Level {
    String getMap();

    int getX();

    int getY();

    String getText();

    Level next();
}
