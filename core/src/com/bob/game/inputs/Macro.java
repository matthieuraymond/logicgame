package com.bob.game.inputs;

public class Macro {
    private Block[][] rules;

    private String title;
    public Macro(String title, Block[][] rules) {
        this.title = title;
        this.rules = rules;
    }

    public Block[][] getRules() {
        return rules;
    }

    public String getTitle() {
        return title;
    }
}
