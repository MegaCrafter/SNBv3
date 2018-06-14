package com.megacrafter.snb.game.battle.powerup;

public class Power {

    public static final int ATTACK_POWER = 0;
    public static final int AREA_CHANGE = 1;
    public static final int TOWER_BUILD = 2;
    public static final int TOWER_UPGRADE = 3;
    public static final int FIELD_EFFECT = 4;
    public static final int BATTLE_CORE = 5;

    private String name;
    private Checker need = () -> true;
    private Runnable power;
    private int type;

    public Power(String name, int type, Checker need, Runnable power) {
        this.name = name;
        this.type = type;
        this.need = need;
        this.power = power;
    }
    public Power(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNeed(Checker need) {
        this.need = need;
    }
    public void setPower(Runnable pow) {
        this.power = pow;
    }
    public Checker getNeed() {
        return this.need;
    }
    public Runnable getPower() {
        return this.power;
    }
    public int getType() { return this.type; }

    public void run() {
        if (this.getNeed().check()) this.getPower().run();
    }
}