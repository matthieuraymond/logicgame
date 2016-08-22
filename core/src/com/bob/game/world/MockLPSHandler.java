package com.bob.game.world;

import com.bob.game.inputs.Block;

import java.util.LinkedList;

public class MockLPSHandler implements InstructionStrategy {

    private final LinkedList<Block> blockStack;

    public MockLPSHandler(LinkedList<Block> blockStack) {
        blockStack.addFirst(null); // In JLPS first update is needed to start
        this.blockStack = blockStack;
    }

    @Override
    public void update() {
        if (!blockStack.isEmpty())
            blockStack.removeFirst();
    }

    @Override
    public EntityState getState() {

        if (blockStack.isEmpty()) {
            return null;
        }

        Block b = blockStack.peekFirst();

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
