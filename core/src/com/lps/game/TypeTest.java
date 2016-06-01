package com.lps.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeTest {

    @Test
    public void ruleIsValid() throws Exception {
        Type[][] testCases = {
                {},
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
                {Type.CONSEQUENT},
                {Type.CONSEQUENT, Type.IMPLY, Type.FLUENT},
                {Type.FLUENT, Type.CONSEQUENT},
                {Type.FLUENT, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
                {Type.FLUENT, Type.NOT, Type.IMPLY, Type.CONSEQUENT},
                {Type.FLUENT, Type.NOT, Type.FLUENT, Type.IMPLY, Type.CONSEQUENT},
        };

        for (Type[] test: testCases) {
            assertFalse(Type.isValid(test));
        }
    }
}