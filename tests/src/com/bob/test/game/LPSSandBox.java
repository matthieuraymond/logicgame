package com.bob.test.game;

import com.bob.game.levels.Level;
import com.bob.game.levels.WriteLevel;
import com.bob.game.world.LPSHandler;
import com.bob.game.world.MapManager;
import com.bob.lps.model.CycleHandler;
import com.bob.lps.model.Goal;
import com.bob.lps.model.GoalsList;
import com.bob.lps.model.SimpleSentence;
import com.bob.test.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class LPSSandBox {

    @Test
    public void sandBox() throws Exception {
        Level lvl = WriteLevel.level2.getLevel();
        MapManager m = new MapManager(lvl.getMap());

        LPSHandler lps = new LPSHandler(m.getLPSDescription(), "isIn(X,Y) & wasIn(U,V) & white(X,Y) -> goRight(0).isIn(X,Y) & wasIn(U,V) & red(X,Y) -> goDown(1).", lvl.getX(), lvl.getY());
        for (int i = 0; i < 20; ++i) {
            lps.update();
            Set<Goal> set = GoalsList.getInstance().getActiveGoals();
            int index = -1;
            if (!set.isEmpty()) {
                Goal g = set.iterator().next();
                index = Integer.parseInt(g.getGoal().getTerm(1).getName());
            }
            String s = "lol";
        }

    }

}