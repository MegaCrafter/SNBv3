package com.megacrafter.snb.game.tiles;

import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.panels.GamePanel;

import java.awt.*;

public class Tile {

    public int x;
    public int y;
    public Image texture;

    private boolean solid = false;
    private Rectangle solidbox = new Rectangle(0, 0, 32, 32);
    private Runnable interaction;
    private boolean isPart = false;

    ////////////////////////////////////////////
    public static TileType[][] parts =
            {

                    {TileType.TREE_GRASS_PART1, TileType.TREE_GRASS_PART2, TileType.TREE_GRASS_PART3, TileType.TREE_GRASS_PART4, TileType.TREE_GRASS_PART5, TileType.TREE_GRASS_PART6, TileType.TREE_GRASS_PART7, TileType.TREE_GRASS_PART8, TileType.TREE_GRASS_PART9}

            };
    public static String[][] codes =
            {

                    {"123",
                     "456", // TREE PART CODE
                     "789"}

            };

    public static TileArea[] areas = {new TileArea(codes[0], parts[0])};
    ////////////////////////////////////////////

    public Tile(int x, int y, Image texture, boolean solid, Runnable interaction) {
        this(x, y, texture, solid, false, null, interaction);
    }

    public Tile(int x, int y, Image texture, Rectangle solidbox) {
        this(x, y, texture, true, false, solidbox, null);
    }
    public Tile(int x, int y, Image texture, boolean solid, Rectangle solidbox) {
        this(x, y, texture, solid, false, solidbox, null);
    }

    public Tile(int x, int y, Image texture, boolean solid, boolean isPart, Rectangle solidbox, Runnable interaction) {
        this.x = x * 32;
        this.y = y * 32;
        if (solidbox == null) solidbox = new Rectangle(0, 0, 0, 0);

        try {
            for (int i = 0; i < areas.length; i++) {
                TileArea area = areas[i];

                for (int i2 = 2; i2 <= area.parts.length; i2++) {
                    int posx = area.getPosition(i2).x;
                    int posy = area.getPosition(i2).y;

                    int tilex = x - posx;
                    int tiley = y - posy;

                    try {
                        Tile ignoredtile = GamePanel.game.tiles[tilex][tiley];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }

                    if (GamePanel.game.tiles[tilex][tiley] != null && area.parts[0].texture == GamePanel.game.tiles[tilex][tiley].texture) {
                        TileType type = area.parts[i2 - 1];
                        this.isPart = type.isPart;
                        this.texture = type.texture;
                        this.solid = type.solid;
                        this.solidbox = type.solidbox;
                        this.interaction = type.interaction;
                        return;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.isPart = isPart;
        this.texture = texture;
        this.interaction = interaction;
        this.solid = solid;
        this.solidbox = solidbox;
    }

    public Tile(int x, int y, TileType type) {
        this.x = x * 32;
        this.y = y * 32;

        try {
            for (int i = 0; i < areas.length; i++) {
                TileArea area = areas[i];

                for (int i2 = 2; i2 < area.parts.length + 1; i2++) {
                    int posx = area.getPosition(i2).x;
                    int posy = area.getPosition(i2).y;

                    int tilex = x - posx;
                    int tiley = y - posy;

                    try {
                        Tile ignoredtile = GamePanel.game.tiles[tilex][tiley];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }

                    if (GamePanel.game.tiles[tilex][tiley] != null && area.parts[0].texture == GamePanel.game.tiles[tilex][tiley].texture) {
                        type = area.parts[i2 - 1];
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.isPart = type.isPart;
        this.texture = type.texture;
        this.solid = type.solid;
        this.solidbox = type.solidbox;
        this.interaction = type.interaction;
    }

    public Tile() {
        this.x = 0;
        this.y = 0;
        this.texture = null;
        this.solid = false;
        this.interaction = () -> {};
    }

    public void render(Graphics g) {
        Graphics2D g2d = GUtils.setRenderingHints(g);
        g2d.translate(-GamePanel.game.cam.x, -GamePanel.game.cam.y);
        g2d.drawImage(texture, x, y, null);
        g2d.translate(GamePanel.game.cam.x, GamePanel.game.cam.y);
    }

    public int getScreenX() {
        return this.x - GamePanel.game.cam.x;
    }

    public int getScreenY() {
        return this.y - GamePanel.game.cam.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return this.solid;
    }

    public Rectangle getSolidbox() {
        Rectangle box = new Rectangle((int) solidbox.getX() + this.getScreenX(), (int) solidbox.getY() + this.getScreenY(), (int) solidbox.getWidth(), (int) solidbox.getHeight());
        return box;
    }

    public Runnable getInteraction() {
        return this.interaction;
    }
    public void setInteraction(Runnable r) {
        this.interaction = r;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image tex) { this.texture = tex; }

    public void interact() {
        getInteraction().run();
    }

    public boolean isInLOS() {
        if (getScreenX() > -32 && getScreenX() < Display.WIDTH && getScreenY() > -32 && getScreenY() < Display.HEIGHT) {
            return true;
        } else return false;
    }

    public boolean isPart() {
        return isPart;
    }
}