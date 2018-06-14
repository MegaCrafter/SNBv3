package com.megacrafter.snb.game.battle.powerup;

import com.megacrafter.snb.game.battle.Attack;

public class AttackPower extends Power {

    public Attack attack;

    public AttackPower(String name, Checker need, Runnable power) {
        super(name, Power.ATTACK_POWER, need, power);
    }
    public AttackPower(String name) {
        super(name);
    }

    public void setAttack(Attack atk) { this.attack = atk; }

}