package com.megacrafter.snb.panels;

import com.megacrafter.programapi.Menu;
import com.megacrafter.programapi.Program;
import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.game.util.images.Assets;
import com.megacrafter.snb.game.util.images.ImageUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

import static com.megacrafter.snb.panels.MainMenu.main_menu_button_font;

public class LoadingPanel {

    public static Menu loading_menu = new Menu() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics g2d = GUtils.setRenderingHints(g);
            g2d.drawImage(ImageUtils.loadImage("/bg_loading.png"), 0, 0, Display.LOADING_WIDTH, Display.LOADING_HEIGHT, null);

//            g2d.setFont(new Font("Arial", Font.BOLD, 30));
//            g2d.setColor(GUtils.AQUA);
//            g2d.drawString("Yükleniyor...", 100, 230);
//            GUtils.drawStringOutline(g2d, 100, 230, "Yükleniyor...", 0.3f);

//            GUtils.drawOutline(g, 3, 3, new Rectangle(0, 0, Display.LOADING_WIDTH - 6, Display.LOADING_HEIGHT - 6), 3f);
        }
    };
    private static JProgressBar loading_menu_pbar = new JProgressBar(0, 0);

    public static void initLoadingMenu() {
        Program.getPanels().remove(loading_menu);

        loading_menu.setBounds(0, 0, Display.LOADING_WIDTH, Display.LOADING_HEIGHT);
        loading_menu.setOpaque(false);

        loading_menu_pbar.setUI(new GProgressBar());
        loading_menu_pbar.setForeground(new Color(150, 163, 157));

        loading_menu_pbar.setBounds(100, 410, Display.LOADING_WIDTH - 100 * 2, 8);
        loading_menu_pbar.setBorderPainted(false);
        loading_menu_pbar.setOpaque(false);
        loading_menu_pbar.setStringPainted(true);
        loading_menu_pbar.setFont(main_menu_button_font);

        loading_menu.add(loading_menu_pbar);
    }

    public static boolean loadStuff() {
        loading_menu_pbar.setMaximum(Assets.count);

        for (int i = 0; i < loading_menu_pbar.getMaximum() + 800000; i++) {
            Assets.load(i + 1);
            loading_menu_pbar.setValue(i + 1);
            loading_menu.repaint();
        }

        return true;
    }

    private static class GProgressBar extends BasicProgressBarUI {

        private Rectangle r = new Rectangle();

        @Override
        protected void paintIndeterminate(Graphics g, JComponent c) {
            Graphics2D g2d = GUtils.setRenderingHints(g);
            r = getBox(r);
            g2d.setColor(progressBar.getBackground());
            g2d.fillOval(r.x, r.y, r.width, r.height);
        }

        @Override
        protected void paintString(Graphics g, int x, int y, int width, int height, int amountFull, Insets b) {}
    }

}