package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bob.main.TextureFactory;

public class MacroLayer extends InputsLayer {
    private Skin skin;
    private MacroCell[] macroCells;
    private TextButton newMacroButton;

    public MacroLayer() {

    }

    public MacroLayer(Skin skin) {
        this.skin = skin;

        Image topCache = new Image(TextureFactory.createTexture("screens/macro_top.png"));
        topCache.setBounds(1400, 720, 500, 310);
        addActor(topCache);

        Image botCache = new Image(TextureFactory.createTexture("screens/macro_bot.png"));
        botCache.setBounds(1400, 80, 500, 615);
        addActor(botCache);

        Label.LabelStyle macroStyle = new Label.LabelStyle();
        macroStyle.background = new Image(TextureFactory.createTexture("blocks/macro.png")).getDrawable();
        macroStyle.font = skin.getFont("impact_small");

        skin.add("macro_style", macroStyle);

        Texture macroTarget = TextureFactory.createTexture("blocks/macro_target.png");

        macroCells = new MacroCell[8];

        for (int i = 0; i < 8; ++i) {
            Image bkgImage = new Image(macroTarget);
            bkgImage.setBounds(1567, 585 - i * 70, 230, 50);

            macroCells[i] = new MacroCell(this, 1567, 585 - i * 70, bkgImage, skin);

            addTarget(macroCells[i].getTarget());
        }
    }

    public void addModalButton(final MacroManager macroManager) {
        newMacroButton = new TextButton("+", skin, "green_button");
        newMacroButton.setBounds(1840, 970, 50, 50);

        newMacroButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                macroManager.displayMacroModal();
            };
        });

        addActor(newMacroButton);
    }

    public MacroCell[] getMacroCells() {
        return macroCells;
    }

    public TextButton getNewMacroButton() {
        return newMacroButton;
    }

    public void setMacroCells(MacroCell[] macroCells) {
        this.macroCells = macroCells;
    }

    public void resetCells() {
        for (int i = 0; i < macroCells.length; i++) {
            if (macroCells[i] != null) {
                macroCells[i].clear();
            }
        }
    }
}
