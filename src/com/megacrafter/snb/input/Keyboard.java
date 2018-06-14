package com.megacrafter.snb.input;

import com.megacrafter.snb.game.mobs.Player;
import com.megacrafter.snb.game.tiles.Tile;
import com.megacrafter.snb.game.util.TileUtils;
import com.megacrafter.snb.panels.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {

    public static boolean[] keys = new boolean[256];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

        keyClicked(e);
    }

    public void tick() {
    }

    public void keyClicked(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (!GamePanel.game.paused) GamePanel.game.pause();
            else GamePanel.game.resume();
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            Tile up = new Tile();
            Tile down = new Tile();
            Tile right = new Tile();
            Tile left = new Tile();


            try {
                up = TileUtils.getTileAt(GamePanel.game.player.getTileX(), GamePanel.game.player.getTileY() - 1, false);
            } catch (ArrayIndexOutOfBoundsException e1) {
            }

            try {
                down = TileUtils.getTileAt(GamePanel.game.player.getTileX(), GamePanel.game.player.getTileY() + 1, false);
            } catch (ArrayIndexOutOfBoundsException e1) {
            }

            try {
                right = TileUtils.getTileAt(GamePanel.game.player.getTileX() + 1, GamePanel.game.player.getTileY(), false);
            } catch (ArrayIndexOutOfBoundsException e1) {
            }

            try {
                left = TileUtils.getTileAt(GamePanel.game.player.getTileX() - 1, GamePanel.game.player.getTileY(), false);
            } catch (ArrayIndexOutOfBoundsException e1) {
            }

            if (GamePanel.game.player.getFacing() == Player.FACEUP) up.interact();
            if (GamePanel.game.player.getFacing() == Player.FACEDOWN) down.interact();
            if (GamePanel.game.player.getFacing() == Player.FACERIGHT) right.interact();
            if (GamePanel.game.player.getFacing() == Player.FACELEFT) left.interact();
        }

        if (e.getKeyCode() == KeyEvent.VK_L) {
            GamePanel.game.showstats = !GamePanel.game.showstats;
        }
    }

}