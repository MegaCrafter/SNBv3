package com.megacrafter.snb.game.util.images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;
    private int tile_len;

    public SpriteSheet(BufferedImage sheet, int tile_len) {
        this.sheet = sheet;
        this.tile_len = tile_len;
    }

    public Image getSprite(int x, int y, int width, int height, int scalex, int scaley) {
        return sheet.getSubimage(x * tile_len, y * tile_len, width * tile_len, height * tile_len).getScaledInstance(scalex, scaley, BufferedImage.TYPE_INT_RGB);
    }

}