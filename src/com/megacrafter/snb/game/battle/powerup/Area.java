package com.megacrafter.snb.game.battle.powerup;

import com.megacrafter.snb.game.util.GUtils;

public class Area {

    private String name = "Normal";
    private int buff_flag = 0;
    private int nerf_flag = 0;

    public Area(String name, int buff_flag, int nerf_flag) {
        this.name = name;
        this.buff_flag = buff_flag;
        this.nerf_flag = nerf_flag;
    }

    public int getBFlag() { return this.buff_flag; }
    public int getNFlag() { return this.nerf_flag; }

    public boolean isBuffing(int flag) {
        return GUtils.compareFlags(this.buff_flag, flag);
    }

    public boolean isNerfing(int flag) {
        return GUtils.compareFlags(this.nerf_flag, flag);
    }

}