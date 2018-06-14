package com.megacrafter.snb.game.mobs;

import java.awt.*;

public abstract class Entity {

    public int x;
    public int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);

    public abstract void tick();

}