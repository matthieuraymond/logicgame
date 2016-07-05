package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;

public class MacroManager {
    private MacroLayer macroLayer;
    private ModalLayer modalLayer;
    private InputsManager inputsManager;
    private Draggable[] macros;

    public MacroManager() {
        inputsManager = new InputsManager();
    }

    public void setLayers(MacroLayer macroLayer, ModalLayer modalLayer) {
        this.macroLayer = macroLayer;
        this.modalLayer = modalLayer;
        this.macros = new Draggable[8];
        inputsManager.setLayer(modalLayer);
    }

    public void addButtons(final Skin skin) {
        macroLayer.addModalButton(this);

        // Submit modal button
        TextButton button = new TextButton("+", skin, "green_button");
        button.setBounds(1340, 970, 50, 50);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                submitModal(skin);
            };
        });

        modalLayer.addActor(button);
    }

    private void submitModal(Skin skin) {
        modalLayer.setVisibility(false);

        Macro macro = new Macro(modalLayer.getText(), inputsManager.getRules());

        createDraggable(skin, macro);
    }

    private void createDraggable(Skin skin, Macro macro) {
        String title = macro.getTitle();

        int index = -1;

        for (int i = 0; i < macros.length; ++i) {
            if (macros[i] == null) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        Label dragImage = new Label(title, skin, "macro_style");
        dragImage.setBounds(1415 + (index/4) * 240, 915 - (index % 4) * 60, 230, 50);
        dragImage.setEllipsis(true);
        dragImage.setAlignment(Align.center);

        Label draggedImage = new Label(title, skin, "macro_style");
        dragImage.setBounds(1415 + (index/4) * 240, 915 - (index % 4) * 60, 230, 50);
        draggedImage.setEllipsis(true);
        draggedImage.setAlignment(Align.center);

        final Draggable d = new Draggable(macroLayer, skin, dragImage, draggedImage, macro);
        macros[index] = d;

        dragImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( getTapCount() == 2) {
                    Macro m = (Macro)d.getPayLoad();
                    displayMacroModal(m.getRules(), m.getTitle());
                }
            }
        });

        for (DragAndDrop.Target t : macroLayer.getTargets()) {
            d.addTarget(t);
        }
    }

    public void initView(Skin skin) {
        inputsManager.initRuleView(skin, 785, 1080 - 545);
    }

    public void displayMacroModal() {
        displayMacroModal(new Block[][]{}, "Macro Title");
    }

    public void displayMacroModal(Block[][] rules, String title) {
        inputsManager.setupRules(8, rules);
        inputsManager.setupInputs(Block.values(), 725, 1080 - 215);
        modalLayer.setText(title);
        modalLayer.setVisibility(true);
    }

    public void toggleLights() {
        inputsManager.toggleLights();
    }


}
