package com.bob.test.game;

import com.bob.game.levels.Level;
import com.bob.game.levels.LevelFactory;
import com.bob.game.levels.ReadLevel;
import com.bob.game.levels.WriteLevel;
import com.bob.game.world.MapManager;
import com.bob.test.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class MapManagerTest {

    @Test
    public void testBobOnFloorInLevels() throws Exception {

        List<Level> lfs = new LinkedList<>();
        lfs.addAll(LevelFactory.WRITE);
        lfs.addAll(LevelFactory.READ);

        for (Level lvl: lfs) {
            MapManager m = new MapManager(lvl.getFloor(), lvl.getObjects());

            String type = m.getType(lvl.getX(), lvl.getY());
            assertNotEquals("water", type);
        }
    }

    @Test
    public void testGetLPSDescription() throws Exception {
        LevelFactory.initialiseLevels();
        Level lvl = LevelFactory.WRITE.get(0);

        MapManager m = new MapManager(lvl.getFloor(), lvl.getObjects());

        assertEquals(
                "white(9,11).white(10,11).white(11,11).white(12,11).gold(13,11).",
                m.getLPSDescription()
        );
    }
}