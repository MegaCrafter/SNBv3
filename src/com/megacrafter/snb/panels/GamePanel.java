package com.megacrafter.snb.panels;

import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.Game;

public class GamePanel {

    public static Game game;

    public static void initGame() {
        game = new Game();
        game.setVisible(false);
        game.setBounds(0, 0, Display.WIDTH, Display.HEIGHT);
    }

}