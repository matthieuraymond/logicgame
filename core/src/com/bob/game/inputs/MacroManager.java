package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MacroManager {
    private MacroLayer macroLayer;
    private ModalLayer modalLayer;
    private InputsManager inputsManager;
    private String[] macros;

    public MacroManager() {
        inputsManager = new InputsManager();
        macros = new String[8];
    }

    public void setLayers(MacroLayer macroLayer, ModalLayer modalLayer) {
        this.macroLayer = macroLayer;
        this.modalLayer = modalLayer;
        inputsManager.setLayer(modalLayer);
        this.macroLayer.setMacros(macros);
    }

    public void addButtons(Skin skin) {
        macroLayer.addModalButton(this);

        // Submit modal button
        TextButton button = new TextButton("+", skin, "green_button");
        button.setBounds(1340, 970, 50, 50);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                submitModal();
            };
        });

        modalLayer.addActor(button);
    }

    private void submitModal() {
        modalLayer.setVisibility(false);

        String macroName = modalLayer.getText();

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
