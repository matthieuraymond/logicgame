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

        List<LevelFactory> lfs = new LinkedList<>();
        Collections.addAll(lfs, WriteLevel.values());
        Collections.addAll(lfs, ReadLevel.values());

        for (LevelFactory lf: lfs) {
            Level lvl = lf.getLevel();
            MapManager m = new MapManager(lvl.getMap());

            String type = m.getType(lvl.getX(), lvl.getY());
            assertNotEquals("water", type);
        }
    }

    @Test
    public void testGetLPSDescription() throws Exception {
        Level lvl = WriteLevel.level1.getLevel();

        MapManager m = new MapManager(lvl.getMap());

        assertEquals(
                "white(2,11).white(3,11).white(4,11).white(5,11).white(6,11).white(7,11).white(8,11).white(9,11).white(10,11).white(11,11).white(12,11).white(13,11).white(14,11).white(15,11).gold(16,11).",
                m.getLPSDescription()
        );
    }
}