package com.bob.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.bob.game.levels.*;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private final Group menuGroup = new Group();
    private final Group modeGroup = new Group();
    private final Group settingsGroup = new Group();
    private final Group levelsGroup = new Group();
    private boolean isVisible = true;
    private Level levelSelected;

    public Menu(Skin skin) {

        initMenu(skin);
        initLevels(skin);
        initMode(skin);
        initSettings(skin);
    }

    private void initMenu(final Skin skin) {
        // Bkg
        Image menuBkg = new Image(TextureFactory.createTexture("screens/menu.png"));
        menuBkg.setBounds(0,0, 1920, 1080);
        menuGroup.addActor(menuBkg);

        // Menu button
        String[] menu = {"PLAY", "LEVELS", "SETTINGS", "QUIT"};

        Map<String, Button> buttons = addButtons(menuGroup, skin, menu);

        buttons.get("PLAY").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                modeGroup.setVisible(true);
            }
        });

        buttons.get("QUIT").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttons.get("LEVELS").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                levelsGroup.clear();
                initLevels(skin);
                levelsGroup.setVisible(true);
            }
        });

        buttons.get("SETTINGS").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                settingsGroup.setVisible(true);
            }
        });

    }

    private Map<String,Button> addButtons(Group group, Skin skin, String[] names) {
        Map<String, Button> buttons = new HashMap<>();
        int menuButtonY = 430;

        for (String buttonName : names) {

            TextButton button = new TextButton(buttonName, skin, "big_grey_button");
            button.setBounds(760, menuButtonY, 400, 100);

            buttons.put(buttonName, button);
            group.addActor(button);

            menuButtonY -= 125;
        }

        return buttons;
    }

    private void initMode(Skin skin) {

        Image levelsBkg = new Image(TextureFactory.createTexture("screens/menu.png"));
        levelsBkg.setBounds(0,0, 1920, 1080);

        modeGroup.addActor(levelsBkg);

        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = skin.getFont("impact");
        Label startLabel = new Label("< Start here!", titleStyle);
        startLabel.setBounds(1185, 430, 400, 100);
        modeGroup.addActor(startLabel);

        String[] menu = {"WRITER", "READER", "MACRO"};
        Map<String, Button> buttons = addButtons(modeGroup, skin, menu);

        buttons.get("WRITER").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                int lvlIndex = 0;

                if (Config.levelsAreLocked) {
                    Preferences prefs = Gdx.app.getPreferences("Progress");
                    lvlIndex = prefs.getInteger("writeProgress", -1) + 1;
                }
                launchLevel(WriteLevel.values()[lvlIndex].getLevel());
            }
        });

        buttons.get("READER").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                int lvlIndex = 0;

                if (Config.levelsAreLocked) {
                    Preferences prefs = Gdx.app.getPreferences("Progress");
                    lvlIndex = prefs.getInteger("readProgress", -1) + 1;
                }
                launchLevel(ReadLevel.values()[lvlIndex].getLevel());
            }
        });

        buttons.get("MACRO").addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                int lvlIndex = 0;

                if (Config.levelsAreLocked) {
                    Preferences prefs = Gdx.app.getPreferences("Progress");
                    lvlIndex = prefs.getInteger("macroProgress", -1) + 1;
                }
                launchLevel(MacroLevel.values()[lvlIndex].getLevel());
            }
        });

        addBackButton(skin, modeGroup);

        modeGroup.setVisible(false);
    }

    private void initSettings(Skin skin) {

        // Bkg
        Image menuBkg = new Image(TextureFactory.createTexture("screens/menu.png"));
        menuBkg.setBounds(0,0, 1920, 1080);
        settingsGroup.addActor(menuBkg);

        TextButton button = new TextButton("RESET LEVELS", skin, "grey_button");
        button.setBounds(810, 430, 300, 100);

        button.addListener(
                new ClickListener() {
                   public void clicked(InputEvent ie, float x, float y) {
                       Preferences prefs = Gdx.app.getPreferences("Progress");
                       prefs.putInteger("writeProgress", -1);
                       prefs.putInteger("readProgress", -1);
                       prefs.putInteger("macroProgress", -1);
                   }
               }
        );
        settingsGroup.addActor(button);

        addBackButton(skin, settingsGroup);

        settingsGroup.setVisible(false);
    }

    private void launchLevel(Level level) {
        levelSelected = level;
        hide();
    }

    private void initLevels(Skin skin) {
        // Levels Menu
        Image levelsBkg = new Image(TextureFactory.createTexture("screens/menu.png"));
        levelsBkg.setBounds(0,0, 1920, 1080);
        levelsGroup.addActor(levelsBkg);

        addLevelButtons(skin, WriteLevel.values(), "writeProgress", "Write", 430);
        addLevelButtons(skin, ReadLevel.values(), "readProgress", "Read", 300);
        addLevelButtons(skin, MacroLevel.values(), "macroProgress", "Macro", 170);

        addBackButton(skin, levelsGroup);

        levelsGroup.setVisible(false);
    }

    private void addLevelButtons(Skin skin, final LevelFactory[] levels, String prefString, String title, int startY) {
        int noLevels = levels.length;
        int levelsButtonX = 660;
        int levelsButtonY = startY;

        Texture lockTexture = TextureFactory.createTexture("buttons/lock.png");

        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = skin.getFont("impact");
        Label titleLabel = new Label(title, titleStyle);
        titleLabel.setAlignment(Align.right);
        titleLabel.setBounds(0, startY, 640, 100);

        levelsGroup.addActor(titleLabel);

        Preferences prefs = Gdx.app.getPreferences("Progress");
        int unlocked = prefs.getInteger(prefString, -1);


        for (int i = 0; i < noLevels; i++) {
            final int j = i;
            TextButton button = new TextButton(Integer.toString(i + 1), skin, "grey_square_button");
            button.setBounds(levelsButtonX, levelsButtonY, 100, 100);

            button.addListener(new ClickListener() {
                public void clicked(InputEvent ie, float x, float y) {
                    launchLevel(levels[j].getLevel());
                }
            });

            levelsGroup.addActor(button);

            // Disable if not unlocked
            if (i > unlocked + 1 && Config.levelsAreLocked) {
                button.setDisabled(true);
                Image lock = new Image(lockTexture);
                lock.setBounds(levelsButtonX - 14, levelsButtonY - 14, 128, 128);
                levelsGroup.addActor(lock);
            }


            levelsButtonX += 125;

            if (levelsButtonX >= 1360) {
                levelsButtonY -= 125;
                levelsButtonX = 560;
            }
        }

    }

    private void addBackButton(Skin skin, final Group group) {
        TextButton backButton = new TextButton("BACK", skin, "grey_button");
        backButton.setBounds(10, 15, 200, 60);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent ie, float x, float y) {
                group.setVisible(false);
            }
        });

        group.addActor(backButton);
    }

    public void show() {
        menuGroup.setVisible(true);
        isVisible = true;
    }

    public void hide() {
        menuGroup.setVisible(false);
        modeGroup.setVisible(false);
        levelsGroup.setVisible(false);
        settingsGroup.setVisible(false);
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
        stage.addActor(modeGroup);
        stage.addActor(settingsGroup);
    }
}
