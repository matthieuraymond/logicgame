package com.bob.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.bob.main.TextureFactory;

class BackgroundLayer extends Layer {

    private final Label text;
    private Label noLights;
    private Label maxLights;
    private Group noLightsGroup;

    public BackgroundLayer(Skin skin) {
        // Bkg
        Image foreground = new Image(TextureFactory.createTexture("screens/foreground.png"));
        group.addActor(foreground);

        // Thumbs
        Image currentThumb = new Image(TextureFactory.createTexture("thumbs/bob.png"));
        currentThumb.setBounds(25, 1080 - 148, currentThumb.getWidth(), currentThumb.getHeight());
        group.addActor(currentThumb);

        // Text
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        text = new Label("", labelStyle);
        text.setBounds(240, 945, 575, 125);
        group.addActor(text);

        // No Lights
        noLightsGroup = new Group();

        Label.LabelStyle lightLabelStyle = new Label.LabelStyle();
        lightLabelStyle.font = skin.getFont("impact");

        Image lightBulb = new Image(TextureFactory.createTexture("bob/light_bulb.png"));
        lightBulb.setBounds(815, 960, 64, 128);
        noLightsGroup.addActor(lightBulb);

        Label colon = new Label(":", lightLabelStyle);
        colon.setBounds(870, 1005, 25, 60);
        noLightsGroup.addActor(colon);

        noLights = new Label("", lightLabelStyle);
        noLights.setBounds(855, 1000, 100, 60);
        noLights.setAlignment(Align.right);
        noLightsGroup.addActor(noLights);

        Label slash = new Label("/", lightLabelStyle);
        slash.setBounds(950, 970, 25, 60);
        noLightsGroup.addActor(slash);

        maxLights = new Label("", lightLabelStyle);
        maxLights.setBounds(975, 940, 100, 60);
        noLightsGroup.addActor(maxLights);

        noLightsGroup.setVisible(false);
        group.addActor(noLightsGroup);
    }

    public void changeText(String text) {
        this.text.setText(text);
    }

    public void setNoLights(int noLights) {
        this.noLights.setText(Integer.toString(noLights));
    }

    public void setMaxLights(int maxLights) {
        setNoLights(0);
        this.maxLights.setText(Integer.toString(maxLights));
        noLightsGroup.setVisible(maxLights > 0);
    }
}
