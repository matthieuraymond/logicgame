package com.bob.game.inputs;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MacroManager {
    private MacroLayer macroLayer;
    private InputsLayer modalLayer;
    private InputsManager inputsManager;

    public MacroManager() {
        inputsManager = new InputsManager();
    }

    public void setLayers(MacroLayer macroLayer, InputsLayer modalLayer) {
        this.macroLayer = macroLayer;
        this.modalLayer = modalLayer;
        inputsManager.setLayer(modalLayer);
    }

    public void addModalButton() {
        macroLayer.addModalButton(this);
    }

    public void initView(Skin skin) {
        inputsManager.initRuleView(skin);
    }

    public void displayMacroModal() {
        inputsManager.setupInputs(Block.values(), 725, 1080 - 215);
        modalLayer.setVisibility(true);
    }
}
