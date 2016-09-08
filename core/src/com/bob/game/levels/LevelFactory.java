package com.bob.game.levels;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;

import java.io.IOException;
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
        WRITE.add(loadLevelFromFile("maps/xml/short.xml"));
        WRITE.add(loadLevelFromFile("maps/xml/straight.xml"));
        WRITE.add(loadLevelFromFile("maps/xml/turn.xml"));
        WRITE.add(loadLevelFromFile("maps/xml/not.xml"));
        WRITE.add(loadLevelFromFile("maps/xml/loop.xml"));

        for (int i = 0; i < WRITE.size() - 1; i++) {
            WRITE.get(i).setNext(WRITE.get(i+1));
        }
    }

    private static void populateRead() {
        READ.add(loadLevelFromFile("maps/xml/guess.xml"));
        READ.add(loadLevelFromFile("maps/xml/guessBis.xml"));
        READ.add(loadLevelFromFile("maps/xml/guessTer.xml"));/*
                11,11,
                new Block[][]{

                },
                "You will not get that one,\nI am sure that it is too complicated!",
                new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
        ));
*/
        for (int i = 0; i < READ.size() - 1; i++) {
            READ.get(i).setNext(READ.get(i+1));
        }
    }

    private static void populateMacro() {/*
        MACRO.add(new MacroLevel("maps/xml/macro.xml",
            6,11,
            "In this mode, I'll change my behavior.\nIndeed, it depends on how many light bulbs I have.\n\nCan you help me collect this light bulb?",
            new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"},
            new String[]{"screens/tut_macro.png"}
        ));
        MACRO.add(new MacroLevel("maps/xml/macro2.xml",
            6,11,
            "Okay, \nNow that you understood the concept, can you apply the same principles here?",
            new String[]{"Vas y", "Tu peux y arriver", "Tu es beau!"}
        ));*/
    }


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
