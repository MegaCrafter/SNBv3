package com.megacrafter.snb.game;

import com.megacrafter.snb.game.util.GTimer;
import com.megacrafter.snb.game.util.PanelSlide;
import com.megacrafter.snb.panels.GamePanel;
import com.megacrafter.snb.panels.battle.BattlePanel;

public class GameLoop implements Runnable {

    public int fps = 0;
    public boolean fps_changed = false;

    private Thread thread;
    public boolean running = false;

    private void render() {
        if (!GamePanel.game.paused) GamePanel.game.repaint();
    }

    private int ticked = 0;
    private void tick() {
        if (!GamePanel.game.paused) GamePanel.game.tick();
        PanelSlide.tick();
        if (BattlePanel.battle != null) {
            BattlePanel.battle.tick();
        }

        if (GTimer.active) {
            ticked++;
            if (ticked >= GTimer.delay) {
                ticked = 0;
                GTimer.r.run();
            }
        }
    }

    @Override
    public void run() {
        long now;
        long lastTime = System.nanoTime();
        double nspt = 1000000000.0D / 60.0D;
        double delta = 0;

        long lastMil = System.currentTimeMillis();
        long lastMil2 = System.currentTimeMillis();
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / nspt;
            lastTime = now;

            if (delta >= 1) {
                tick();
                delta--;
                render();
                ticks++;
            }

            if (System.currentTimeMillis() - lastMil >= 1000) {
                lastMil = System.currentTimeMillis();
                fps = ticks;
                fps_changed = true;
                ticks = 0;
            }

            if (BattlePanel.battle != null && BattlePanel.battle.countdown <= 5) {
                if (System.currentTimeMillis() - lastMil2 >= 1000) {
                    lastMil2 = System.currentTimeMillis();
                    BattlePanel.battle.tickCountdown();
                }
            } else {
                lastMil2 = System.currentTimeMillis();
            }
        }
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this, "GameLoop");
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}