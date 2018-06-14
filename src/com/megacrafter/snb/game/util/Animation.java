package com.megacrafter.snb.game.util;

import com.megacrafter.snb.panels.GamePanel;

import java.awt.*;
import java.util.Arrays;

public class Animation {

    private int currentIndex = 0;
    private int delay;
    private int time = 0;
    private Image[] animation;
    private Image currentImage;

    public Animation(int delay, Image... images) {
        if (images != null && images.length >= 1) {
            this.animation = images;
        } else {
            throw new IllegalArgumentException("Animation sınıfına boş animasyon verildi.");
        }
        this.delay = delay;
    }

    public void start() {
        if (!Arrays.asList(animation).contains(GamePanel.game.player.current_image)) {
            currentImage = animation[0];
            time = 0;
        }
    }

    public void tick() {
        time++;
        if (time >= delay) {
            time = 0;
            currentIndex = (currentIndex == animation.length - 1) ? 0 : (currentIndex + 1);
            currentImage = animation[currentIndex];
        }
    }

    public Image getCurrentImage() {
        return currentImage;
    }

}