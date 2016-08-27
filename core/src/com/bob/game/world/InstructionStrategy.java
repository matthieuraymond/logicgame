package com.bob.game.world;

import java.util.Collection;

public interface InstructionStrategy {
    void update();
    EntityState getState();
    Collection<Integer> getAppliedRuleIndexes();
}
