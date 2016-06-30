package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bob.game.Layer;

public class MacroLayer extends Layer {
    private final Skin skin;

    public MacroLayer(Skin skin) {
        this.skin = skin;

        Image topCache = new Image(new Texture("resources/screens/macro_top.png"));
        topCache.setBounds(1400, 720, 500, 310);
        addActor(topCache);

        Image botCache = new Image(new Texture("resources/screens/macro_bot.png"));
        botCache.setBounds(1400, 80, 500, 615);
        addActor(botCache);
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
