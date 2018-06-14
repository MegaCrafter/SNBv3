package com.megacrafter.snb.game.mobs;

import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.battle.HumanBPlayer;
import com.megacrafter.snb.game.tiles.Tile;
import com.megacrafter.snb.game.util.Animation;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.game.util.TileUtils;
import com.megacrafter.snb.game.util.images.Assets;
import com.megacrafter.snb.input.Keyboard;
import com.megacrafter.snb.panels.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

// TODO: Entity ile Oyuncuyu ayır.

public class Player extends Entity {

    private String name;

    public Image current_image = Assets.spr_playerdown1;

    private Animation animdown = new Animation(10, Assets.spr_playerdown1, Assets.spr_playerdown2);
    private Animation animup = new Animation(10, Assets.spr_playerup1, Assets.spr_playerup2);
    private Animation animright = new Animation(10, Assets.spr_playerright1, Assets.spr_playerright2);
    private Animation animleft = new Animation(10, Assets.spr_playerleft1, Assets.spr_playerleft2);

    private static final int maxvel = 2;

    public static int FACELEFT = 0;
    public static int FACEUP = 1;
    public static int FACERIGHT = 2;
    public static int FACEDOWN = 3;

    private int facing = FACEDOWN;

    public HumanBPlayer battleplayer = new HumanBPlayer();

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 15));

        if (name == null) name = "Oyuncu";

        int textx = GUtils.getCenteredTextX(x, 32, g, name);
        int texty = GUtils.getCenteredTextY(y - 17, 17, g);

        if (textx < 2) textx = 2;
        if ((textx + g.getFontMetrics().stringWidth(name)) > (Display.WIDTH - 8))
            textx = Display.WIDTH - g.getFontMetrics().stringWidth(name) - 8;
        if (texty < 10) texty = GUtils.getCenteredTextY(y + 35, 17, g);

        g.setColor(Color.BLACK);

        Graphics2D g2d = GUtils.setRenderingHints(g);
        g2d.drawString(name, textx, texty);

        g.drawImage(current_image, x, y, null);
    }

    @Override
    public void tick() {
        movements();
    }

    private void movements() {
        byte rspeed = maxvel;
        byte lspeed = maxvel;
        byte uspeed = maxvel;
        byte dspeed = maxvel;

        // Moving
        if (Keyboard.keys[KeyEvent.VK_S] && this.y < 600 - 60 && !Keyboard.keys[KeyEvent.VK_W]) {
            facing = FACEDOWN;

            for (int i = 4; i < 28; i++) {
                Tile tile = TileUtils.getTileAt(x + i, y + 33);
                if ((tile == null || (tile.isSolid() && tile.getSolidbox().intersects(x + i, y + 33, 1, 1)))) {
                    dspeed = 0;
                    break;
                }
            }

            y += dspeed;

            animdown.start();
        }

        if (Keyboard.keys[KeyEvent.VK_W] && this.y > 2 && !Keyboard.keys[KeyEvent.VK_S]) {
            facing = FACEUP;

            for (int i = 4; i < 28; i++) {
                Tile tile = TileUtils.getTileAt(x + i, y + 1);
                if ((tile == null || (tile.isSolid() && tile.getSolidbox().intersects(x + i, y + 1, 1, 1)))) {
                    uspeed = 0;
                    break;
                }
            }

            y -= uspeed;

            animup.start();
        }

        if (Keyboard.keys[KeyEvent.VK_D] && this.x < 800 - 34 && !Keyboard.keys[KeyEvent.VK_A]) {
            facing = FACERIGHT;

            for (int i = 4; i < 28; i++) {
                Tile tile = TileUtils.getTileAt(x + 30, y + i);
                if ((tile == null || (tile.isSolid() && tile.getSolidbox().intersects(x + 30, y + i, 1, 1)))) {
                    rspeed = 0;
                    break;
                }
            }

            x += rspeed;

            animright.start();
        }

        if (Keyboard.keys[KeyEvent.VK_A] && this.x > -2 && !Keyboard.keys[KeyEvent.VK_D]) {
            facing = FACELEFT;

            for (int i = 4; i < 28; i++) {
                Tile tile = TileUtils.getTileAt(x + 2, y + i);
                if ((tile == null || (tile.isSolid() && tile.getSolidbox().intersects(x + 2, y + i, 1, 1)))) {
                    lspeed = 0;
                    break;
                }
            }

            x -= lspeed;

            animleft.start();
        }

        //////////////////////////////////////////////////
        // ANIMATION
        if (Keyboard.keys[KeyEvent.VK_S] && !Keyboard.keys[KeyEvent.VK_W]) {
            animdown.tick();
            current_image = animdown.getCurrentImage();
        }
        if (Keyboard.keys[KeyEvent.VK_W] && !Keyboard.keys[KeyEvent.VK_S]) {
            animup.tick();
            current_image = animup.getCurrentImage();
        }
        if (Keyboard.keys[KeyEvent.VK_D] && !Keyboard.keys[KeyEvent.VK_A]) {
            animright.tick();
            current_image = animright.getCurrentImage();
        }
        if (Keyboard.keys[KeyEvent.VK_A] && !Keyboard.keys[KeyEvent.VK_D]) {
            animleft.tick();
            current_image = animleft.getCurrentImage();
        }
    }

    public int getTileX() {
        return (int) Math.floor((this.x - GamePanel.game.tiles[0][0].getScreenX() + 16) / 32);
    }

    public int getTileY() {
        return (int) Math.floor((this.y - GamePanel.game.tiles[0][0].getScreenY() + 16) / 32);
    }

    public int getFacing() {
        return this.facing;
    }

    public void setName(String name) {
        this.name = name;
    }

}