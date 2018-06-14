package com.megacrafter.snb.panels;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.game.util.GUtils;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import static com.megacrafter.snb.panels.GamePanel.game;

public class LauncherPanel {

    public static Menu launcher = new Menu() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = GUtils.setRenderingHints(g);

            g2d.setColor(new Color(0, 210, 225, 200));
            g2d.fillRect(260, 165, 280, 245);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(270, 175, 260, 225);

            g.setColor(GUtils.AQUA);
            g.setFont(new Font("Arial", Font.BOLD, 15));

            String username = "Kullanıcı Adı:";
            String password = "Şifre:";

            g2d.drawString(username, 300, 210);
            g2d.drawString(password, 300, 270);

            GUtils.drawOutline(g2d, 260, 165, new Rectangle(0, 0, 280, 245), 0.7f);
            GUtils.drawStringOutline(g2d, 300, 210, username, 0.2f);
            GUtils.drawStringOutline(g2d, 300, 270, password, 0.2f);
        }
    };
    public static JTextField launcher_username_txtf = new JTextField();
    private static JPasswordField launcher_password_pasf = new JPasswordField();
    private static JComboBox<String> launcher_version_cbox = new JComboBox<>();
    private static JButton launcher_login_btn = new JButton("Giriş");
    private static Font launcher_txtf_font = new Font("Arial", Font.PLAIN, 20);

    public static void initLauncher() {
        launcher.makeMainMenu();
        launcher.setBounds(0, 0, 800, 600);
        launcher.setOpaque(false);

        launcher_username_txtf.setBounds(300, 215, 200, 30);
        launcher_username_txtf.setFont(launcher_txtf_font);
        launcher_username_txtf.setDocument(new PlainDocument() {
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                while (!(str.length() + launcher_username_txtf.getText().length() <= 16)) {
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

                String pass = "";
                for (char ch : launcher_password_pasf.getPassword()) {
                    pass += ch;
                }

                if (pass.isEmpty() || launcher_username_txtf.getText().isEmpty())
                    launcher_login_btn.setEnabled(false);
                else
                    launcher_login_btn.setEnabled(true);
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                if (Arrays.asList(launcher_password_pasf.getPassword()).isEmpty() || launcher_username_txtf.getText().isEmpty())
                    launcher_login_btn.setEnabled(false);
            }
        });

        launcher_password_pasf.setBounds(300, 275, 200, 30);
        launcher_password_pasf.setFont(launcher_txtf_font);
        launcher_password_pasf.setEchoChar('*');
        launcher_password_pasf.setDocument(new PlainDocument() {
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                while (!(str.length() + launcher_password_pasf.getPassword().length <= 15)) {
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

                String pass = "";
                for (char ch : launcher_password_pasf.getPassword()) {
                    pass += ch;
                }
                if (pass.isEmpty() || launcher_username_txtf.getText().isEmpty())
                    launcher_login_btn.setEnabled(false);
                else
                    launcher_login_btn.setEnabled(true);
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String pass = "";
                for (char ch : launcher_password_pasf.getPassword()) {
                    pass += ch;
                }

                if (pass.isEmpty() || launcher_username_txtf.getText().isEmpty())
                    launcher_login_btn.setEnabled(false);
            }
        });

        launcher_login_btn.setBounds(300, 330, 200, 50);
        launcher_login_btn.setFont(MainMenu.main_menu_button_font);
        launcher_login_btn.setBorderPainted(false);
        launcher_login_btn.setOpaque(false);
        launcher_login_btn.setEnabled(false);
        launcher_login_btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "login");
        launcher_login_btn.getActionMap().put("login", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launcher_login_btn.doClick();
            }
        });
        launcher_login_btn.addActionListener(e -> {
            // TODO: Username ve Password kontrol
            OptionsPanel.options_nickname_field.setText(launcher_username_txtf.getText());
            game.playername = launcher_username_txtf.getText();
            MainMenu.main_menu.forward();
        });

        launcher.add(launcher_username_txtf);
        launcher.add(launcher_password_pasf);
        launcher.add(launcher_version_cbox);
        launcher.add(launcher_login_btn);
    }

}