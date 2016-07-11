package com.bob.game.inputs;

import com.badlogic.gdx.graphics.g2d.Batch;
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
        final TextButton submitbutton = new TextButton("Submit", skin, "green_button") {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                this.setDisabled(!inputsManager.checkRules());
                super.draw(batch, parentAlpha);
            }
        };
        submitbutton.setBounds(1250, 880, 200, 50);
        submitbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!submitbutton.isDisabled()) submitModal(skin);
            };
        });
        modalLayer.addActor(submitbutton);

        // Cancel modal button
        TextButton button = new TextButton("Cancel", skin, "blue_button");
        button.setBounds(1250, 940, 200, 50);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                modalLayer.setVisibility(false);
            };
        });
        modalLayer.addActor(button);

        // Delete modal button
        button = new TextButton("Delete", skin, "orange_button");
        button.setBounds(1250, 1000, 200, 50);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                deleteButtonModal();
            };
        });
        modalLayer.addActor(button);
    }

    private void deleteButtonModal() {
        modalLayer.setVisibility(false);

        int index = modalLayer.getIndex();

        if (draggables[index] != null) {
            macros[index] = null;
            draggables[index].clear();
            draggables[index] = null;
        }

        // Todo refactor for demeter law
        macroLayer.getNewMacroButton().setDisabled(false);
    }

    private void submitModal(Skin skin) {
        modalLayer.setVisibility(false);

        int index = modalLayer.getIndex();

        // Disable button if all occupied
        boolean aSlotFree = true;

        for (Macro m: macros) {
            aSlotFree |= m == null;
        }

        macroLayer.getNewMacroButton().setDisabled(!aSlotFree);

        // Create dragable and macro object
        if (draggables[index] == null) {
            macros[index] = new Macro(modalLayer.getText(), inputsManager.getRules());
        } else {
            draggables[index].clear();
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
        draggedImage.setSize(230, 50);
        draggedImage.setEllipsis(true);
        draggedImage.setAlignment(Align.center);

        draggables[index]  = new Draggable(macroLayer, dragImage, draggedImage, macro);
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
        MacroCell[] cells = macroLayer.getMacroCells();
        for (int i = 0; i < cells.length; i++) {
            sb.append(cells[i].getLPSString(i));
        }

        return sb.toString();
    }
}
