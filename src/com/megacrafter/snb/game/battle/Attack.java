package com.megacrafter.snb.game.battle;

import com.megacrafter.snb.game.battle.powerup.AttackPower;
import com.megacrafter.snb.panels.battle.BattlePanel;

import java.util.ArrayList;

public class Attack {

    private int damage;
    private float multiplier = 1;
    private int flag = 0;
    private ArrayList<Element> types = new ArrayList<>();
    private String name;
    private ArrayList<AttackPower> powers = new ArrayList<>();

    public Attack(int damage, String name, Element... type) {
        this.damage = damage;
        this.name = name;
        for (Element type1 : type) {
            types.add(type1);
            this.flag += type1.getFlag();
        }
    }

    public Attack(String name, Element... type) {
        this.damage = 10;
        this.name = name;
        for (Element type1 : type) {
            types.add(type1);
            this.flag += type1.getFlag();
        }
    }

    public int getDamage() {
        return Math.round(damage*multiplier);
    }

    public float getMultiplier() {
        return this.multiplier;
    }

    public int getFlag() { return this.flag; }

    public ArrayList<Element> getTypes() { return this.types; }

    public void multiply(float f) {
        multiplier *= f;
    }

    public void setMultiplier(float f) {
        multiplier = f;
    }

    public void activate() {
        if (BattlePanel.battle.attack == null) BattlePanel.battle.attack = this;
        else BattlePanel.battle.counterattack = this;
        BattlePanel.battle.giveTurn(BattlePanel.battle.turn.getRival());
    }

    public void addPower(AttackPower p) {
        p.setAttack(this);
        powers.add(p);
        if (p.getNeed().check()) p.getPower().run();
    }

    public ArrayList<AttackPower> getPowers() {
        return powers;
    }

    public String getName() {
        return name;
    }
}