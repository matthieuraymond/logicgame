package test;

import com.bob.game.inputs.Block;
import com.bob.game.inputs.InputsManager;
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

        for (ReadLevel read : ReadLevel.values()) {
            im.resetRules(read.getLevel().getRules());
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

        assertEquals("isIn(X,Y) & wasIn(U,V) & white(X,Y) -> goRight().isIn(X,Y) & wasIn(U,V) & white(X,Y)&red(X,Y) -> goRight().", im.getRulesString());
    }


}