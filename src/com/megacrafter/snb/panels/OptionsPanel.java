package com.megacrafter.snb.panels;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.game.util.PanelSlide;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static com.megacrafter.snb.panels.GamePanel.game;
import static com.megacrafter.snb.panels.PausePanel.al_backtopausemenu;

public class OptionsPanel {

    static Menu options_menu = new Menu() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = GUtils.setRenderingHints(g);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(40, 40, Display.WIDTH - 90, Display.HEIGHT - 110);

            g.setColor(GUtils.AQUA);
            g.setFont(new Font("Arial", Font.BOLD, 17));
            g2d.drawString("Görünen İsim:", 65, 75);

            GUtils.drawStringOutline(g2d, 65, 75, "Görünen İsim:", 0.3f);
        }
    };
    public static JTextField options_nickname_field = new JTextField();
    public static JButton options_back_button_tomainmenu = new JButton("Geri");
    public static JButton options_back_button_topausemenu = new JButton("Geri");
    private static ActionListener al_backtomainmenu = e -> {
        game.playername = options_nickname_field.getText();
        if (game.player != null) {
            game.player.setName(game.playername);
        }
        PanelSlide.slideToRight(options_menu, MainMenu.main_menu, -1, -1);
    };
    /* OPTIONS MENU VARIABLES */

    public static void initOptionsMenu() {
        options_menu.setBounds(0, 0, Display.WIDTH, Display.HEIGHT);
        options_menu.setOpaque(false);

        options_nickname_field.setBounds(65, 83, 200, 25);
        options_nickname_field.setFont(new Font("Arial", Font.PLAIN, 15));
        options_nickname_field.setDocument(new PlainDocument() {
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                while (!(str.length() + options_nickname_field.getText().length() <= 16)) {
                    if (str.length() != 0) {
                        str = str.substring(0, str.length() - 1);
                    } else break;
                }

                String newstr = "";

                for (char c : str.toCharArray()) {
                    if ((Character.isDigit(c) || Character.isLetter(c) || c == '_')) {
                        newstr = newstr + c;
                    }
                }

                super.insertString(offs, newstr, a);
            }
        });

        options_back_button_topausemenu.setBounds(65, 460, 125, 50);
        options_back_button_topausemenu.setFont(SingleplayerPanel.singleplayer_menu_button_font);
        options_back_button_topausemenu.setOpaque(false);
        options_back_button_topausemenu.setBorderPainted(false);
        options_back_button_topausemenu.setFocusPainted(false);
        options_back_button_topausemenu.addActionListener(al_backtopausemenu);
        options_back_button_topausemenu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "backbutton2");
        options_back_button_topausemenu.getActionMap().put("backbutton2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options_back_button_topausemenu.doClick();
            }
        });

        options_back_button_tomainmenu.setBounds(65, 460, 125, 50);
        options_back_button_tomainmenu.setFont(SingleplayerPanel.singleplayer_menu_button_font);
        options_back_button_tomainmenu.setOpaque(false);
        options_back_button_tomainmenu.setBorderPainted(false);
        options_back_button_tomainmenu.setFocusPainted(false);
        options_back_button_tomainmenu.addActionListener(al_backtomainmenu);
        options_back_button_tomainmenu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "backbutton2");
        options_back_button_tomainmenu.getActionMap().put("backbutton2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options_back_button_tomainmenu.doClick();
            }
        });

        options_menu.add(options_nickname_field);
        options_menu.add(options_back_button_tomainmenu);
    }

}