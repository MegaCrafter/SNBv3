package com.megacrafter.snb.game.battle;

import com.megacrafter.snb.Display;
import com.megacrafter.snb.game.battle.powerup.Area;
import com.megacrafter.snb.game.battle.powerup.AttackPower;
import com.megacrafter.snb.game.util.GTimer;
import com.megacrafter.snb.game.util.GUtils;
import com.megacrafter.snb.panels.battle.BattleConPanel;
import com.megacrafter.snb.panels.battle.BattlePanel;

public class Battle {

    private boolean active = false;
    private boolean paused = true;
    public Attack attack = null;
    public Attack counterattack = null;
    public BPlayer p1;
    public BPlayer p2;
    public BPlayer turn;
    public static BPlayer DRAW = new BPlayer() {};
    private Area area = new Area("Normal",0, 0);

    private BPlayer startp;
    BPlayer turnp;

    private BPlayer winner = null;

    int maxgem;
    // TODO: DEBUGGING
    public static int countdown_start = (Display.TESTING_BATTLE ? 1 : 5);
    public int countdown = countdown_start + 1;
    public int rounds;
    public int rem_rounds;

    public Battle(BPlayer p1, BPlayer p2) {
        this.p1 = p1;
        this.p2 = p2;

        p1.setBattle(this);
        p2.setBattle(this);
    }
    public void tick() {
        if (attack != null) {
            for (AttackPower p : attack.getPowers()) {
                if (p.getNeed().check()) p.getPower().run();
            }
        }
        if (counterattack != null) {
            for (AttackPower p : counterattack.getPowers()) {
                if (p.getNeed().check()) p.getPower().run();
            }
        }
    }

    public void start() {
        if (!active) {
            active = true;
            BattlePanel.battlemenu.forward();

            BPlayer _selected_player;
            if (GUtils.chanceOf(50, 100)) _selected_player = p1;
            else _selected_player = p2;

            startp = _selected_player;
            turnp = _selected_player;

            startRound();
            if (p1.getAttacks().size() == 0 && p1.getDefenses().size() == 0) {
                p1.give(new Attack("Isı Işını", Element.FIRE, Element.LIGHT));
                p1.give(new Attack("Dehşet-ül Vahşet", Element.FIRE));
                p1.give(new Attack("Elektromanyetik Patlama", Element.FIRE, Element.ELECTRIC));
                p1.give(new Defense("Zihin Duvarı", Element.MIND));
            }
            BattlePanel.initLists();
        }
    }
    public void startRound() {
        setWinner(null);

        p1.init();
        p2.init();

        this.turn = p1;

        startp = startp.getRival();
        turnp = startp;

        GTimer.doDelayed(60, 2, () -> {
            if (p1.getWins() == 0) p2.setHP(0);
            else if (p2.getWins() == 0) p1.setHP(0);
            else {
                p2.setHP(0);
            }
        });
    }

    public void endRound(BPlayer winner) {
        rem_rounds--;

        this.winner = winner;
        winner.win();

        if (winner.getWins() > (int) Math.floor(rounds / 2)) {
            end(winner);
        } else {
            if (rem_rounds <= 0) endDraw();
        }

        BattlePanel.battlemenu.repaint();
    }
    public void end(BPlayer winner) {
        if (active) {
            active = false;
            this.winner = winner;
            if (p2 instanceof AIBPlayer && winner != p2) {
                ((AIBPlayer) p2).getOwner().lost();
            }
        }
    }
    public void endDraw() {
        end(DRAW);
    }

    public void init(int startgem, int maxgem, int rounds) {
        this.maxgem = maxgem;
        this.rem_rounds = rounds;
        this.rounds = rounds;

        p1.setGem(startgem);
        p2.setGem(startgem);

        p1.setMana(100);
        p2.setMana(100);

        p1.setWins(0);
        p2.setWins(0);

    }

    public boolean isActive() { return this.active; }
    public BPlayer getWinner() {
        return this.winner;
    }
    public void setWinner(BPlayer winner) {
        this.winner = winner;
        BattlePanel.battlemenu.repaint();
    }
    public boolean isPaused() { return this.paused; }
    public void pause() { this.paused = true; }
    public void resume() { this.paused = false; }
    public Area getArea() { return this.area; }
    public void setArea(Area a) { this.area = a; }
    public void giveTurn(BPlayer player) {
        turnp = player;
    }

    public void tickCountdown() {
        if (countdown <= countdown_start) countdown--;
        BattleConPanel.battlecon_menu.repaint();
        if (countdown <= 0) {
            countdown = countdown_start + 1;
            start();
        }
    }
    public void startCountdown() {
        countdown = countdown_start;
        BattleConPanel.battlecon_menu.repaint();
    }
    public void stopCountdown() {
        countdown = countdown_start + 1;
        BattleConPanel.battlecon_menu.repaint();
    }



}