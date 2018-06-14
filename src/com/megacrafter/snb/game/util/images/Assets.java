package com.megacrafter.snb.game.util.images;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.megacrafter.snb.panels.GameLoadPanel.initGameLoadPanel;
import static com.megacrafter.snb.panels.GamePanel.initGame;
import static com.megacrafter.snb.panels.LauncherPanel.initLauncher;
import static com.megacrafter.snb.panels.MainMenu.initMainMenu;
import static com.megacrafter.snb.panels.OptionsPanel.initOptionsMenu;
import static com.megacrafter.snb.panels.PausePanel.initPauseMenu;
import static com.megacrafter.snb.panels.SingleplayerPanel.initSingleplayerMenu;
import static com.megacrafter.snb.panels.battle.BattleConPanel.initBattleConMenu;
import static com.megacrafter.snb.panels.battle.BattlePanel.initBattlePanel;

public class Assets {

    private static SpriteSheet blocksheet = null;
    private static SpriteSheet playersheet = null;
    private static SpriteSheet battlebtnsheet = null;

    public static Image spr_grasstile = null;
    public static Image spr_filledgrasstile = null;
    public static Image spr_battletile = null;
    public static Image spr_flowergrasstile1 = null;
    public static Image spr_flowergrasstile2 = null;
    public static Image spr_shoptile = null;
    public static Image spr_treetile_part1 = null;
    public static Image spr_treetile_part2 = null;
    public static Image spr_treetile_part3 = null;
    public static Image spr_treetile_part4 = null;
    public static Image spr_treetile_part5 = null;
    public static Image spr_treetile_part6 = null;
    public static Image spr_treetile_part7 = null;
    public static Image spr_treetile_part8 = null;
    public static Image spr_treetile_part9 = null;

    public static Image spr_playerdown1 = null;
    public static Image spr_playerdown2 = null;
    public static Image spr_playerup1 = null;
    public static Image spr_playerup2 = null;
    public static Image spr_playerright1 = null;
    public static Image spr_playerright2 = null;
    public static Image spr_playerleft1 = null;
    public static Image spr_playerleft2 = null;

    public static Image spr_defensebtn = null;
    public static Image spr_powerbtn = null;
    public static Image spr_attackbtn = null;
    public static Image spr_defensebtn_action = null;
    public static Image spr_powerbtn_action = null;
    public static Image spr_attackbtn_action = null;

    public static Image bg_default = null;

    public static int count = 14;

    public static void load(int i) {
        if (i == 1) {
            blocksheet = new SpriteSheet(ImageUtils.loadImage("/spr_tiles.png"), 16);
        }

        if (i == 2) {
            playersheet = new SpriteSheet(ImageUtils.loadImage("/spr_player.png"), 16);
        }

        if (i == 3) {
            battlebtnsheet = new SpriteSheet(ImageUtils.loadImage("/spr_btn_battle.png"), 30);
        }

        if (i == 4) {
            bg_default = ImageUtils.loadImage("/bg_default.png");
        }

        if (i == 5) {
            initImages();
        }

        if (i == 6) {
            initLauncher();
        }

        if (i == 7) {
            initMainMenu();
        }

        if (i == 8) {
            initSingleplayerMenu();
        }

        if (i == 9) {
            initOptionsMenu();
        }

        if (i == 10) {
            initGame();
        }

        if (i == 11) {
            initGameLoadPanel();
        }

        if (i == 12) {
            initPauseMenu();
        }

        if (i == 13) {
            initBattleConMenu();
        }

        if (i == 14) {
            initBattlePanel();
        }
    }

    private static void initImages() {
        spr_grasstile = blocksheet.getSprite(0, 0, 1, 1, 32, 32);
        spr_battletile = blocksheet.getSprite(1, 0, 1, 1, 32, 32);
        spr_filledgrasstile = blocksheet.getSprite(0, 1, 1, 1, 32, 32);
        spr_flowergrasstile1 = blocksheet.getSprite(1, 1, 1, 1, 32, 32);
        spr_flowergrasstile2 = blocksheet.getSprite(2, 1, 1, 1, 32, 32);
        spr_shoptile = blocksheet.getSprite(2, 0, 1, 1, 32, 32);
        spr_treetile_part1 = blocksheet.getSprite(3, 0, 1, 1, 32, 32);
        spr_treetile_part2 = blocksheet.getSprite(4, 0, 1, 1, 32, 32);
        spr_treetile_part3 = blocksheet.getSprite(5, 0, 1, 1, 32, 32);
        spr_treetile_part4 = blocksheet.getSprite(3, 1, 1, 1, 32, 32);
        spr_treetile_part5 = blocksheet.getSprite(4, 1, 1, 1, 32, 32);
        spr_treetile_part6 = blocksheet.getSprite(5, 1, 1, 1, 32, 32);
        spr_treetile_part7 = blocksheet.getSprite(3, 2, 1, 1, 32, 32);
        spr_treetile_part8 = blocksheet.getSprite(4, 2, 1, 1, 32, 32);
        spr_treetile_part9 = blocksheet.getSprite(5, 2, 1, 1, 32, 32);

        spr_playerdown1 = playersheet.getSprite(0, 0, 1, 1, 32, 32);
        spr_playerdown2 = playersheet.getSprite(1, 0, 1, 1, 32, 32);
        spr_playerup1 = playersheet.getSprite(0, 1, 1, 1, 32, 32);
        spr_playerup2 = playersheet.getSprite(1, 1, 1, 1, 32, 32);
        spr_playerright1 = playersheet.getSprite(0, 2, 1, 1, 32, 32);
        spr_playerright2 = playersheet.getSprite(1, 2, 1, 1, 32, 32);
        spr_playerleft1 = playersheet.getSprite(0, 3, 1, 1, 32, 32);
        spr_playerleft2 = playersheet.getSprite(1, 3, 1, 1, 32, 32);

        spr_defensebtn = battlebtnsheet.getSprite(0, 0, 1, 1, 30, 30);
        spr_powerbtn = battlebtnsheet.getSprite(1, 0, 1, 1, 30, 30);
        spr_attackbtn = battlebtnsheet.getSprite(2, 0, 1, 1, 30, 30);
        spr_defensebtn_action = battlebtnsheet.getSprite(0, 1, 1, 1, 30, 30);
        spr_powerbtn_action = battlebtnsheet.getSprite(1, 1, 1, 1, 30, 30);
        spr_attackbtn_action = battlebtnsheet.getSprite(2, 1, 1, 1, 30, 30);
    }

    private static void initSounds() {}

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

}