package com.bob.test.game;

import com.bob.game.inputs.Block;
import com.bob.game.inputs.InputsManager;
import com.bob.game.levels.Level;
import com.bob.game.levels.LevelFactory;
import com.bob.game.levels.ReadLevel;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputsManagerTest {

    @Test
    public void testCheckRules() throws Exception {
        InputsManager im = new InputsManager();

        im.resetRules(new Block[][]{
                {Block.WHITE, Block.IMPLY, Block.RIGHT},
                {Block.WHITE, Block.AND, Block.RED, Block.IMPLY, Block.RIGHT}
        });

        assertTrue(im.checkRules());
    }

    @Test
    public void readLevelIntegrationTest() throws Exception {
        InputsManager im = new InputsManager();

        for (Level read : LevelFactory.READ) {
            im.resetRules(read.getRules());
        }

        assertTrue(im.checkRules());
    }
    @Test
    public void getLPSStringTest() throws Exception {
        InputsManager im = new InputsManager();

        im.resetRules(new Block[][]{
                {Block.WHITE, Block.IMPLY, Block.RIGHT},
                {Block.WHITE, Block.AND, Block.RED, Block.IMPLY, Block.RIGHT}
        });

        assertEquals("isIn(X,Y) & wasIn(U,V) & lights(0) & !lightBulb(X,Y) & white(X,Y) -> goRight(0).isIn(X,Y) & wasIn(U,V) & lights(0) & !lightBulb(X,Y) & white(X,Y)&red(X,Y) -> goRight(1).", im.getRulesString());
    }


}