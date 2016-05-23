package com.lps.game;

public enum Level {
    level1("maps/tmx/map1.tmx",2,-1,1,"Hi, my name is Bob!\n\nI am quite a simple robot and I am lost. Can you help me to reach the golden platform?\n\nTo do so, write rules I can follow in the box on the right!\nThanks for your help!"),
    level2("maps/tmx/map2.tmx",2,-1,2,"WOW! That was amazing!\n\nHowever I still have a little problem:\n\tI don't know how to cross that one, can you help me again?\n\nThanks!"),
    level3("maps/tmx/map3.tmx",2,-1,1,"Be careful, this one is tricky!\n\nLet's see if you can riddle me this."),
    level4("maps/tmx/map4.tmx",2,-1,5,"Be careful, this one is tricky!\n\nYou'll have to make good use of the \"previous\" block.\nThanks for your help.");


    private String map;
    private int coordX;
    private int coordY;
    private int noRules;
    private String text;

    Level(String map, int x, int y, int noRules, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
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
}
