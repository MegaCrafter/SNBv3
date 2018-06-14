package com.megacrafter.snb.game.util;

import com.megacrafter.snb.game.tiles.Tile;
import com.megacrafter.snb.panels.GamePanel;

import java.awt.*;

public class TileUtils {

    public static Tile getTileAt(int x, int y, boolean forTileLoc) {
        try {
            if (forTileLoc) {
                Point tilepoint = getTileNumber(x, y);

                return GamePanel.game.tiles[tilepoint.x][tilepoint.y];
            } else return GamePanel.game.tiles[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static Tile getTileAt(int x, int y) { return getTileAt(x, y, true); }

    public static Point getTileNumber(int x, int y, boolean forScreen) {
        int tileX;
        int tileY;

        if (forScreen) {
            tileX = GamePanel.game.tiles[0][0].getScreenX();
            tileY = GamePanel.game.tiles[0][0].getScreenY();
        } else {
            tileX = GamePanel.game.tiles[0][0].x;
            tileY = GamePanel.game.tiles[0][0].y;
        }

        int xt = (int) Math.floor((x - tileX) / 32);
        int yt = (int) Math.floor((y - tileY) / 32);

        return new Point(xt, yt);
    }

    public static Point getTileNumber(int x, int y) { return getTileNumber(x, y, true); }

    public static boolean isPlacable(int x, int y, String[] forCode) {
        if (x == GamePanel.game.tiles.length - 1 || y == GamePanel.game.tiles[x].length - 1) return false;
        for (int i = 1; i < forCode.length; i++) {
            try {
                if (x - 1 < 0 || y + i >= 50) continue;
                if (GamePanel.game.tiles[x - 1][y + i].isPart()) return false;
            } catch (ArrayIndexOutOfBoundsException ignored) { ignored.printStackTrace(); }
        }
        if (GamePanel.game.startx > x * 32 && GamePanel.game.startx < (x + forCode[0].length()) * 32) return false;
        if (GamePanel.game.starty > y * 32 && GamePanel.game.starty < (y + forCode.length) * 32) return false;

        return true;
    }
}