package com.megacrafter.snb.panels;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.util.GUtils;

import java.awt.*;

import static com.megacrafter.snb.panels.GamePanel.game;

public class GameLoadPanel {

    private static String text = "Dünya Oluşturuluyor...";
    static Menu gameload_menu = new Menu() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics g2d = GUtils.setRenderingHints(g);

            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setColor(GUtils.AQUA);

            int textx = GUtils.getCenteredTextX(0, Display.WIDTH, g2d, text);
            int texty = GUtils.getCenteredTextY(0, Display.HEIGHT - 15, g2d);

            g2d.drawString(text, textx, texty);
            GUtils.drawStringOutline(g2d, textx, texty, text, 0.3f);
        }
    };

    public static void initGameLoadPanel() {
        gameload_menu.setBounds(0, 0, Display.WIDTH, Display.HEIGHT);
        gameload_menu.setBackground(Color.WHITE);
    }

    public static boolean loadGame() {
        for (int x = 0; x < game.tiles.length; x++) {
            for (int y = 0; y < game.tiles[0].length; y++) {
                game.generateTile(x, y);
            }
        }
        game.init();

        return true;
    }

}