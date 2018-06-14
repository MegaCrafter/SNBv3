package com.megacrafter.snb.panels.battle;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.battle.AIBPlayer;
import com.megacrafter.snb.game.battle.BPlayer;
import com.megacrafter.snb.game.battle.Battle;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.panels.GamePanel;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

import static com.megacrafter.snb.panels.GamePanel.game;
import static com.megacrafter.snb.panels.SingleplayerPanel.singleplayer_menu_button_font;

public class BattleConPanel {

    public static BPlayer p2 = new AIBPlayer(0);

    public static Menu battlecon_menu = new Menu() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = GUtils.setRenderingHints(g);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(20, 20, Display.WIDTH - 45, 460);

            g.setColor(GUtils.AQUA);
            g.fillRect(20, 20, Display.WIDTH - 45, 30);

            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Comic Sans", Font.BOLD, 20));
            int stringy = GUtils.getCenteredTextY(20, 22, g);
            g2d.drawString("Ayarlar", 24, stringy + 6);
            g.drawLine(20, 50, Display.WIDTH - 25, 50);

            g.setColor(GUtils.AQUA);

            g.setFont(singleplayer_menu_button_font);
            int textx = GUtils.getCenteredTextX(30, 70, g, "Sen");
            int texty = GUtils.getCenteredTextY(445 - 17, 17, g);
            g2d.drawString("Sen", textx, texty);
            g.drawLine(30, 445, 100, 445);

            int textx2 = GUtils.getCenteredTextX(686, 70, g, "Rakip");
            int texty2 = GUtils.getCenteredTextY(445 - 17, 17, g);
            g2d.drawString("Rakip", textx2, texty2);
            g.drawLine(685, 445, 755, 445);

            g2d.setFont(new Font("Arial", Font.PLAIN, 16));
            g2d.drawString("Kristal Başlangıcı:", 28, 145);
            g2d.drawString("Maksimum Kristal:", 28, 225);
            g2d.drawString("Tur Sayısı:", 28, 305);
            g2d.drawString("Tur Arası Bekleme:", 328, 145);

            if (BattlePanel.battle != null && BattlePanel.battle.countdown <= Battle.countdown_start) {
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRect(0, 0, Display.WIDTH, Display.HEIGHT);

                g2d.setFont(new Font("Arial", Font.BOLD, 43));
                g2d.setColor(GUtils.AQUA);

                int textx3 = GUtils.getCenteredTextX(20, Display.WIDTH - 45, g2d, "" + BattlePanel.battle.countdown);
                int texty3 = GUtils.getCenteredTextY(20, Display.HEIGHT - 70, g2d);

                g2d.drawString("" + BattlePanel.battle.countdown, textx3, texty3);
                GUtils.drawStringOutline(g2d, textx3, texty3, "" + BattlePanel.battle.countdown, 0.3f);
            }
        }
    };
    private static JButton battlecon_cancel_btn = new JButton("İptal");
    private static JCheckBox battlecon_ready_box_player = new JCheckBox("Hazır");
    private static JCheckBox battlecon_ready_box_enemy = new JCheckBox("Hazır");

    private static JComboBox<String> battlecon_battle_type = new JComboBox<>();
    private static String[] battlecon_battletypes = {"Klasik Savaş"};
    private static JTextField battlecon_start_gem = new JTextField();
    private static JTextField battlecon_max_gem = new JTextField();
    private static JTextField battlecon_lines = new JTextField();
    private static JTextField battlecon_breaktime = new JTextField();

    public static void initBattleConMenu() {
        battlecon_menu.setBounds(0, 0, Display.WIDTH, Display.HEIGHT);
        battlecon_menu.setBackground(Color.WHITE);

        battlecon_cancel_btn.setBounds(18, 490, Display.WIDTH - 42, 70);
        battlecon_cancel_btn.setFont(singleplayer_menu_button_font);
        battlecon_cancel_btn.setOpaque(false);
        battlecon_cancel_btn.setBorderPainted(false);
        battlecon_cancel_btn.setFocusPainted(false);
        battlecon_cancel_btn.addActionListener(e -> {
            game.resume();
        });

        battlecon_ready_box_player.setBounds(35, 450, 200, 20);
        battlecon_ready_box_player.setFont(new Font("Arial", Font.PLAIN, 15));
        battlecon_ready_box_player.setForeground(Color.WHITE);
        battlecon_ready_box_player.setFocusPainted(false);
        battlecon_ready_box_player.setBorderPainted(false);
        battlecon_ready_box_player.setOpaque(false);
        battlecon_ready_box_player.addActionListener(e -> {
            if (battlecon_ready_box_player.isSelected() && battlecon_ready_box_enemy.isSelected()) {
                BattlePanel.battle = new Battle(GamePanel.game.player.battleplayer, p2);

                int startgem = Integer.parseInt(battlecon_start_gem.getText());
                int maxgem = Integer.parseInt(battlecon_max_gem.getText());
                int lines = Integer.parseInt(battlecon_lines.getText());
                BattlePanel.battle.init(startgem, maxgem, lines);

                battlecon_battle_type.setEnabled(false);
                battlecon_start_gem.setEnabled(false);
                battlecon_max_gem.setEnabled(false);
                battlecon_lines.setEnabled(false);
                battlecon_breaktime.setEnabled(false);
                battlecon_cancel_btn.setEnabled(false);

                BattlePanel.breaktime = Integer.parseInt(battlecon_breaktime.getText());
                BattlePanel.battle.startCountdown();
            } else {
                battlecon_battle_type.setEnabled(true);
                battlecon_start_gem.setEnabled(true);
                battlecon_max_gem.setEnabled(true);
                battlecon_lines.setEnabled(true);
                battlecon_breaktime.setEnabled(true);
                battlecon_cancel_btn.setEnabled(true);

                BattlePanel.battle.stopCountdown();
            }
        });

        battlecon_ready_box_enemy.setBounds(690, 450, 200, 20);
        battlecon_ready_box_enemy.setFont(new Font("Arial", Font.PLAIN, 15));
        battlecon_ready_box_enemy.setForeground(Color.WHITE);
        battlecon_ready_box_enemy.setFocusPainted(false);
        battlecon_ready_box_enemy.setBorderPainted(false);
        battlecon_ready_box_enemy.setOpaque(false);
        battlecon_ready_box_enemy.setEnabled(false);
        //////////////////////////////////////////////////// TODO: DELETE
        battlecon_ready_box_enemy.setSelected(true);
        ////////////////////////////////////////////////////

        battlecon_battle_type.setBounds(27, 70, 150, 30);
        for (String s : battlecon_battletypes) {
            battlecon_battle_type.addItem(s);
        }
        battlecon_battle_type.setOpaque(false);
        battlecon_battle_type.setFocusable(false);

        battlecon_start_gem.setBounds(27, 150, 210, 30);
        battlecon_start_gem.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String insert = "";
                for (char c : str.toCharArray()) {
                    if (Character.isDigit(c)) insert += c;
                }

                while (battlecon_start_gem.getText().length() + insert.length() > 4) {
                    insert = insert.substring(0, insert.length() - 1);
                }

                super.insertString(offs, insert, a);
            }
        });

        battlecon_max_gem.setBounds(27, 230, 210, 30);
        battlecon_max_gem.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String insert = "";
                for (char c : str.toCharArray()) {
                    if (Character.isDigit(c)) insert += c;
                }

                while (battlecon_max_gem.getText().length() + insert.length() > 4) {
                    insert = insert.substring(0, insert.length() - 1);
                }
                super.insertString(offs, insert, a);
            }
        });

        battlecon_lines.setBounds(27, 310, 210, 30);
        battlecon_lines.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String insert = "";
                for (char c : str.toCharArray()) {
                    if (Character.isDigit(c) && c != '0') insert += c;
                }

                while (battlecon_lines.getText().length() + insert.length() > 1) {
                    insert = insert.substring(0, insert.length() - 1);
                }

                super.insertString(offs, insert, a);
            }
        });

        battlecon_breaktime.setBounds(327, 150, 210, 30);
        battlecon_breaktime.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String insert = "";
                for (char c : str.toCharArray()) {
                    if (Character.isDigit(c)) insert += c;
                }

                while (battlecon_breaktime.getText().length() + insert.length() > 4) {
                    insert = insert.substring(0, insert.length() - 1);
                }

                super.insertString(offs, insert, a);
            }
        });

        battlecon_menu.add(battlecon_cancel_btn);
        battlecon_menu.add(battlecon_ready_box_player);
        battlecon_menu.add(battlecon_ready_box_enemy);

        battlecon_menu.add(battlecon_battle_type);
        battlecon_menu.add(battlecon_start_gem);
        battlecon_menu.add(battlecon_max_gem);
        battlecon_menu.add(battlecon_lines);
        battlecon_menu.add(battlecon_breaktime);
    }

    public static void resetComponents() {
        battlecon_ready_box_player.setSelected(false);
        battlecon_battle_type.setSelectedIndex(0);
        battlecon_start_gem.setText("500");
        battlecon_max_gem.setText("5000");
        battlecon_lines.setText("3");
        battlecon_breaktime.setText("10");

        battlecon_battle_type.setEnabled(true);
        battlecon_start_gem.setEnabled(true);
        battlecon_max_gem.setEnabled(true);
        battlecon_lines.setEnabled(true);
        battlecon_breaktime.setEnabled(true);
        battlecon_cancel_btn.setEnabled(true);
    }

}