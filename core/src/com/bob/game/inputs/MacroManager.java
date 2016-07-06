package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StringBuilder;

public class MacroManager {
    private MacroLayer macroLayer;
    private ModalLayer modalLayer;
    private InputsManager inputsManager;
    private Draggable[] draggables;
    private Macro[] macros;

    public MacroManager() {
        this.draggables = new Draggable[8];
        this.macros = new Macro[8];
        inputsManager = new InputsManager();
    }

    public void setLayers(MacroLayer macroLayer, ModalLayer modalLayer) {
        this.macroLayer = macroLayer;
        this.modalLayer = modalLayer;
        inputsManager.setLayer(modalLayer);
    }

    public void initView(Skin skin) {
        inputsManager.initRuleView(skin, 785, 1080 - 545);
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

        int index = modalLayer.getIndex();

        if (draggables[index] == null) {
            macros[index] = new Macro(modalLayer.getText(), inputsManager.getRules());
        } else {
            macros[index].setTitle(modalLayer.getText());
            macros[index].setRules(inputsManager.getRules());
        }

        createDraggable(skin, macros[index], modalLayer.getIndex());

        macroLayer.getMacroCells();
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

        draggables[index]  = new Draggable(macroLayer, dragImage, draggedImage, macro);

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
            draggables[index].addTarget(t);
        }
    }

    public void displayMacroModal() {
        displayMacroModal(new Block[][]{}, "Macro Title");
    }

    public void displayMacroModal(Block[][] rules, String title) {
        int index = -1;

        for (int i = 0; i < draggables.length; ++i) {
            if (draggables[i] == null) {
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


    public String getRulesString() {
        StringBuilder sb = new StringBuilder();

        for (MacroCell mc: macroLayer.getMacroCells()) {
            sb.append(mc.getLPSString());
        }

        return sb.toString();
    }
}
