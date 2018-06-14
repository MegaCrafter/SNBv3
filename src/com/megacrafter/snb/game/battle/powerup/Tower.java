package com.megacrafter.snb.game.battle.powerup;

import com.megacrafter.snb.game.battle.BPlayer;

public class Tower {

    private TowerType type;
    private int level = 1;
    private BPlayer owner;

    public Tower(TowerType type) {
        this.type = type;
    }

    public boolean build(BPlayer owner) {
        this.owner = owner;
        if (owner.addTower(this)) return true;

        this.owner = null;
        return false;
    }

    public void upgrade() {
        level++;
    }

    public void destroy() {
        owner.removeTower(this);
        this.owner = null;
    }

    public boolean swap(Tower t2) {
        if (t2.getOwner() != this.getOwner().getRival()) return false;
        BPlayer owner1 = this.getOwner();
        BPlayer owner2 = t2.getOwner();

        this.owner = null;
        t2.owner = null;

        if (owner1.hasTower(t2)) return false;
        if (owner2.hasTower(this)) return false;

        this.owner = owner1;
        t2.owner = owner2;

        this.destroy();
        t2.destroy();

        if (this.getType() != null && this.getLevel() != 0) {
            this.build(owner2);
        }

        if (t2.getType() != null && t2.getLevel() != 0) {
            t2.build(owner1);
        }


        return true;
    }

    public BPlayer getOwner() { return this.owner; }
    public TowerType getType() { return this.type; }
    public int getLevel() { return this.level; }

}