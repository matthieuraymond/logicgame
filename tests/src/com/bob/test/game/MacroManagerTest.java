package com.bob.test.game;

import com.bob.game.inputs.*;
import com.bob.game.levels.Level;
import com.bob.game.levels.LevelFactory;
import com.bob.game.levels.MacroLevel;
import com.bob.game.world.WorldController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MacroManagerTest {

    @Test
    public void testGetRulesString() throws Exception {
        MacroManager mm = setupLevel1();

        assertEquals("isIn(X,Y) & wasIn(U,V) & lights(0) & !lightBulb(X,Y) & white(X,Y) -> goRight(0).isIn(X,Y) & wasIn(U,V) & lights(0) & !lightBulb(X,Y) & red(X,Y) -> goUp(1).isIn(X,Y) & wasIn(U,V) & lights(1) & !lightBulb(X,Y) & white(X,Y) -> goRight(0).isIn(X,Y) & wasIn(U,V) & lights(1) & !lightBulb(X,Y) & red(X,Y) -> goDown(1).", mm.getRulesString());
    }

    @Test
    public void testLevel1() throws Exception {
        LevelFactory.initialiseLevels();
        Level lvl = LevelFactory.MACRO.get(0);

        WorldController wc = new WorldController();
        wc.setupWorld(lvl);

        MacroManager mm = setupLevel1();

        wc.startLPSAnimation(lvl, mm.getRulesString());
        for (int i=0; i < 200; i++) {
            wc.updateWorld(1f);
            if (wc.isLevelWon()) break;
        }

        assertTrue(wc.isLevelWon());
    }

    private MacroManager setupLevel1() {
        MacroCell mc1 = new MacroCell();
        mc1.setPayload(new Macro("", new Block[][]{
                {Block.WHITE, Block.IMPLY, Block.RIGHT},
                {Block.RED, Block.IMPLY, Block.UP}
        }));

        MacroCell mc2 = new MacroCell();
        mc2.setPayload(new Macro("", new Block[][]{
                {Block.WHITE, Block.IMPLY, Block.RIGHT},
                {Block.RED, Block.IMPLY, Block.DOWN}
        }));

        MacroLayer ml = new MacroLayer();
        ml.setMacroCells(new MacroCell[]{mc1, mc2});

        MacroManager mm = new MacroManager();
        mm.setLayers(ml, null);

        return mm;
    }
}