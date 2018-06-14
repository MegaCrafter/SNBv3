package com.megacrafter.snb.panels.battle;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.battle.Attack;
import com.megacrafter.snb.game.battle.Battle;
import com.megacrafter.snb.game.battle.Defense;
import com.megacrafter.snb.game.battle.gui.GButton;
import com.megacrafter.snb.game.battle.gui.GPanel;
import com.megacrafter.snb.game.util.GTimer;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.game.util.audio.Sound;
import com.megacrafter.snb.game.util.images.Assets;
import com.megacrafter.snb.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BattlePanel {

    public static Battle battle = null;

    private static boolean defensebtn_hovered = false;
    private static boolean powerbtn_hovered = false;
    private static boolean attackbtn_hovered = false;
    private static int _selected = 0;

    public static int breaktime = 10;
    private static int timer = -1;
    private static int ending_msg_fade = 0;
    public static Menu battlemenu = new Menu() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = GUtils.setRenderingHints(g);

            //////////////////////////////////////////////////////////////////////////
            // TODO: Panel Background
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(0, 0, Display.WIDTH, Display.HEIGHT);
            //////////////////////////////////////////////////////////////////////////

            //////////////////////////////////////////////////////////////////////////
            // TODO: Sidepanel Background
            g2d.setColor(Color.BLACK);

            g2d.fillRect(Display.WIDTH-170, 0, 170, Display.HEIGHT);
            //////////////////////////////////////////////////////////////////////////

            //////////////////////////////////////////////////////////////////////////
            // TODO: Triangles
            g2d.setColor(Color.DARK_GRAY);

            int[] x1 = {665, 665, 658};
            int[] y1 = {18, 52, 52};
            g2d.fillPolygon(x1, y1, 3);

            int[] x2 = {759, 759, 766};
            int[] y2 = {18, 52, 52};
            g2d.fillPolygon(x2, y2, 3);

            g2d.fillRect(665, 18, 94, 34);
            //////////////////////////////////////////////////////////////////////////

            //////////////////////////////////////////////////////////////////////////
            // TODO: Defense - Power - Attack Button Images
            g2d.drawImage((defensebtn_hovered || _selected == 1) ? Assets.spr_defensebtn_action : Assets.spr_defensebtn, 667, 20, null);
            g2d.drawImage((powerbtn_hovered || _selected == 2) ? Assets.spr_powerbtn_action : Assets.spr_powerbtn, 697, 20, null);
            g2d.drawImage((attackbtn_hovered || _selected == 3) ? Assets.spr_attackbtn_action : Assets.spr_attackbtn, 727, 20, null);
            //////////////////////////////////////////////////////////////////////////

            //////////////////////////////////////////////////////////////////////////
            // TODO: Sidepanel AQUA Seperators
            g2d.setColor(GUtils.AQUA);

            g2d.setStroke(new BasicStroke(3.0f));

            g2d.drawLine(Display.WIDTH-170, 0, Display.WIDTH-170, Display.HEIGHT);
            g2d.drawLine(Display.WIDTH-169, 52, Display.WIDTH, 52);

            g2d.setStroke(new BasicStroke(1.3f));

            g2d.drawLine(658, 52, 665, 18);
            g2d.drawLine(665, 18, 759, 18);
            g2d.drawLine(759, 18, 766, 52);

            g2d.drawLine(Display.WIDTH - 169, Display.HEIGHT - 62, Display.WIDTH, Display.HEIGHT - 62);

            g2d.setStroke(new BasicStroke(0f));
            //////////////////////////////////////////////////////////////////////////

            //////////////////////////////////////////////////////////////////////////
            // TODO: Round Counter Boxes
            g2d.setColor(GUtils.AQUA);

//            int boardx = GUtils.getCenteredRectX(0, Display.WIDTH, 80);

            int boardx = GUtils.getCenteredRectX(0, Display.WIDTH - 170, 80);

            g2d.setStroke(new BasicStroke(1.3f));

            g2d.drawRect(boardx, -1, 80, 25);
            g2d.drawRect(boardx, 24, 40, 25);
            g2d.drawRect(boardx + 40, 24, 40, 25);

            g2d.setStroke(new BasicStroke(0f));
            //////////////////////////////////////////////////////////////////////////

            try {
                //////////////////////////////////////////////////////////////////////////
                // TODO: Round Counter Numbers
                g2d.setColor(GUtils.AQUA);

                g2d.setFont(new Font("Arial", Font.BOLD, 20));

                int wins_textx1 = GUtils.getCenteredTextX(boardx, 40, g2d, "" + battle.p1.getWins());
                int wins_textx2 = GUtils.getCenteredTextX(boardx + 40, 40, g2d, "" + battle.p2.getWins());
                int wins_textx3 = GUtils.getCenteredTextX(boardx, 80, g2d, (battle.rounds - battle.rem_rounds) + " / " + battle.rounds);

                int wins_texty1 = GUtils.getCenteredTextY(24, 25, g2d);
                int wins_texty2 = GUtils.getCenteredTextY(-1, 25, g2d);

                g2d.drawString((battle.rounds - battle.rem_rounds) + " / " + battle.rounds, wins_textx3, wins_texty2 + 4);
                g2d.drawString("" + battle.p1.getWins(), wins_textx1, wins_texty1 + 5);
                g2d.drawString("" + battle.p2.getWins(), wins_textx2, wins_texty1 + 5);

                GUtils.drawStringOutline(g2d, wins_textx3, wins_texty2+4, (battle.rounds - battle.rem_rounds) + " / " + battle.rounds, 0.2f);
                GUtils.drawStringOutline(g2d, wins_textx1, wins_texty1+5, "" + battle.p1.getWins(), 0.2f);
                GUtils.drawStringOutline(g2d, wins_textx2, wins_texty1+5, "" + battle.p2.getWins(), 0.2f);
                //////////////////////////////////////////////////////////////////////////
            } catch (NullPointerException ignored) {}

            if (battle != null && battle.getWinner() != null) {
                //////////////////////////////////////////////////////////////////////////
                // TODO: Fade In
                g2d.setColor(new Color(0, 0, 0, ending_msg_fade));
                g2d.fillRect(0, 0, Display.WIDTH, Display.HEIGHT);
                if (ending_msg_fade < 100) {
                    ending_msg_fade++;
                    battlemenu.repaint();
                }
                //////////////////////////////////////////////////////////////////////////

                //////////////////////////////////////////////////////////////////////////
                // TODO: Round/Match Ending
                g2d.setFont(new Font("Arial", Font.BOLD, 43));
                String winmsg;
                String winmsg2;

                if (battle.getWinner().isProtagonist()) {
                    if (battle.isActive()) winmsg = "Turu Sen Kazandın!";
                    else winmsg = "Maçı Sen Kazandın!";

                    g2d.setColor(Color.GREEN);
                } else if (battle.getWinner() == Battle.DRAW) {
                    winmsg = "Maç Berabere!";
                    g2d.setColor(Color.CYAN);
                } else {
                    if (battle.isActive()) winmsg = "Turu Rakip Kazandı!";
                    else winmsg = "Maçı Rakip Kazandı!";
                    g2d.setColor(Color.RED);
                }

                if (timer == -1) {
                    if (!battle.isActive()) {
                        GTimer.setFinally(() -> {
                            GamePanel.game.resume();
                        });
                        timer = 4;
                    } else {
                        GTimer.setFinally(() -> {
                            battle.startRound();
                        });
                        timer = breaktime;
                    }
                }

                if (battle.isActive()) winmsg2 = "Yeni Tur Başlıyor: " + timer;
                else winmsg2 = "Maç Bitiyor: " + timer;

//                int textx1 = GUtils.getCenteredTextX(20, Display.WIDTH - 190, g2d, winmsg);
//                int textx2 = GUtils.getCenteredTextX(20, Display.WIDTH - 190, g2d, winmsg2);

                int textx1 = GUtils.getCenteredTextX(20, Display.WIDTH - 45, g2d, winmsg);
                int textx2 = GUtils.getCenteredTextX(20, Display.WIDTH - 45, g2d, winmsg2);
                int texty = GUtils.getCenteredTextY(20, Display.HEIGHT - 70, g2d, winmsg);
                int height = g2d.getFontMetrics().getHeight();

                g2d.drawString(winmsg, textx1, texty - height/2 - 2);
                g2d.drawString(winmsg2, textx2, texty + height/2 + 2);

                GUtils.drawStringOutline(g2d, textx1, texty - height/2 - 2, winmsg, 0.4f);
                GUtils.drawStringOutline(g2d, textx2, texty + height/2 + 2, winmsg2, 0.4f);

                GTimer.set(60, () -> {
                    if (timer <= 1) {
                        timer = -1;
                        GTimer.end();
                    } else {
                        timer--;
                        battlemenu.repaint();
                    }
                });
                //////////////////////////////////////////////////////////////////////////
            } else ending_msg_fade = 0;
        }
    };

    public static JButton battlemenu_defense_btn = new JButton();
    public static JButton battlemenu_power_btn = new JButton();
    public static JButton battlemenu_attack_btn = new JButton();
    public static Font battlemenu_btn_font = new Font("Arial", Font.BOLD, 20);

    public static JButton battlemenu_activate_btn = new JButton("Saldır");

    public static ArrayList<GPanel> battlemenu_panels = new ArrayList<>();
    public static GPanel battlemenu_defenses = new GPanel();
    public static GPanel battlemenu_powers = new GPanel();
    public static GPanel battlemenu_attackpowers = new GPanel();
    public static GPanel battlemenu_areapowers = new GPanel();
    public static GPanel battlemenu_buildpowers = new GPanel();
    public static GPanel battlemenu_upgradepowers = new GPanel();
    public static GPanel battlemenu_fieldpowers = new GPanel();
    public static GPanel battlemenu_bcpowers = new GPanel();
    public static GPanel battlemenu_attacks = new GPanel();

    public static void initBattlePanel() {
        battlemenu.setBounds(0, 0, Display.WIDTH, Display.HEIGHT);

        battlemenu_panels.add(battlemenu_defenses);
        battlemenu_panels.add(battlemenu_powers);
        battlemenu_panels.add(battlemenu_attackpowers);
        battlemenu_panels.add(battlemenu_areapowers);
        battlemenu_panels.add(battlemenu_buildpowers);
        battlemenu_panels.add(battlemenu_upgradepowers);
        battlemenu_panels.add(battlemenu_fieldpowers);
        battlemenu_panels.add(battlemenu_bcpowers);
        battlemenu_panels.add(battlemenu_attacks);

        for (GPanel panel : battlemenu_panels) {
            panel.setBounds(Display.WIDTH - 169, 53, 169, Display.HEIGHT - 117);
            panel.setBackground(Color.BLACK);
        }


        Sound click_1 = new Sound("/Click.wav");
        Sound click_2 = new Sound("/Click.wav");
        Sound click_3 = new Sound("/Click.wav");

        battlemenu_defense_btn = GUtils.attachKey(battlemenu_defense_btn, KeyEvent.VK_1);
        battlemenu_power_btn = GUtils.attachKey(battlemenu_power_btn, KeyEvent.VK_2);
        battlemenu_attack_btn = GUtils.attachKey(battlemenu_attack_btn, KeyEvent.VK_3);

        battlemenu_defense_btn.setBounds(667, 20, 30, 30);
        battlemenu_defense_btn.setFont(battlemenu_btn_font);
        battlemenu_defense_btn.setFocusPainted(false);
        battlemenu_defense_btn.setBorderPainted(false);
        battlemenu_defense_btn.setOpaque(false);
        battlemenu_defense_btn.setContentAreaFilled(false);
        battlemenu_defense_btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                defensebtn_hovered = true;
                battlemenu.repaint();
                click_1.play();
            }
            public void mouseExited(MouseEvent e) {
                defensebtn_hovered = false;
                battlemenu.repaint();
            }
            public void mousePressed(MouseEvent e) {
                defensebtn_hovered = true;
                battlemenu.repaint();
            }
            public void mouseReleased(MouseEvent e) {
                defensebtn_hovered = false;
                battlemenu.repaint();
            }
        });
        battlemenu_defense_btn.addActionListener(e -> {
            hideLists();
            battlemenu_defenses.setVisible(true);
            _selected = 1;
        });

        battlemenu_power_btn.setBounds(697, 20, 30, 30);
        battlemenu_power_btn.setFont(battlemenu_btn_font);
        battlemenu_power_btn.setFocusPainted(false);
        battlemenu_power_btn.setBorderPainted(false);
        battlemenu_power_btn.setOpaque(false);
        battlemenu_power_btn.setContentAreaFilled(false);
        battlemenu_power_btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                powerbtn_hovered = true;
                battlemenu.repaint();
                click_2.play();
            }
            public void mouseExited(MouseEvent e) {
                powerbtn_hovered = false;
                battlemenu.repaint();
            }
            public void mousePressed(MouseEvent e) {
                powerbtn_hovered = true;
                battlemenu.repaint();
            }
            public void mouseReleased(MouseEvent e) {
                powerbtn_hovered = false;
                battlemenu.repaint();
            }
        });
        battlemenu_power_btn.addActionListener(e -> {
            hideLists();
            battlemenu_powers.setVisible(true);
            _selected = 2;
        });

        battlemenu_attack_btn.setBounds(727, 20, 30, 30);
        battlemenu_attack_btn.setFont(battlemenu_btn_font);
        battlemenu_attack_btn.setFocusPainted(false);
        battlemenu_attack_btn.setBorderPainted(false);
        battlemenu_attack_btn.setOpaque(false);
        battlemenu_attack_btn.setContentAreaFilled(false);
        battlemenu_attack_btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                attackbtn_hovered = true;
                battlemenu.repaint();
                click_3.play();
            }
            public void mouseExited(MouseEvent e) {
                attackbtn_hovered = false;
                battlemenu.repaint();
            }
            public void mousePressed(MouseEvent e) {
                attackbtn_hovered = true;
                battlemenu.repaint();
            }
            public void mouseReleased(MouseEvent e) {
                attackbtn_hovered = false;
                battlemenu.repaint();
            }
        });
        battlemenu_attack_btn.addActionListener(e -> {
            hideLists();
            battlemenu_attacks.setVisible(true);
            _selected = 3;
        });

        battlemenu_activate_btn.setBounds(Display.WIDTH - 167, Display.HEIGHT - 59, 160, 30);
        battlemenu_activate_btn.setFont(battlemenu_btn_font);
        battlemenu_activate_btn.setFocusPainted(false);
        battlemenu_activate_btn.setBorderPainted(false);
        battlemenu_activate_btn.setOpaque(false);
        battlemenu_activate_btn.setContentAreaFilled(true);
        battlemenu_activate_btn.addActionListener(e -> {
            if (battle != null && battle.turn != null && battle.turn.attack != null) {
                battle.turn.attack.activate();
            }
        });

        battlemenu.add(battlemenu_attack_btn);
        battlemenu.add(battlemenu_power_btn);
        battlemenu.add(battlemenu_defense_btn);
        battlemenu.add(battlemenu_activate_btn);

        battlemenu_panels.forEach(battlemenu::add);
        hideLists();
    }

    public static void initLists() {
        battlemenu_defenses.removeButtons();
        for (Defense def : GamePanel.game.player.battleplayer.getDefenses()) {
            battlemenu_defenses.addButton(new GButton(def));
        }

        battlemenu_attacks.removeButtons();
        for (Attack atk : GamePanel.game.player.battleplayer.getAttacks()) {
            battlemenu_attacks.addButton(new GButton(atk));
        }

        battlemenu.repaint();
    }

    private static void hideLists() {
        for (GPanel panel : battlemenu_panels) panel.setVisible(false);
    }
}