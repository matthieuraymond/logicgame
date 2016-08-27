package com.bob.test.game;

import com.bob.game.inputs.Block;
import com.bob.game.inputs.InputsManager;
import com.bob.game.levels.Level;
import com.bob.game.levels.LevelFactory;
import com.bob.game.world.WorldController;
import com.bob.test.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class WorldControllerTest {

    @Test
    public void testLevels() throws Exception {

        LevelFactory.initialiseLevels();
        MacroManagerTest mmt = new MacroManagerTest();

        testWriteLevel(
                1,
                new Block[][] {{Block.WHITE, Block.IMPLY, Block.RIGHT}}
        );

        testWriteLevel(
                1,
                new Block[][] {{Block.WHITE, Block.IMPLY, Block.RIGHT}}
        );

        testWriteLevel(
                1,
                new Block[][] {{Block.WHITE, Block.IMPLY, Block.RIGHT}}
        );

        testWriteLevel(
                1,
                new Block[][] {{Block.WHITE, Block.IMPLY, Block.RIGHT}}
        );

        testWriteLevel(
                2,
                new Block[][] {{Block.WHITE, Block.IMPLY, Block.RIGHT},{Block.RED, Block.IMPLY, Block.DOWN}}
        );

        testWriteLevel(
                3,
                new Block[][] {{Block.NOT, Block.WHITE, Block.IMPLY, Block.RIGHT}}
        );

        testWriteLevel(
                4,
                new Block[][] {
                        {Block.WHITE, Block.AND, Block.WHITE_PREV, Block.IMPLY, Block.RIGHT},
                        {Block.RED, Block.IMPLY, Block.DOWN},
                        {Block.YELLOW, Block.IMPLY, Block.LEFT},
                        {Block.GREEN_PREV, Block.IMPLY, Block.UP},
                        {Block.GREEN, Block.IMPLY, Block.UP}
                }
        );

        testReadLevel(0);
        testReadLevel(1);
        testReadLevel(2);

        mmt.testLevel1();
        mmt.testLevel2();

    }

    public void testWriteLevel(int lvlIndex, Block[][] rules) {

        Level lvl = LevelFactory.WRITE.get(lvlIndex);

        InputsManager im = new InputsManager();
        im.resetRules(rules);

        Long start = System.nanoTime();

        WorldController wc = new WorldController();
        wc.setupWorld(lvl);
        wc.startLPSAnimation(lvl, im.getRulesString());


        Long settedUp = System.nanoTime();

        int i;
        for (i=0; i < 100; i++) {
            wc.updateWorld(1f);
            if (wc.isLevelWon()) break;
        }

        Long done = System.nanoTime();

        System.out.println("Write level " +lvlIndex + ": " + i + " cycles in: "
                + Long.toString((done - settedUp)/1000) + " us, set-up of: "
                + Long.toString((settedUp - start)/1000) + " us, total: "
                + Long.toString((done - start)/1000) + " us");
        assertTrue(wc.isLevelWon());
    }

    public void testReadLevel(int lvlIndex) {

        Level lvl = LevelFactory.READ.get(lvlIndex);

        InputsManager im = new InputsManager();
        im.resetRules(lvl.getRules());

        Long start = System.nanoTime();

        WorldController wc = new WorldController();
        wc.setupWorld(lvl);
        wc.startLPSAnimation(lvl, im.getRulesString());


        Long settedUp = System.nanoTime();

        int i;
        for (i=0; i < 200; i++) {
            wc.updateWorld(1f);
            if (wc.isLevelWon() || wc.isOnQuestionMark()) break;
        }

        Long done = System.nanoTime();

        System.out.println("Read level " + (lvlIndex+1) + ": " + i + " cycles in: "
                + Long.toString((done - settedUp)/1000) + " us, set-up of: "
                + Long.toString((settedUp - start)/1000) + " us, total: "
                + Long.toString((done - start)/1000) + " us");

        assertTrue(wc.isLevelWon() || wc.isOnQuestionMark());
    }
}
