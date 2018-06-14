package com.megacrafter.snb.game.battle.gui;

import com.megacrafter.snb.game.util.GUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GPanel extends JPanel {

    public static final int BUTTON_HEIGHT = 30;

    private ArrayList<GButton> buttons = new ArrayList<>();

    public GPanel() {
        super(null);
    }

    public ArrayList<GButton> getButtons() {
        return buttons;
    }

    public void addButton(GButton btn) {
        int no = getButtons().size();
        btn.setBounds(1, (no * BUTTON_HEIGHT) + 1, this.getWidth() - 8, BUTTON_HEIGHT);
        this.buttons.add(btn);
        this.add(btn);
    }

    public void removeButtons() {
        buttons.clear();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = GUtils.setRenderingHints(g);
        for (GButton btn : buttons) {
            g2d.setColor(Color.GRAY);
            g2d.fillRect(btn.getX() + 1, btn.getY() + 1, btn.getWidth() - 2, btn.getHeight() - 2);
        }
    }
}