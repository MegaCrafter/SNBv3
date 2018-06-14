package com.megacrafter.snb.game.util;

public class GTimer {

    public static boolean active = false;
    public static Runnable r = () -> {};
    public static Runnable r2 = () -> {};
    public static int delay = 0;

    public static void set(int delay, Runnable r) {
        GTimer.delay = delay;
        GTimer.r = r;
        GTimer.active = true;
    }

    private static int temp_cdown = 0;

    public static void doDelayed(int delay, int timer, Runnable r) {
        GTimer.setFinally(r);
        GTimer.set(delay, () -> {
            temp_cdown++;
            if (temp_cdown == timer) {
                temp_cdown = 0;
                GTimer.end();
            }
        });
    }

    public static void doDelayed(int delay, int timer) {
        GTimer.set(delay, () -> {
            temp_cdown++;
            if (temp_cdown == timer) {
                temp_cdown = 0;
                GTimer.end();
            }
        });
    }

    public static void end() {
        GTimer.active = false;
        r2.run();
    }

    public static void setFinally(Runnable r2) {
        GTimer.r2 = r2;
    }
}