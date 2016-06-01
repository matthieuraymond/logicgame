package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
        String[] menu = {"NEW GAME", "LEVELS", "SETTINGS", "QUIT"};
        HashMap<String, Button> menuButtons = new HashMap<>();
        int menuButtonY = 430;

        for (String buttonName : menu) {

            TextButton button = new TextButton(buttonName, skin, "big_grey_button");
            button.setBounds(760, menuButtonY, 400, 100);

            menuButtons.put(buttonName, button);
            menuGroup.addActor(button);

            menuButtonY -= 125;
        }

        menuButtons.get("NEW GAME").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                levelSelected = Level.level1;
                hide();
            }
        });

        menuButtons.get("QUIT").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                Gdx.app.exit();
            }
        });

        menuButtons.get("LEVELS").addListener(new ClickListener() {
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

        int noLevels = Level.values().length;
        int levelsButtonX = 660;
        int levelsButtonY = 430;

        for (int i = 0; i < noLevels; i++) {
            final int j = i;
            TextButton button = new TextButton(Integer.toString(i + 1), skin, "grey_square_button");
            button.setBounds(levelsButtonX, levelsButtonY, 100, 100);

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

        TextButton backButton = new TextButton("BACK", skin, "grey_button");
        backButton.setBounds(10, 15, 200, 60);
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
