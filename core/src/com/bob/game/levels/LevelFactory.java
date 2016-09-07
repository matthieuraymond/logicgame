package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.bob.game.inputs.Block;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    public static final List<Level> WRITE = new ArrayList<>();
    public static final List<Level> READ = new ArrayList<>();
    public static final List<Level> MACRO = new ArrayList<>();

    public static void initialiseLevels() { // TODO, PUT INTO FILES
        populateWrite();
        //populateRead();
        //populateMacro();
    }

    private static void populateWrite() {
        /*WRITE.add(new WriteLevel("maps/tmx/short.tmx",
                9,11,
                8,
                new Block[]{Block.RIGHT},
                "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"},
                new String[]{"screens/tut1.png", "screens/tut2.png", "screens/tut3.png"}
        ));*/

        WRITE.add(loadLevelFromFile("maps/tmx/straight.tmx"));
/*
        WRITE.add(new WriteLevel("maps/tmx/turn.tmx",
                2,11,
                2,
                new Block[]{Block.WHITE, Block.RED, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
                "WOW! That was amazing!\n\nHowever I still have a little problem:\n\tI don't know how to cross that one, can you help me again?\n\nThanks!",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
        ));

        WRITE.add(new WriteLevel("maps/tmx/not.tmx",
                2,11,
                4,
                new Block[]{Block.WHITE, Block.RED, Block.GREEN, Block.ORANGE, Block.PURPLE, Block.YELLOW, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
                "Be careful, this one is tricky!\n\nLet's see if you can riddle me this.",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
        ));

        WRITE.add(new WriteLevel("maps/tmx/loop.tmx",
                7,11,
                5,
                new Block[]{Block.WHITE, Block.RED, Block.GREEN, Block.YELLOW, Block.WHITE_PREV, Block.RED_PREV, Block.GREEN_PREV, Block.YELLOW_PREV, Block.IMPLY, Block.AND, Block.NOT, Block.LEFT, Block.RIGHT, Block.UP, Block.DOWN},
                "Oh its getting quite complicated here.\n\nIf you need a hint: you'll have to make good use of the \"previous\" block.\nThanks for your help.",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
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
                "Hi again!\nMake sure you've played the write mode first!\n\nHere you'll need to guess where I'm going!\nClick on the question mark when you've guessed.",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"},
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
                "The previous one was too easy...\n\nSame layout, different rules, can you guess it right?",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
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
                "You will not get that one,\nI am sure that it is too complicated!",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
        ));

        for (int i = 0; i < READ.size() - 1; i++) {
            READ.get(i).setNext(READ.get(i+1));
        }
    }

    private static void populateMacro() {
        MACRO.add(new MacroLevel("maps/tmx/macro.tmx",
            6,11,
            "In this mode, I'll change my behavior.\nIndeed, it depends on how many light bulbs I have.\n\nCan you help me collect this light bulb?",
            new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"},
            new String[]{"screens/tut_macro.png"}
        ));
        MACRO.add(new MacroLevel("maps/tmx/macro2.tmx",
            6,11,
            "Okay, \nNow that you understood the concept, can you apply the same principles here?",
            new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
        ));*/
    }

    private static void loadLevel(String path){

        XmlReader xmlReader = new XmlReader();

        try {

            FileHandle localFile = new FileHandle(path);
            localFile.write(new FileInputStream(new File(path)), false);

            XmlReader.Element root = xmlReader.parse(localFile);

            XmlReader.Element levelNode = root.getChildByName("level");
            XmlReader.Element bobNode = levelNode.getChildByName("bob");

            int startX = bobNode.getInt("x");
            int startY = bobNode.getInt("y");
            int noRules = levelNode.getChildByName("rules").getIntAttribute("available");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    protected int[][] floor;
    protected int[][] objects;
    protected int coordX;
    protected int coordY;
    protected int noRules = 8;
    protected Block[] inputs;
    protected Block[][] rules;
    protected String[] tutorialImages;
    protected String[] hints;
    protected String text;


    super(floor, objects, x, y, text, hints, tutorialImages);
     */

    public static Level loadLevelFromFile(String path) {
        XmlReader xmlReader = new XmlReader();

        XmlReader.Element root = null;

        try {
            root = xmlReader.parse(new FileHandle(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String type = root.getAttribute("type");

        switch (type) {
            case "WRITE":
                return new WriteLevel(root);
            case "READ":
                return new ReadLevel(root);
            case "MACRO":
                return new MacroLevel(root);
            default:
                return null;
        }
    }
}
