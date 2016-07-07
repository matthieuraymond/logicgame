package com.bob.test.game;

import com.bob.game.inputs.Block;
import com.bob.game.inputs.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RuleTest {

    @Test
    public void testGetLPSString() throws Exception {
        Rule r = new Rule();

        r.setRuleBlocks(new Block[] {Block.WHITE, Block.IMPLY, Block.RIGHT});

        assertEquals("isIn(X,Y) & wasIn(U,V) & lights(0) & !lightBulb(X,Y) & white(X,Y) -> goRight(0).", r.getString(0));
    }

    @Test
    public void testIsValid() throws Exception {

    }
}