package com.bob.game.world;

import com.bob.game.inputs.Block;

import java.util.LinkedList;

public class MockLPSHandler extends LPSHandler {

    private final LinkedList<Block> blockStack;

    public MockLPSHandler(LinkedList<Block> blockStack) {
        this.blockStack = blockStack;
    }

    @Override
    public void update() {
        // Do nothing as it is a mock version
    }

    @Override
    public EntityState getNewState() {

        if (blockStack.isEmpty()) {
            return null;
        }

        Block b = blockStack.removeFirst();

        if (b == Block.LEFT) {
            return EntityState.WALK_LEFT;
        }

        if (b == Block.RIGHT) {
            return EntityState.WALK_RIGHT;
        }

        if (b == Block.UP) {
            return EntityState.WALK_UP;
        }

        if (b == Block.DOWN) {
            return EntityState.WALK_DOWN;
        }

        return null;
    }
}
