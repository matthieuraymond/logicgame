package com.bob.test.game;

import com.bob.game.inputs.Block;
import com.bob.game.inputs.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RuleTest {

    @Test
    public void testGetLPSString() throws Exception {
        Rule r = new Rule();

        r.setRuleBlocks(new Block[] {Block.WHITE, Block.IMPLY, Block.RIGHT});

        assertEquals("isIn(X,Y) & wasIn(U,V) & lights(0) & !lightBulb(X,Y) & white(X,Y) -> goRight(0).", r.getString(0));
    }

    @Test
    public void testIsNullIsTrue() throws Exception {
        Rule r = new Rule();

        r.setRuleBlocks(new Block[] {null, null, null});

        assertTrue(r.isNull());
    }

    @Test
    public void testIsNullIsFalse() throws Exception {
        Rule r = new Rule();

        r.setRuleBlocks(new Block[] {null, null, Block.RIGHT});
        assertFalse(r.isNull());

        r.setRuleBlocks(new Block[] {Block.RIGHT, null});
        assertFalse(r.isNull());
    }
}