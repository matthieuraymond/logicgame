package com.bob.game.inputs;

import com.badlogic.gdx.graphics.Texture;
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
    private MacroCell[] macroCells;

    public MacroManager() {
        this.macros = new Draggable[8];
        this.macroCells = new MacroCell[8];
        inputsManager = new InputsManager();
    }

    public void setLayers(MacroLayer macroLayer, ModalLayer modalLayer) {
        this.macroLayer = macroLayer;
        this.modalLayer = modalLayer;
        inputsManager.setLayer(modalLayer);
    }

    public void initView(Skin skin) {
        inputsManager.initRuleView(skin, 785, 1080 - 545);
        Texture macroTarget = new Texture("resources/blocks/macro_target.png");

        for (int i = 0; i < 8; ++i) {
            Image bkgImage = new Image(macroTarget);
            bkgImage.setBounds(1567, 585 - i * 70, 230, 50);

            macroCells[i] = new MacroCell(macroLayer, 1567, 585 - i * 70, bkgImage, skin);

            macroLayer.addTarget(macroCells[i].getTarget());
        }
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

        createDraggable(skin, macro, modalLayer.getIndex());
    }

    private void createDraggable(Skin skin, final Macro macro, final int index) {
        String title = macro.getTitle();

        Label dragImage = new Label(title, skin, "macro_style");

        dragImage.setBounds(1415 + (index/4) * 240, 915 - (index % 4) * 60, 230, 50);
        dragImage.setEllipsis(true);
        dragImage.setAlignment(Align.center);

        Label draggedImage = new Label(title, skin, "macro_style");
        dragImage.setBounds(1415 + (index/4) * 240, 915 - (index % 4) * 60, 230, 50);
        draggedImage.setEllipsis(true);
        draggedImage.setAlignment(Align.center);

        macros[index]  = new Draggable(macroLayer, dragImage, draggedImage, macro);

        final int i = index;
        dragImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getTapCount() == 2) {
                    displayMacroModal(macro.getRules(), macro.getTitle(), index);
                }
            }
        });

        for (DragAndDrop.Target t : macroLayer.getTargets()) {
            macros[index].addTarget(t);
        }
    }

    public void displayMacroModal() {
        displayMacroModal(new Block[][]{}, "Macro Title");
    }

    public void displayMacroModal(Block[][] rules, String title) {
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

        displayMacroModal(rules, title, index);
    }

    public void displayMacroModal(Block[][] rules, String title, int index) {
        inputsManager.setupRules(8, rules);
        inputsManager.setupInputs(Block.values(), 725, 1080 - 215);
        modalLayer.setText(title);
        modalLayer.setIndex(index);
        modalLayer.setVisibility(true);
    }

    public void toggleLights() {
        inputsManager.toggleLights();
    }


}
