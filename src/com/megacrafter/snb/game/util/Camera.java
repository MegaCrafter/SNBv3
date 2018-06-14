package com.megacrafter.snb.game.util;

import com.megacrafter.snb.panels.GamePanel;

public class Camera {

    public boolean terminated = false;
    private int movespeed = 2;

    public int x = 0;
    public int y = 0;

    public void tick() {
        if (!terminated) {
            if (GamePanel.game.player.x < GamePanel.game.startx && GamePanel.game.tiles[0][0].getScreenX() != 0) {
                move(-movespeed, 0);
            }
            if (GamePanel.game.player.y < GamePanel.game.starty && GamePanel.game.tiles[0][0].getScreenY() != 0) {
                move(0, -movespeed);
            }

            if (GamePanel.game.player.x > GamePanel.game.startx && !(GamePanel.game.tiles[GamePanel.game.tiles.length - 1][GamePanel.game.tiles[0].length - 1].getScreenX() < 800-37)) {
                move(movespeed, 0);
            }

            if (GamePanel.game.player.y > GamePanel.game.starty && !(GamePanel.game.tiles[GamePanel.game.tiles.length - 1][GamePanel.game.tiles[0].length - 1].getScreenY() < 600-58)) {
                move(0, movespeed);
            }
        }
    }

    public void move(int xvel, int yvel) {
        x += xvel;
        y += yvel;

        if (xvel == 0) GamePanel.game.player.y = GamePanel.game.starty;
        if (yvel == 0) GamePanel.game.player.x = GamePanel.game.startx;
    }
}