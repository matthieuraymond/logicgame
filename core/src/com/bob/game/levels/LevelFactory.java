package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bob.game.inputs.Block;

import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    public static final List<Level> WRITE = new ArrayList<>();
    public static final List<Level> READ = new ArrayList<>();
    public static final List<Level> MACRO = new ArrayList<>();

    public static void initialiseLevels() { // TODO, PUT INTO FILES
        populateWrite();
        populateRead();
        populateMacro();
    }

    private static void populateWrite() {
        WRITE.add(new WriteLevel("maps/tmx/short.tmx",
                9,11,
                8,
                new Block[]{Block.RIGHT},
                "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!",
                new String[]{"screens/tut1.png", "screens/tut2.png", "screens/tut3.png"}
        ));

        WRITE.add(new WriteLevel("maps/tmx/straight.tmx",
                2,11,
                1,
                new Block[]{Block.WHITE, Block.IMPLY, Block.RIGHT},
                "Thanks for that first one!\n\nI must admit that doing the same here would be tedious... Could you find another way?",
                new String[]{"screens/tut4.png"}
        ));

        WRITE.add(new WriteLevel("maps/tmx/turn.tmx",
                2,11,
                2,
                new Block[]{Block.WHITE, Block.RED, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
                "WOW! That was amazing!\n\nHowever I still have a little problem:\n\tI don't know how to cross that one, can you help me again?\n\nThanks!"
        ));

        WRITE.add(new WriteLevel("maps/tmx/not.tmx",
                2,11,
                4,
                new Block[]{Block.WHITE, Block.RED, Block.GREEN, Block.ORANGE, Block.PURPLE, Block.YELLOW, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
                "Be careful, this one is tricky!\n\nLet's see if you can riddle me this."
        ));

        WRITE.add(new WriteLevel("maps/tmx/loop.tmx",
                7,11,
                5,
                new Block[]{Block.WHITE, Block.RED, Block.GREEN, Block.YELLOW, Block.WHITE_PREV, Block.RED_PREV, Block.GREEN_PREV, Block.YELLOW_PREV, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
                "Oh its getting quite complicated here.\n\nIf you need a hint: you'll have to make good use of the \"previous\" block.\nThanks for your help."
        ));

        for (int i = 0; i < WRITE.size() - 1; i++) {
            WRITE.get(i).setNext(WRITE.get(i+1));
        }
    }

    private static void populateRead() {
        READ.add(new ReadLevel("maps/tmx/guess.tmx",
                11,11,
                new Block[][]{
                        {Block.RED, Block.IMPLY, Block.DOWN},
                        {Block.PURPLE, Block.IMPLY, Block.RIGHT},
                        {Block.YELLOW, Block.IMPLY, Block.DOWN},
                        {Block.GREEN, Block.IMPLY, Block.LEFT}
                },
                "Hi again!\nnMake sure you've played the write mode first!\n\nHere you'll need to guess where I'm going!\nClick on the question mark when you've guessed.",
                new String[]{"screens/tut_read.png"}
        ));
        READ.add(new ReadLevel("maps/tmx/guess.tmx",
                11,11,
                new Block[][]{
                        {Block.RED, Block.AND, Block.RED_PREV, Block.IMPLY, Block.DOWN},
                        {Block.RED, Block.AND, Block.YELLOW_PREV, Block.IMPLY, Block.LEFT},
                        {Block.YELLOW, Block.AND, Block.YELLOW_PREV, Block.IMPLY, Block.DOWN},
                        {Block.YELLOW, Block.AND, Block.RED_PREV, Block.IMPLY, Block.UP},
                        {Block.PURPLE, Block.AND, Block.YELLOW_PREV, Block.IMPLY, Block.RIGHT},
                        {Block.GREEN, Block.AND, Block.RED_PREV,Block.IMPLY, Block.LEFT},
                        {Block.PURPLE, Block.AND, Block.PURPLE_PREV, Block.IMPLY, Block.RIGHT},
                        {Block.GREEN, Block.AND, Block.GREEN_PREV,Block.IMPLY, Block.LEFT},
                },
                "The previous one was too easy...\n\nSame layout, different rules, can you guess it right?"
        ));
        READ.add(new ReadLevel("maps/tmx/guess2.tmx",
                11,11,
                new Block[][]{
                        {Block.WHITE, Block.AND, Block.WHITE_PREV, null, Block.IMPLY, Block.RIGHT},
                        {Block.WHITE, Block.AND, Block.RED_PREV, null, Block.IMPLY, Block.LEFT},
                        {Block.WHITE, Block.AND, Block.GREEN_PREV, null, Block.IMPLY, Block.UP},
                        {Block.GREEN, Block.AND, Block.NOT, Block.WHITE_PREV, Block.IMPLY, Block.DOWN},
                        {Block.GREEN, Block.AND, Block.WHITE_PREV, null, Block.IMPLY, Block.RIGHT},
                        {Block.RED, Block.AND, Block.NOT, Block.WHITE_PREV, Block.IMPLY, Block.UP},
                        {Block.RED, Block.AND, Block.WHITE_PREV, null, Block.IMPLY, Block.LEFT},
                },
                "You will not get that one,\nI am sure that it is too complicated!"
        ));

        for (int i = 0; i < READ.size() - 1; i++) {
            READ.get(i).setNext(READ.get(i+1));
        }
    }

    private static void populateMacro() {
        MACRO.add(new MacroLevel("maps/tmx/macro.tmx",
            6,11,
            "In this mode, I'll change my behavior.\nIndeed, it depends on how many light bulbs I have.\n\nCan you help me collect this light bulb?",
            new String[]{"screens/tut_macro.png"}
        ));
        MACRO.add(new MacroLevel("maps/tmx/macro2.tmx",
            6,11,
            "Okay, \nNow that you understood the concept, can you apply the same principles here?"
        ));
    }


}
