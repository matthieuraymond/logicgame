package com.bob.test.game;

import com.bob.game.levels.Level;
import com.bob.game.levels.LevelFactory;
import com.bob.game.levels.WriteLevel;
import com.bob.game.world.LPSHandler;
import com.bob.game.world.MapManager;
import com.bob.test.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class LPSSandBox {

    @Test
    public void sandBox() throws Exception {
        LevelFactory.initialiseLevels();
        Level lvl = LevelFactory.WRITE.get(2);
        MapManager m = new MapManager(lvl.getFloor(), lvl.getObjects());

        LPSHandler lps = new LPSHandler(m.getLPSDescription(), "isIn(X,Y) & wasIn(U,V) & white(X,Y) -> goRight(0).isIn(X,Y) & wasIn(U,V) & white(X,Y) -> goRight(1).", lvl.getX(), lvl.getY());
        /*for (int i = 0; i < 20; ++i) {
            lps.update();
            GoalsList goalsList = GoalsList.getInstance();

            Set<Goal> set = GoalsList.getInstance().getActiveGoals();
            int index = -1;
            if (!set.isEmpty()) {
                Goal g = set.iterator().getNext();
                index = Integer.parseInt(g.getGoal().getTerm(1).getName());
            }
            String s = "lol";

        }*/

    }

}