package com.bob.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    public static final List<Level> WRITE = new ArrayList<>();
    public static final List<Level> READ = new ArrayList<>();
    public static final List<Level> MACRO = new ArrayList<>();

    public static void initialiseLevels() {
        populateWrite();
        populateRead();
        populateMacro();
    }

    private static void populateWrite() {
        WRITE.add(loadInternalLevel("levels/short.xml"));
        WRITE.add(loadInternalLevel("levels/straight.xml"));
        WRITE.add(loadInternalLevel("levels/turn.xml"));
        WRITE.add(loadInternalLevel("levels/not.xml"));
        WRITE.add(loadInternalLevel("levels/loop.xml"));

        for (int i = 0; i < WRITE.size() - 1; i++) {
            WRITE.get(i).setNext(WRITE.get(i+1));
        }
    }

    private static void populateRead() {
        READ.add(loadInternalLevel("levels/guess.xml"));
        READ.add(loadInternalLevel("levels/guessBis.xml"));
        READ.add(loadInternalLevel("levels/guessTer.xml"));

        for (int i = 0; i < READ.size() - 1; i++) {
            READ.get(i).setNext(READ.get(i+1));
        }
    }

    private static void populateMacro() {
        MACRO.add(loadInternalLevel("levels/macro.xml"));
        MACRO.add(loadInternalLevel("levels/macro2.xml"));

        for (int i = 0; i < MACRO.size() - 1; i++) {
            MACRO.get(i).setNext(MACRO.get(i+1));
        }
    }

    public static Level loadExternaLevel(String path) {
        return(loadLevelFromFile(new FileHandle(path)));
    }

    public static Level loadInternalLevel(String path) {
        return(loadLevelFromFile(Gdx.files.internal(path)));
    }

    public static Level loadLevelFromFile(FileHandle file) {
        XmlReader xmlReader = new XmlReader();

        XmlReader.Element root = null;

        try {
            root = xmlReader.parse(file);
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
