package com.lps.game;

public enum ReadLevel implements Level {

    ;

    private String map;
    private int coordX;
    private int coordY;
    private int noRules;
    private String text;
    private Object rules;

    ReadLevel(String map, int x, int y, int noRules, String text) {
        this.map = map;
        this.coordX = x;
        this.coordY = y;
        this.noRules = noRules;
        this.text = text;
    }

    @Override
    public String getMap() {
        return map;
    }

    @Override
    public int getX() {
        return coordX;
    }

    @Override
    public int getY() {
        return coordY;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Level next() {
        ReadLevel[] levels = ReadLevel.values();

        int res = 0;

        for(int i = 0; i < levels.length; i++) {
            if (levels[i] == this) {
                res = (i + 1) % levels.length;
                break;
            }
        }

        return levels[res];
    }

    @Override
    public int getNoRules() {
        return noRules;
    }

    public Object getRules() {
        return rules;
    }
}
