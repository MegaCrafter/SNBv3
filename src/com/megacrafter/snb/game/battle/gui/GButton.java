package com.megacrafter.snb.game.battle.gui;

import com.megacrafter.snb.game.battle.Attack;
import com.megacrafter.snb.game.battle.Defense;
import com.megacrafter.snb.game.battle.powerup.AttackPower;
import com.megacrafter.snb.game.battle.powerup.Power;
import com.megacrafter.snb.panels.GamePanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GButton extends JButton {

    private boolean hovered = false;
    private int type;
    private Attack atk;
    private Defense def;
    private Power pow;

    public GButton(Attack atk) {
        super(atk.getName());
        this.atk = atk;
        type = 1;
        setOptions();
    }

    public GButton(Defense def) {
        super(def.getName());
        this.def = def;
        type = 2;
        setOptions();
    }

    public GButton(Power pow) {
        super(pow.getName());
        this.pow = pow;
        type = 3;
        setOptions();
    }

    private void setOptions() {
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                hovered = true;
            }
            public void mouseExited(MouseEvent e) {
                hovered = false;
            }
        });
        this.addActionListener(e -> {
            if (type == 1) {
                GamePanel.game.player.battleplayer.attack(atk);
            }

            if (type == 2) {
                GamePanel.game.player.battleplayer.setDefense(def);
            }

            if (type == 3) {
                if (pow instanceof AttackPower) {
                    if (GamePanel.game.player.battleplayer.attack != null) {
                        GamePanel.game.player.battleplayer.attack.addPower((AttackPower) pow);
                    }
                } else pow.run();
            }
        });
    }

    public boolean isHovered() { return this.hovered; }


}