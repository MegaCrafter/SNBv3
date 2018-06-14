package com.megacrafter.snb.game;

import com.megacrafter.programapi.Menu;
import com.megacrafter.programapi.Program;
import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.mobs.Player;
import com.megacrafter.snb.game.tiles.Tile;
import com.megacrafter.snb.game.tiles.TileType;
import com.megacrafter.snb.game.tiles.specials.BattleTile;
import com.megacrafter.snb.game.util.Camera;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.game.util.TileUtils;

import java.awt.*;

import static com.megacrafter.snb.panels.PausePanel.pausemenu;

public class Game extends Menu {

    public int startx = Display.WIDTH / 2 - 16, starty = Display.HEIGHT / 2 - 16;
    public String playername = "Oyuncu";

    public Tile[][] tiles = new Tile[50][50];

    public void init() {
        cam = new Camera();

        // TODO: TEST İÇİN KONULDU
        tiles[7][7] = new BattleTile(7, 7, 0);

        Point startPoint = TileUtils.getTileNumber(startx, starty);
        int xt = startPoint.x;
        int yt = startPoint.y;
        tiles[xt][yt] = new Tile(xt, yt, TileType.GRASS);

        player = new Player(startx, starty);
        player.setName(playername);
    }

    public void tick() {
        player.tick();
        cam.tick();
    }

    public void draw(Graphics g) {
        for (int x = -30; x < Display.WIDTH + 5; x += 32) {
            for (int y = -30; y < Display.HEIGHT + 5; y += 32) {
                Tile tile = TileUtils.getTileAt(x, y);
                if (tile == null) continue;
                if (tile.isInLOS()) tile.render(g);
            }
        }

        player.render(g);

        String fpss = Display.gameloop.fps + "";
        if ((Display.gameloop.fps + "").toCharArray().length == 1) fpss = "0" + fpss;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.fillRect(708, 0, 92, 24);

        if (Display.gameloop.fps_changed) {
            g.setColor(Color.RED);
            Display.gameloop.fps_changed = false;
        } else g.setColor(Color.YELLOW);

        Graphics2D g2d = GUtils.setRenderingHints(g);

        String text = "FPS: " + fpss;
        int textx = GUtils.getCenteredTextX(708, 92, g2d, text) - 2;
        int texty = GUtils.getCenteredTextY(2, 24, g2d);

        g2d.drawString(text, textx, texty);
    }

    private int save_slot = 0;
    private int difficulty = 0;

    public boolean paused = true;
    public boolean showstats = false;
    public Player player;
    public Camera cam = new Camera();

    public void start(int save_slot) {
        this.save_slot = save_slot;
        startT();
    }

    private void startT() {
        if (!paused) return;
        paused = false;
        this.forward();
        Program.getWindow().requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void pause() {
        if (paused) return;
        paused = true;
        pausemenu.forward();
    }

    public void resume() {
        startT();
    }

    public void generateTile(int x, int y) {
        Point startTile;
        int stx = -100;
        int sty = -100;

        if (tiles[0][0] != null) {
            startTile = TileUtils.getTileNumber(startx, starty);
            stx = startTile.x;
            sty = startTile.y;
        }

        TileType type = TileType.GRASS;

        if (x - 1 != stx && x != stx && x + 1 != stx) {
            if (y - 1 != sty && y != sty && y + 1 != sty) {
                if (GUtils.chanceOf(1, 400)) {
                    tiles[x][y] = new BattleTile(x, y, 0);
                    return;
                } else if (GUtils.chanceOf(1, 50)) {
                    type = TileType.FLOWER_GRASS_1;
                } else if (GUtils.chanceOf(1, 50)) {
                    type = TileType.FLOWER_GRASS_2;
                } else if (GUtils.chanceOf(1, 40)) {
                    type = TileType.FILLED_GRASS;
                } else if (GUtils.chanceOf(1, 400)) {
                    type = TileType.SHOP;
                } else if (GUtils.chanceOf(1, 20)) {
                    type = (TileUtils.isPlacable(x, y, Tile.codes[0])) ? TileType.TREE_GRASS_PART1 : TileType.GRASS;
                }
            }
        }

        tiles[x][y] = new Tile(x, y, type);
    }

}