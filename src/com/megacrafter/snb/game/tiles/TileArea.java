package com.megacrafter.snb.game.tiles;

import java.awt.*;

public class TileArea {

    public String[] code;
    public TileType[] parts;

    public TileArea(String[] code, TileType[] parts) {
        this.code = code;
        this.parts = parts;
    }

    public Point getPosition(int num) {
        String n = "" + num;
        char c = n.charAt(0);
        for (int x = 0; x < code.length; x++) {
            for (int y = 0; y < code[x].length(); y++) {
                if (code[x].charAt(y) == c) {
                    return new Point(y, x);
                }
            }
        }
        return null;
    }

}