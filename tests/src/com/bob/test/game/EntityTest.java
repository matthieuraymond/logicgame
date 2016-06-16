package com.bob.test.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.bob.game.world.Entity;
import com.bob.test.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class EntityTest {

    @Test
    public void testUpdatePosition() throws Exception {

    }

    @Test
    public void testMakeIDLE() throws Exception {

    }

    @Test
    public void needInstructionsWhenMoreThan1Sec() throws Exception {

        Entity e1 = new Entity(0,0);
        e1.increaseTime(1);

        assertTrue(e1.needInstructions());

        Entity e2 = new Entity(0,0);
        e2.increaseTime(1.1f);

        assertTrue(e2.needInstructions());

    }
}