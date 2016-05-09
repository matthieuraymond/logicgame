package test;

import com.lps.game.LPSHandler;
import org.junit.Test;

public class LPSHandlerTest {

    @Test
    public void testUpdate() throws Exception {
        LPSHandler lps = new LPSHandler("bob_test");

        updateAndPrint(lps);
        updateAndPrint(lps);
        updateAndPrint(lps);

    }

    private void updateAndPrint(LPSHandler lps) {
        lps.update();

        System.out.println(lps.getEvents().toString());
    }
}