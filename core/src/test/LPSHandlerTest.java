package test;

import com.lps.game.LPSHandler;
import org.junit.Test;
import com.lps.model.*;

import static org.junit.Assert.*;

public class LPSHandlerTest {

    @Test
    public void testUpdate() throws Exception {
        LPSHandler lps = new LPSHandler("bob");

        lps.update();

        RuleSet event = lps.getEvents();
        System.out.println(event.toString());
    }
}