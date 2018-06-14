package com.megacrafter.snb.game.battle;

import com.megacrafter.snb.game.battle.powerup.AttackPower;
import com.megacrafter.snb.game.battle.powerup.BattleCore;
import com.megacrafter.snb.game.battle.powerup.Power;
import com.megacrafter.snb.game.battle.powerup.Tower;
import com.megacrafter.snb.panels.GamePanel;
import com.megacrafter.snb.panels.battle.BattlePanel;

import java.util.ArrayList;

public abstract class BPlayer {

    public Attack attack = null;
    public Defense defense = null;

    private int mana;
    private int gem;
    private int hp;

    private Battle battle;

    private int wins;
    private BattleCore battlecore;

    private Power latest_power;

    private ArrayList<Tower> towers = new ArrayList<>();
    private ArrayList<Defense> defenses;
    private ArrayList<Power> powers;
    private ArrayList<Attack> attacks;

    public BPlayer() {
        this.defenses = new ArrayList<>();
        this.powers = new ArrayList<>();
        this.attacks = new ArrayList<>();
    }

    public BPlayer(ArrayList<Defense> defenses, ArrayList<Power> powers, ArrayList<Attack> attacks) {
        this.defenses = defenses;
        this.powers = powers;
        this.attacks = attacks;
    }

    public void init() {
        hp = 100;
    }

    public void setWins(int wins) { this.wins = wins; }
    public int getWins() { return this.wins; }

    public boolean attack(Attack atk) {
        if (!attacks.contains(atk)) return false;
        this.attack = atk;
        return true;
    }
    public void attackActive() {
        this.attack.activate();
        BattlePanel.battle.giveTurn(BattlePanel.battle.turn.getRival());
    }

    public boolean setDefense(Defense def) {
        if (!defenses.contains(def)) return false;
        this.defense = def;
        return true;
    }

    public boolean usePower(Power pow) {
        if (pow instanceof AttackPower) {
            if (this.attack == null) return false;
            this.attack.addPower((AttackPower) pow);
            return true;
        } else {
            pow.run();
            return true;
        }
    }

    public void setHP(int hp) {
        this.hp = hp;
        if (this.hp <= 0) {
            this.hp = 0;
            this.battlecore = null;
            if (getRival().getHP() > 0) {
                battle.endRound(getRival());
            }
        }
    }

    public int getHP() { return this.hp; }
    public void damage(int damage) {
        setHP(getHP() - damage);
    }

    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) { this.mana = mana; }

    public int getGem() {
        return this.gem;
    }

    public void setGem(int gem) {
        this.gem = gem;
        if (getGem() > getBattle().maxgem) this.gem = getBattle().maxgem;
    }

    public Battle getBattle() { return this.battle; }

    public BPlayer getRival() {
        if (this == getBattle().p1) return getBattle().p2;
        if (this == getBattle().p2) return getBattle().p1;
        else return null;
    }

    public void setBattle(Battle b) { this.battle = b; }

    public boolean isProtagonist() { return (this == GamePanel.game.player.battleplayer); }

    public ArrayList<Tower> getTowers() { return this.towers; }

    public boolean addTower(Tower t) {
        if (this.towers.contains(t)) return false;

        this.towers.add(t);
        return true;
    }

    public boolean removeTower(Tower t) {
        if (!this.towers.contains(t)) return false;

        this.towers.remove(t);
        return true;
    }

    public boolean hasTower(Tower t) {
        return this.towers.contains(t);
    }

    public boolean hasTurn() {
        return (battle.turnp == this);
    }

    public void win() {
        setWins(getWins() + 1);
    }

    public ArrayList<Defense> getDefenses() {
        return defenses;
    }
    public ArrayList<Power> getPowers() {
        return powers;
    }
    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public boolean addDefense(Defense def) {
        if (defenses.contains(def)) return false;
        defenses.add(def);
        return true;
    }
    public boolean addPower(Power pow) {
        if (powers.contains(pow)) return false;
        powers.add(pow);
        return true;
    }
    public boolean addAttack(Attack atk) {
        if (attacks.contains(atk)) return false;
        attacks.add(atk);
        return true;
    }

    public boolean removeDefense(Defense def) {
        if (!defenses.contains(def)) return false;
        defenses.remove(def);
        return true;
    }
    public boolean removePower(Power pow) {
        if (!powers.contains(pow)) return false;
        powers.remove(pow);
        return true;
    }
    public boolean removeAttack(Attack atk) {
        if (!attacks.contains(atk)) return false;
        attacks.remove(atk);
        return true;
    }

    public BattleCore getBattlecore() {return battlecore; }

    public void give(Attack atk) {
        this.attacks.add(atk);
    }

    public void give(Power pow) {
        this.powers.add(pow);
    }

    public void give(Defense def) {
        this.defenses.add(def);
    }
}