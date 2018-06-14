package com.megacrafter.snb.panels;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.util.PanelSlide;

import javax.swing.*;
import java.awt.event.ActionListener;

import static com.megacrafter.snb.panels.GamePanel.game;
import static com.megacrafter.snb.panels.MainMenu.main_menu;
import static com.megacrafter.snb.panels.MainMenu.main_menu_button_font;
import static com.megacrafter.snb.panels.OptionsPanel.*;
import static com.megacrafter.snb.panels.SingleplayerPanel.singleplayer_menu_yesno;

public class PausePanel {

    /* PAUSE MENU VARIABLES */
    public static Menu pausemenu = new Menu();
    private static JButton pausemenu_resume_btn = new JButton("Oyuna Devam Et");
    private static JButton pausemenu_settings_btn = new JButton("Ayarlar");
    private static JButton pausemenu_quit_btn = new JButton("Ana Menüye Dön");
    public static ActionListener al_backtopausemenu = e -> {
        game.playername = options_nickname_field.getText();
        game.player.setName(game.playername);
        PanelSlide.slideToRight(options_menu, pausemenu, -1, -1);
    };
    /* PAUSE MENU VARIABLES */

    public static void initPauseMenu() {
        pausemenu.setBounds(0, 0, Display.WIDTH, Display.HEIGHT);
        pausemenu.setOpaque(false);

        pausemenu_resume_btn.setBounds(250, 210, 300, 50);
        pausemenu_resume_btn.setFont(main_menu_button_font);
        pausemenu_resume_btn.setFocusPainted(false);
        pausemenu_resume_btn.setOpaque(false);
        pausemenu_resume_btn.setBorderPainted(false);
        pausemenu_resume_btn.addActionListener(e -> game.resume());

        pausemenu_settings_btn.setBounds(250, 270, 300, 50);
        pausemenu_settings_btn.setFont(main_menu_button_font);
        pausemenu_settings_btn.setFocusPainted(false);
        pausemenu_settings_btn.setOpaque(false);
        pausemenu_settings_btn.setBorderPainted(false);
        pausemenu_settings_btn.addActionListener(e -> {
            options_menu.remove(options_back_button_tomainmenu);
            options_menu.add(options_back_button_topausemenu);
            PanelSlide.slideToLeft(pausemenu, options_menu, -1, -1);
        });

        pausemenu_quit_btn.setBounds(250, 330, 300, 50);
        pausemenu_quit_btn.setFont(main_menu_button_font);
        pausemenu_quit_btn.setFocusPainted(false);
        pausemenu_quit_btn.setOpaque(false);
        pausemenu_quit_btn.setBorderPainted(false);
        pausemenu_quit_btn.addActionListener(e -> {
            int pane = JOptionPane.showOptionDialog(null, "Ana menüye dönmek istediğinize emin misiniz?",
                    "Ana Menüye Dön", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, singleplayer_menu_yesno, 1);
            if (pane == 0) {
                main_menu.forward();
            }
        });

        pausemenu.add(pausemenu_resume_btn);
        pausemenu.add(pausemenu_settings_btn);
        pausemenu.add(pausemenu_quit_btn);
    }

}