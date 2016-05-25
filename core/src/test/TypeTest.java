package test;

import com.lps.game.Type;
import org.junit.Assert;
import org.junit.Test;
public class TypeTest {

    @Test
    public void testIsValidWhenEmpty() throws Exception {
        Type[] types = new Type[8];

        Assert.assertTrue(Type.isValid(types));
    }

    @Test
    public void testIsInvalidOnJustOne() throws Exception {
        testEverywhereOnType(Type.FLUENT);
        testEverywhereOnType(Type.AND);
        testEverywhereOnType(Type.CONSEQUENT);
        testEverywhereOnType(Type.IMPLY);
        testEverywhereOnType(Type.NOT);
    }

    private void testEverywhereOnType(Type t) {
        Type[] types;

        for (int i = 0; i < 8; i++) {
            types = new Type[8];
            types[i] = t;
            Assert.assertFalse(Type.isValid(types));
        }
    }
}