package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.bob.game.Layer;

import java.util.ArrayList;

public class MacroLayer extends InputsLayer {
    private final Skin skin;
    private MacroCell[] macros;

    public MacroLayer(Skin skin) {
        this.skin = skin;
        this.macros = new MacroCell[8];

        Image topCache = new Image(new Texture("resources/screens/macro_top.png"));
        topCache.setBounds(1400, 720, 500, 310);
        addActor(topCache);

        Image botCache = new Image(new Texture("resources/screens/macro_bot.png"));
        botCache.setBounds(1400, 80, 500, 615);
        addActor(botCache);

        Label.LabelStyle macroStyle = new Label.LabelStyle();
        macroStyle.background = new Image(new Texture("resources/blocks/macro.png")).getDrawable();
        macroStyle.font = skin.getFont("impact_small");

        skin.add("macro_style", macroStyle);

        Texture macroTarget = new Texture("resources/blocks/macro_target.png");

        for (int i = 0; i < 8; ++i) {
            Image bkgImage = new Image(macroTarget);
            bkgImage.setBounds(1567, 585 - i * 70, 230, 50);

            macros[i] = new MacroCell(this, 1567, 585 - i * 70, bkgImage, skin);

            targets.add(macros[i].getTarget());
        }
    }

    public void addModalButton(final MacroManager macroManager) {
        TextButton button = new TextButton("+", skin, "green_button");
        button.setBounds(1840, 970, 50, 50);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                macroManager.displayMacroModal();
            };
        });

        addActor(button);
    }
}
