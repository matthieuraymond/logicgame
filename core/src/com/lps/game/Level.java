package com.lps.game;

public enum Level {
    // map, startX, startY, noRules, inputs, prevAuthorised, field)
    level1("maps/tmx/straight.tmx",2,-1,1, new String[]{"white", "red"}, false, "Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"),
    level3("maps/tmx/turn.tmx",2,-1,2, new String[]{"white", "red"}, false, "WOW! That was amazing!\n\nHowever I still have a little problem:\n\tI don't know how to cross that one, can you help me again?\n\nThanks!"),
    level4("maps/tmx/not.tmx",2,-1,4, new String[]{"white", "red", "yellow", "green", "orange", "purple"}, false, "Be careful, this one is tricky!\n\nLet's see if you can riddle me this."),
    level5("maps/tmx/loop.tmx",7,-1,5, new String[]{"white", "red", "yellow", "green"}, true, "Be careful, this one is tricky!\n\nYou'll have to make good use of the \"previous\" block.\nThanks for your help.");


    private String map;
    private int coordX;
    private int coordY;
    private int noRules;
    private String[] colors;
    private boolean prevAuthorised;
    private String text;

    Level(String map, int x, int y, int noRules, String[] colors, boolean prevAuthorised, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
        this.colors = colors;
        this.prevAuthorised = prevAuthorised;
        this.text = text;
    }

    public String getMap() {
        return map;
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

    public Level next() {
        Level[] levels = Level.values();

        int res = 0;

        for(int i = 0; i < levels.length; i++) {
            if (levels[i] == this) {
                res = (i + 1) % levels.length;
                break;
            }
        }

        return levels[res];
    }

    public int getNoRules() {
        return noRules;
    }

    public String[] getColors() {
        return colors;
    }

    public boolean isPrevAuthorised() {
        return prevAuthorised;
    }
}
