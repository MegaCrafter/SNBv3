package com.megacrafter.snb;

import com.megacrafter.programapi.Program;
import com.megacrafter.snb.game.GameLoop;
import com.megacrafter.snb.game.util.images.Assets;
import com.megacrafter.snb.input.Focus;
import com.megacrafter.snb.input.Keyboard;
import com.megacrafter.snb.input.Mouse;
import com.megacrafter.snb.panels.GamePanel;
import com.megacrafter.snb.panels.LoadingPanel;
import com.megacrafter.snb.panels.battle.BattleConPanel;

import javax.swing.*;

import static com.megacrafter.snb.panels.LauncherPanel.launcher_username_txtf;
import static com.megacrafter.snb.panels.LoadingPanel.initLoadingMenu;
import static com.megacrafter.snb.panels.LoadingPanel.loadStuff;

public class Display {

    private static final String TITLE = "SNS - v3.00.8 - BETA";
    public static final int WIDTH = 800, HEIGHT = 600;
    public static final int LOADING_WIDTH = 700, LOADING_HEIGHT = 437;

    public static GameLoop gameloop = new GameLoop();

    public static final boolean TESTING_BATTLE = false;


    public static void main(String[] args) {
        decorateLAF();

        initLoadingMenu();

        JFrame frame = new JFrame("SNS - Yükleniyor...");
        frame.setLayout(null);
        frame.setSize(LOADING_WIDTH, LOADING_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.add(LoadingPanel.loading_menu);
        frame.setVisible(true);
        LoadingPanel.loading_menu.setVisible(true);

        if (loadStuff()) {
            frame.setVisible(false);

            if (TESTING_BATTLE) {
                GamePanel.game.generateTile(0, 0);
                GamePanel.game.init();
                BattleConPanel.battlecon_menu.makeMainMenu();
                BattleConPanel.resetComponents();
            }

            Program.setImage(Assets.bg_default);
            Program.start(TITLE, WIDTH, HEIGHT);

            Program.getWindow().addKeyListener(new Keyboard());

            Program.getWindow().addMouseListener(new Mouse());
            Program.getWindow().addMouseMotionListener(new Mouse());
            Program.getWindow().addMouseWheelListener(new Mouse());

            Program.getWindow().addFocusListener(new Focus());

            launcher_username_txtf.requestFocus();
        }

        gameloop.start();
    }

    private static void decorateLAF() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}