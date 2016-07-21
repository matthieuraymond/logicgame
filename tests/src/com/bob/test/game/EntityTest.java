package com.bob.test.game;

import com.bob.main.Config;
import com.bob.game.world.Entity;
import com.bob.game.world.EntityState;
import com.bob.game.world.WorldCoordinates;
import com.bob.test.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class EntityTest {

    private EntityState[] walkingStates = new EntityState[]{
            EntityState.WALK_RIGHT,
            EntityState.WALK_UP,
            EntityState.WALK_LEFT,
            EntityState.IDLE_DOWN
    };

    @Test
    public void testUpdatePosition() throws Exception {
        for (EntityState s: walkingStates) {
            Entity e = new Entity(0,0);
            e.updateState(s);
            e.increaseTime(1);

            WorldCoordinates c = e.getCoord();
            assertEquals(c.getWorldX(), s.getDx(), 0);
            assertEquals(c.getWorldY(), s.getDy(), 0);
        }
    }

    @Test
    public void bobMovesAreRightOnTiles() throws Exception {
        Entity e = new Entity(0,0);
        float sX = e.getCoord().getScreenX();
        float sY = e.getCoord().getScreenY();

        e.updateState(EntityState.WALK_UP);
        e.increaseTime(1);

        WorldCoordinates ending = e.getCoord();

        assertEquals(Config.tileWidth/2, ending.getScreenX() - sX, 0);
        assertEquals(Config.tileHeight/2, ending.getScreenY() - sY, 0);
    }

    @Test
    public void testMakeIDLE() throws Exception {
        Entity e = new Entity(0,0);

        e.updateState(EntityState.WALK_RIGHT);
        e.makeIDLE();

        assertEquals(EntityState.IDLE_RIGHT, e.getState());

        e.updateState(EntityState.WALK_LEFT);
        e.makeIDLE();

        assertEquals(EntityState.IDLE_LEFT, e.getState());

        e.updateState(EntityState.WALK_UP);
        e.makeIDLE();

        assertEquals(EntityState.IDLE_UP, e.getState());

        e.updateState(EntityState.WALK_DOWN);
        e.makeIDLE();

        assertEquals(EntityState.IDLE_DOWN, e.getState());
    }

    @Test
    public void dontInstructionsWhenLessThan1Sec() throws Exception {
        testNeedInstructions(null, 0, false);
        testNeedInstructions(null, 0.99f, false);
    }

    @Test
    public void needInstructionsWhenMoreThan1Sec() throws Exception {
        testNeedInstructions(null, 1, true);
        testNeedInstructions(null, 2.5f, true);
    }

    @Test
    public void dontInstructionsWhenLessThan1Delta() throws Exception {
        for(EntityState s: walkingStates){
            testNeedInstructions(s, 0.99f, false);
            testNeedInstructions(s, 0f, false);
        }
    }

    @Test
    public void needInstructionsWhenMoreThan1Delta() throws Exception {
        for(EntityState s: walkingStates){
            testNeedInstructions(s, 1, true);
            testNeedInstructions(s, 2.5f, true);
        }
    }

    private void testNeedInstructions(EntityState state, float time, boolean assertion) {
        Entity e = new Entity(0,0);
        if (state != null) e.updateState(state);
        e.increaseTime(time);

        if(assertion) {
            assertTrue(e.needInstructions());
        } else{
            assertFalse(e.needInstructions());
        }
    }
}