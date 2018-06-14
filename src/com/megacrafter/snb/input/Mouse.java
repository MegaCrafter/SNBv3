package com.megacrafter.snb.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    public static int x = 0;
    public static int y = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public static boolean isInArea(int x, int y, int width, int height) {
        return (Mouse.x > x && Mouse.y > y && Mouse.x < (x + width) && Mouse.y < (y + height));
    }
}