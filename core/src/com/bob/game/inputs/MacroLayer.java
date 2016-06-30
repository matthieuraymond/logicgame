package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.bob.game.Layer;

public class MacroLayer extends Layer {
    private final Skin skin;
    private String[] macros;
    private DragAndDrop.Target[] targets;

    public MacroLayer(Skin skin) {
        this.skin = skin;
        this.targets = new DragAndDrop.Target[8];

        Image topCache = new Image(new Texture("resources/screens/macro_top.png"));
        topCache.setBounds(1400, 720, 500, 310);
        addActor(topCache);

        Image botCache = new Image(new Texture("resources/screens/macro_bot.png"));
        botCache.setBounds(1400, 80, 500, 615);
        addActor(botCache);

        Texture macroTarget = new Texture("resources/blocks/target.png");

        for (int i = 0; i < 8; ++i) {
            Image bkgImage = new Image(macroTarget);

            final int index = i;

            bkgImage.setBounds(1535, 585 - i * 70, 290, 50);

            addActor(bkgImage);

            DragAndDrop.Target target = new DragAndDrop.Target(bkgImage) {
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    getActor().setColor(Color.GREEN);
                    return true;
                }

                public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                    getActor().setColor(Color.CLEAR);
                }

                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    setMacro(index , (String)payload.getObject()); // TODO define object more precisely
                }
            };

            target.getActor().setColor(Color.CLEAR);

            targets[i] = target;
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


    private void setMacro(int index, String macro) {
        macros[index] = macro;
    }

    public void setMacros(String[] macros) {
        this.macros = macros;
    }
}
