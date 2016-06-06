package test;

import com.bob.game.world.LPSHandler;
import org.junit.Test;

public class LPSHandlerTest {

    @Test
    public void testUpdate() throws Exception {
        LPSHandler lps = new LPSHandler();
        int i = 0;
        boolean keepGoing = true;
        while (i < 10 && keepGoing) {
            keepGoing = updateAndPrint(lps);
            i++;
        }

    }

    private boolean updateAndPrint(LPSHandler lps) {
        lps.update();
        String res = lps.getEvents().toString();
        System.out.println(res);
        return res != "true.";
    }
}