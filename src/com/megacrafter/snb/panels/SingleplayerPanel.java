package com.megacrafter.snb.panels;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.SaveGame;
import com.megacrafter.snb.game.util.DynamicButton;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.game.util.PanelSlide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.megacrafter.snb.panels.GameLoadPanel.gameload_menu;
import static com.megacrafter.snb.panels.GameLoadPanel.loadGame;
import static com.megacrafter.snb.panels.GamePanel.game;

public class SingleplayerPanel {

    public static Menu singleplayer_menu = new Menu() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(80, 45, 640, 480);

            db1.render(g);
            db2.render(g);
            db3.render(g);
        }
    };

    private static Color spmenu_title_color = new Color(0, 100, 200);
    private static Color spmenu_bg_color = GUtils.AQUA;
    private static Font singleplayer_menu_button_font_2 = new Font("Arial", Font.BOLD, 24);

    private static DynamicButton db1 = new DynamicButton(singleplayer_menu, 90, 55, 200, 200, spmenu_bg_color, spmenu_title_color, "Kayıt 1", singleplayer_menu_button_font_2);
    private static DynamicButton db2 = new DynamicButton(singleplayer_menu, 300, 55, 200, 200, spmenu_bg_color, spmenu_title_color, "Kayıt 2", singleplayer_menu_button_font_2);
    private static DynamicButton db3 = new DynamicButton(singleplayer_menu, 510, 55, 200, 200, spmenu_bg_color, spmenu_title_color, "Kayıt 3", singleplayer_menu_button_font_2);

    private static JButton singleplayer_menu_back_button = new JButton("Geri");
    private static JButton singleplayer_menu_game1_button = new JButton();
    private static JButton singleplayer_menu_game2_button = new JButton();
    private static JButton singleplayer_menu_game3_button = new JButton();
    public static Object[] singleplayer_menu_yesno = new Object[]{"Evet", "Hayır"};
    public static Font singleplayer_menu_button_font = new Font("Arial", Font.BOLD, 16);

    public static void initSingleplayerMenu() {
        singleplayer_menu.setBounds(0, 0, 800, 600);
        singleplayer_menu.setVisible(false);
        singleplayer_menu.setOpaque(false);

        singleplayer_menu_back_button.setBounds(95, 460, 125, 50);
        singleplayer_menu_back_button.setFont(singleplayer_menu_button_font);
        singleplayer_menu_back_button.setBorderPainted(false);
        singleplayer_menu_back_button.setOpaque(false);
        singleplayer_menu_back_button.setFocusPainted(false);
        singleplayer_menu_back_button.addActionListener(e -> {
            PanelSlide.slideToLeft(singleplayer_menu, MainMenu.main_menu, -1, -1);
        });
        singleplayer_menu_back_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "backbutton1");
        singleplayer_menu_back_button.getActionMap().put("backbutton1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singleplayer_menu_back_button.doClick();
            }
        });

        singleplayer_menu_game1_button.setBounds(90, 55, 200, 200);
        singleplayer_menu_game1_button.setBorderPainted(false);
        singleplayer_menu_game1_button.setOpaque(false);
        singleplayer_menu_game1_button.setFocusPainted(false);
        singleplayer_menu_game1_button.setContentAreaFilled(false);
        singleplayer_menu_game1_button.setFont(singleplayer_menu_button_font_2);
        singleplayer_menu_game1_button.setForeground(Color.DARK_GRAY);
        singleplayer_menu_game1_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                db1.incSize();
                singleplayer_menu_game1_button.setBounds(db1.getX(), db1.getY(), db1.getWidth(), db1.getHeight());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                db1.decSize();
                singleplayer_menu_game1_button.setBounds(db1.getX(), db1.getY(), db1.getWidth(), db1.getHeight());
            }
        });
        singleplayer_menu_game1_button.addActionListener(e -> {
            if (SaveGame.isSaveEmpty(1)) {
                int pane = JOptionPane.showOptionDialog(null, "Yeni bir oyun başlatmak\nistediğinize emin misiniz?",
                        "Yeni Oyun", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, singleplayer_menu_yesno, 1);
                if (pane == 0) {
                    // Oyunu başlatma işlemleri
                    gameload_menu.forward();
                    EventQueue.invokeLater(() -> {
                        if (loadGame()) {
                            game.start(1);
                        }
                    });
                }
            } else {
                // Kayıttan devam işlemleri

            }
        });

        singleplayer_menu_game2_button.setBounds(300, 55, 200, 200);
        singleplayer_menu_game2_button.setBorderPainted(false);
        singleplayer_menu_game2_button.setOpaque(false);
        singleplayer_menu_game2_button.setFocusPainted(false);
        singleplayer_menu_game2_button.setContentAreaFilled(false);
        singleplayer_menu_game2_button.setFont(singleplayer_menu_button_font_2);
        singleplayer_menu_game2_button.setForeground(Color.DARK_GRAY);
        singleplayer_menu_game2_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                db2.incSize();
                singleplayer_menu_game2_button.setBounds(db2.getX(), db2.getY(), db2.getWidth(), db2.getHeight());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                db2.decSize();
                singleplayer_menu_game2_button.setBounds(db2.getX(), db2.getY(), db2.getWidth(), db2.getHeight());
            }
        });
        singleplayer_menu_game2_button.addActionListener(e -> {
            if (SaveGame.isSaveEmpty(2)) {
                int pane = JOptionPane.showOptionDialog(null, "Yeni bir oyun başlatmak\nistediğinize emin misiniz?",
                        "Yeni Oyun", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, singleplayer_menu_yesno, 1);
                if (pane == 0) {
                    // Oyunu başlatma işlemleri
                    gameload_menu.forward();
                    EventQueue.invokeLater(() -> {
                        if (loadGame()) {
                            game.start(2);
                        }
                    });
                }
            } else {
                // Kayıttan devam işlemleri

            }
        });

        singleplayer_menu_game3_button.setBounds(510, 55, 200, 200);
        singleplayer_menu_game3_button.setBorderPainted(false);
        singleplayer_menu_game3_button.setOpaque(false);
        singleplayer_menu_game3_button.setFocusPainted(false);
        singleplayer_menu_game3_button.setContentAreaFilled(false);
        singleplayer_menu_game3_button.setFont(singleplayer_menu_button_font_2);
        singleplayer_menu_game3_button.setForeground(Color.DARK_GRAY);
        singleplayer_menu_game3_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                db3.incSize();
                singleplayer_menu_game3_button.setBounds(db3.getX(), db3.getY(), db3.getWidth(), db3.getHeight());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                db3.decSize();
                singleplayer_menu_game3_button.setBounds(db3.getX(), db3.getY(), db3.getWidth(), db3.getHeight());
            }
        });
        singleplayer_menu_game3_button.addActionListener(e -> {
            if (SaveGame.isSaveEmpty(3)) {
                int pane = JOptionPane.showOptionDialog(null, "Yeni bir oyun başlatmak\nistediğinize emin misiniz?",
                        "Yeni Oyun", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, singleplayer_menu_yesno, 1);
                if (pane == 0) {
                    // Oyunu başlatma işlemleri
                    gameload_menu.forward();
                    EventQueue.invokeLater(() -> {
                        if (loadGame()) {
                            game.start(3);
                        }
                    });
                }
            } else {
                // Kayıttan devam işlemleri

            }
        });

        singleplayer_menu.add(singleplayer_menu_back_button);
        singleplayer_menu.add(singleplayer_menu_game1_button);
        singleplayer_menu.add(singleplayer_menu_game2_button);
        singleplayer_menu.add(singleplayer_menu_game3_button);
    }

}