package com.bob.game.levels;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.bob.game.inputs.Block;

import java.io.IOException;

public abstract class Level {

    protected int[][] floor;
    protected int[][] objects;
    protected int coordX;
    protected int coordY;
    protected int noRules;
    protected Block[] inputs;
    protected Block[][] rules;
    protected String[] tutorialImages;
    protected String[] hints;
    protected String text;

    protected Level next;

    public Level(XmlReader.Element root) {

        XmlReader.Element bobNode = root.getChildByName("bob");

        this.floor = csvToArray(root.getChildByName("floor").getText());
        this.objects = csvToArray(root.getChildByName("object").getText());
        this.coordX = bobNode.getInt("x");
        this.coordY = bobNode.getInt("y");
        this.text = root.getChildByName("text").getText();
        this.hints = extractStrings(root.getChildByName("hints"));
        this.tutorialImages = extractStrings(root.getChildByName("tutorial"));

        this.noRules = 8;
        this.inputs = new Block[]{};
        this.rules = new Block[][]{};
    }

    public abstract void save();

    public Level getNext() {
        return next;
    };

    public Boolean allowMacro() {
        return false;
    }

    public Boolean allowRuleReset() {
        return true;
    }

    public Boolean hasTutorial() {
        return tutorialImages.length > 0;
    }

    public Boolean hasHints() {
        return hints.length > 0;
    }

    public Block[] getInputs() {
        return inputs;
    }

    public int getNoRules() {
        return noRules;
    }

    public int[][] getFloor() {
        return floor;
    }

    public int[][] getObjects() {
        return objects;
    }

    public int getX() {
        return coordX;
    }

    public int getY() {
        return coordY;
    }

    public String getText() {
        return text;
    }

    public Block[][] getRules() {
        return rules;
    }

    public String[] getTutorialImages() {
        return tutorialImages;
    }

    public void setNext(Level next) {
        this.next = next;
    }

    public String[] getHints() {
        return hints;
    }

    private int[][] csvToArray(String csv) {
        String[] lines = csv.split("\n");
        int[][] res = new int[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            String[] cols = lines[i].split(",");
            res[i] = new int[cols.length];
            for(int j = 0; j < cols.length; j++) {
                res[i][j] = Integer.parseInt(cols[j]);
            }

        }

        return res;
    }

    private String[] extractStrings(XmlReader.Element element) {
        String[] res = new String[element.getChildCount()];

        for (int i = 0; i < res.length; i++) {
            res[i] = element.getChild(i).getText();
        }

        return res;
    }

    protected Block[] extractBlocks(XmlReader.Element blockContainer) {
        int n = blockContainer.getChildCount();
        Block[] res = new Block[n];

        for(int i = 0; i < n; i++) {
            res[i] = Block.getBlock(blockContainer.getChild(i).getAttribute("name"));
        }

        return res;
    }
}
