package com.bob.game.world;

public interface InstructionStrategy {
    void update();
    EntityState getState();
}
