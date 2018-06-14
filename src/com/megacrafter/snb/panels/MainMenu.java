package com.megacrafter.snb.panels;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.game.util.PanelSlide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.megacrafter.snb.panels.OptionsPanel.*;
import static com.megacrafter.snb.panels.SingleplayerPanel.singleplayer_menu;

public class MainMenu {

    static Menu main_menu = new Menu() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

//            GUtils.drawAlphaGradient(g, 50, 50, 30, 20, 100, Color.BLACK);
        }
    };
    private static JButton main_menu_exit_button = new JButton("Çıkış");
    private static JButton main_menu_settings_button = new JButton("Ayarlar");
    private static JButton main_menu_multiplayer_button = new JButton("Arkadaşlarınla Oyna");
    private static JButton main_menu_singleplayer_button = new JButton("Tek Başına Oyna");
    public static Font main_menu_button_font = new Font("Arial", Font.BOLD, 20);
    private static JLabel main_menu_versiontext_label = new JLabel("Version: 3.0");

    public static void initMainMenu() {
        main_menu.setOpaque(false);
        main_menu.setBounds(0, 0, 800, 600);

        main_menu_singleplayer_button.setBounds(200, 240, 400, 50);
        main_menu_singleplayer_button.setFont(main_menu_button_font);
        main_menu_singleplayer_button.setFocusPainted(false);
        main_menu_singleplayer_button.setOpaque(false);
        main_menu_singleplayer_button.setBorderPainted(false);
        main_menu_singleplayer_button.addActionListener(e -> {
            PanelSlide.slideToRight(main_menu, singleplayer_menu, -1, -1);
        });

        main_menu_multiplayer_button.setBounds(200, 310, 400, 50);
        main_menu_multiplayer_button.setFont(main_menu_button_font);
        main_menu_multiplayer_button.setEnabled(false);
        main_menu_multiplayer_button.setBorderPainted(false);
        main_menu_multiplayer_button.setFocusPainted(false);
        main_menu_multiplayer_button.setOpaque(false);
        main_menu_multiplayer_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                main_menu_multiplayer_button.setText("Gelişme Aşamasında...");
            }

            public void mouseExited(MouseEvent e) {
                main_menu_multiplayer_button.setText("Arkadaşlarınla Oyna");
            }
        });

        main_menu_settings_button.setBounds(200, 380, 400, 50);
        main_menu_settings_button.setFont(main_menu_button_font);
        main_menu_settings_button.setFocusPainted(false);
        main_menu_settings_button.setOpaque(false);
        main_menu_settings_button.setBorderPainted(false);
        main_menu_settings_button.addActionListener(e -> {
            options_menu.remove(options_back_button_topausemenu);
            options_menu.add(options_back_button_tomainmenu);
            PanelSlide.slideToLeft(main_menu, options_menu, -1, -1);
        });

        main_menu_exit_button.setBounds(200, 450, 400, 50);
        main_menu_exit_button.setFont(main_menu_button_font);
        main_menu_exit_button.addActionListener(e -> System.exit(0));
        main_menu_exit_button.setFocusPainted(false);
        main_menu_exit_button.setOpaque(false);
        main_menu_exit_button.setBorderPainted(false);

        main_menu_versiontext_label.setBounds(715, 0, 200, 15);
        main_menu_versiontext_label.setForeground(Color.BLACK);
        main_menu_versiontext_label.setFont(new Font("Arial", Font.BOLD, 13));

        main_menu.add(main_menu_singleplayer_button);
        main_menu.add(main_menu_multiplayer_button);
        main_menu.add(main_menu_settings_button);
        main_menu.add(main_menu_exit_button);
        main_menu.add(main_menu_versiontext_label);
    }

}