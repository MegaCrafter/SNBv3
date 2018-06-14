package com.megacrafter.snb.game.util;

import com.megacrafter.programapi.Menu;
import com.megacrafter.snb.Display;

public class PanelSlide {

    private static boolean sliding = false;
    private static boolean slidingRight = false;
    private static boolean slidingLeft = false;

    private static int def_speed = 20;
    private static int def_delay = 1;
    private static int ticked = 0;

    private static Menu frompanel;
    private static Menu topanel;
    private static int delay;
    private static int speed;

    public static void tick() {
        sliding = (slidingRight || slidingLeft);

        if (slidingRight) {
            ticked++;
            if (ticked >= delay) {
                if (!topanel.isVisible()) {
                    topanel.setLocation(-topanel.getWidth(), topanel.getY());
                    topanel.setVisible(true);
                }

                if (topanel.getX() == 0) {
                    frompanel.setVisible(false);
                    slidingRight = false;
                }

                int used_speed = speed;
                if (-topanel.getX() < speed) used_speed = (Display.WIDTH - frompanel.getX());

                frompanel.setLocation(frompanel.getX() + used_speed, frompanel.getY());
                topanel.setLocation(topanel.getX() + used_speed, topanel.getY());

                frompanel.repaint();
                topanel.repaint();

                ticked = 0;
            }
        }

        if (slidingLeft) {
            ticked++;
            if (ticked >= delay) {
                if (!topanel.isVisible()) {
                    topanel.setLocation(topanel.getWidth(), topanel.getY());
                    topanel.setVisible(true);
                }

                if (topanel.getX() == 0) {
                    frompanel.setVisible(false);
                    slidingLeft = false;
                }

                int used_speed = speed;
                if (topanel.getX() < speed) used_speed = topanel.getX();

                frompanel.setLocation(frompanel.getX() - used_speed, frompanel.getY());
                topanel.setLocation(topanel.getX() - used_speed, topanel.getY());

                frompanel.repaint();
                topanel.repaint();

                ticked = 0;
            }
        }
    }

    public static void slideToRight(Menu frompanel, Menu topanel, int delay, int speed) {
        if (!sliding) {
            if (delay == -1) delay = def_delay;
            if (speed == -1) speed = def_speed;

            PanelSlide.frompanel = frompanel;
            PanelSlide.topanel = topanel;
            PanelSlide.delay = delay;
            PanelSlide.speed = speed;

            slidingRight = true;
        }
    }

    public static void slideToLeft(Menu frompanel, Menu topanel, int delay, int speed) {
        if (!sliding) {
            if (delay == -1) delay = def_delay;
            if (speed == -1) speed = def_speed;

            PanelSlide.frompanel = frompanel;
            PanelSlide.topanel = topanel;
            PanelSlide.delay = delay;
            PanelSlide.speed = speed;

            slidingLeft = true;
        }
    }

}