package com.bob.test.game;

import com.bob.game.inputs.Type;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void ruleIsValid() throws Exception {
        Type[][] testCases = {
                {},
                {Type.CONSEQUENT, Type.CONSEQUENT},
                {Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
                {Type.NOT, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
                {Type.FLUENT, Type.AND, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
                {Type.FLUENT, Type.AND, Type.NOT, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
                {Type.NOT, Type.FLUENT, Type.AND, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
        };

        for (Type[] test: testCases) {
            assertTrue(Type.isValid(test));
        }
    }

    @Test
    public void ruleIsInvalid() throws Exception {
        Type[][] testCases = {
                {Type.IMPLY},
                {Type.FLUENT},
                {Type.NOT},
                {Type.AND},
                {Type.CONSEQUENT, Type.IMPLY, Type.FLUENT},
                {Type.FLUENT, Type.CONSEQUENT},
                {Type.FLUENT, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
                {Type.FLUENT, Type.NOT, Type.IMPLY, Type.CONSEQUENT},
                {Type.FLUENT, Type.NOT, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
                {Type.FLUENT, Type.IMPLY, Type.CONSEQUENT, Type.CONSEQUENT},
        };

        for (Type[] test: testCases) {
            assertFalse(Type.isValid(test));
        }
    }
}