package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.HashMap;

public class Menu {

    private final Group menuGroup = new Group();

    private final Group levelsGroup = new Group();
    private boolean isVisible = true;
    private Level levelSelected;
    public Menu(Skin skin) {

        initMenu(skin);
        initLevels(skin);

    }

    private void initMenu(Skin skin) {
        // Bkg
        Image menuBkg = new Image(new Texture("screens/menu.png"));
        menuBkg.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menuGroup.addActor(menuBkg);

        // Menu button
        String[] menu = {"new_game", "levels", "settings", "quit"};
        HashMap<String, Button> menuButtons = new HashMap<>();
        int menuButtonY = 430;

        for (int i = 0; i < menu.length; i++) {
            skin.add(menu[i], new Texture("menu_buttons/" + menu[i] + ".png"));
            skin.add(menu[i] + "_clicked", new Texture("menu_buttons/" + menu[i] + "_clicked.png"));

            Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
            buttonStyle.up = skin.getDrawable(menu[i]);
            buttonStyle.down = skin.getDrawable(menu[i] + "_clicked");

            Button button = new Button(buttonStyle);
            button.setBounds(760, menuButtonY, 400, 100);

            menuButtons.put(menu[i], button);
            menuGroup.addActor(button);

            menuButtonY -= 125;
        }

        menuButtons.get("new_game").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                levelSelected = Level.level1;
                hide();
            }
        });

        menuButtons.get("quit").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                Gdx.app.exit();
            }
        });

        menuButtons.get("levels").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                showLevels();
            }
        });

    }

    private void initLevels(Skin skin) {
        // Levels Menu
        Image levelsBkg = new Image(new Texture("screens/menu.png"));
        levelsBkg.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        levelsGroup.addActor(levelsBkg);

        skin.add("level", new Texture("menu_buttons/level.png"));
        skin.add("level_clicked", new Texture("menu_buttons/level_clicked.png"));

        TextButton.TextButtonStyle levelButtonStyle = new TextButton.TextButtonStyle();
        levelButtonStyle.up = skin.getDrawable("level");
        levelButtonStyle.down = skin.getDrawable("level_clicked");
        levelButtonStyle.font = new BitmapFont();
        levelButtonStyle.font.getData().scale(4f);

        int noLevels = Level.values().length;
        int levelsButtonX = 660;
        int levelsButtonY = 430;

        for (int i = 0; i < noLevels; i++) {
            final int j = i;
            TextButton button = new TextButton(Integer.toString(i + 1), levelButtonStyle);
            button.setBounds(levelsButtonX, levelsButtonY, 100, 100);

            button.setName(Integer.toString(i));

            button.addListener(new ClickListener() {
                public void clicked(InputEvent ie, float x, float y) {
                    levelSelected = Level.values()[j];
                    hide();
                }
            });


            levelsGroup.addActor(button);

            levelsButtonX += 125;

            if (levelsButtonX >= 1360) {
                levelsButtonY -= 125;
                levelsButtonX = 560;
            }
        }

        skin.add("back", new Texture("menu_buttons/back.png"));
        skin.add("back_clicked", new Texture("menu_buttons/back_clicked.png"));

        Button.ButtonStyle backStyle = new Button.ButtonStyle();
        backStyle.up = skin.getDrawable("back");
        backStyle.down = skin.getDrawable("back_clicked");

        Button backButton = new Button(backStyle);
        backButton.setBounds(10, 15, 200, 50);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                hideLevels();
            }
        });

        levelsGroup.addActor(backButton);

        hideLevels();
    }

    private void showLevels() {
        levelsGroup.setVisible(true);
    }

    private void hideLevels() {
        levelsGroup.setVisible(false);
    }

    public void show() {
        menuGroup.setVisible(true);
        isVisible = true;
    }

    public void hide() {
        menuGroup.setVisible(false);
        levelsGroup.setVisible(false);
        isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Level getLevelSelected() {
        return levelSelected;
    }

    public void setStage(Stage stage) {
        stage.addActor(menuGroup);
        stage.addActor(levelsGroup);
        //stage.addActor(settingsGroup);
    }
}
