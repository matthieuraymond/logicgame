package com.bob.game.inputs;

import com.badlogic.gdx.utils.StringBuilder;

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

    public void setTitle(String text) {
        this.title = text;
    }

    public void setRules(Block[][] rules) {
        this.rules = rules;
    }

    public String getLPSString(int macroIndex) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rules.length; i++) {
            Rule r = new Rule();
            r.setRuleBlocks(rules[i]);

            sb.append(r.getString(i, macroIndex));
        }

        return sb.toString();
    }
}
