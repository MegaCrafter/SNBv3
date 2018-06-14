package com.megacrafter.snb.game.battle;

import com.megacrafter.snb.game.tiles.specials.BattleTile;

public class AIBPlayer extends BPlayer {

    private int difficulty = 0;
    private BattleTile owner;
    public AIBPlayer(int difficulty) {
        this.difficulty = difficulty;
    }
    public AIBPlayer(int difficulty, BattleTile owner) {
        this.difficulty = difficulty;
        this.owner = owner;
    }

    public void attack() {

    }

    public void setDefense() {

    }

    public BattleTile getOwner() {
        return this.owner;
    }
}