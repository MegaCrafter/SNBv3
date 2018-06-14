package com.megacrafter.snb.game.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class GUtils {

    public static final Color AQUA = new Color(0, 210, 225);

    public static void drawOutline(Graphics g, int x, int y, Shape sh, float thickness, Color color) {
        Graphics2D g2d = setRenderingHints(g);

        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(color);
        g2d.translate(x, y);
        g2d.draw(sh);
        g2d.translate(-x, -y);
    }

    public static void drawStringOutline(Graphics g, int x, int y, String st, float thickness, Color color) {
        Graphics2D g2d = setRenderingHints(g);

        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(color);
        g2d.translate(x, y);
        g2d.draw(g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), st).getOutline());
        g2d.translate(-x, -y);
    }

    public static void drawOutline(Graphics g, int x, int y, Shape sh, float thickness) {
        drawOutline(g, x, y, sh, thickness, Color.BLACK);
    }

    public static void drawStringOutline(Graphics g, int x, int y, String st, float thickness) {
        drawStringOutline(g, x, y, st, thickness, Color.BLACK);
    }

    public static Graphics2D setRenderingHints(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        return g2d;
    }

    public static void drawAlphaGradient(Graphics g, int x, int y, int width, int height, int edge, Color color) {
        drawGlowGradient(g, x, y, width, height, edge, color, color);
    }

    public static void drawGlowGradient(Graphics g, int x, int y, int width, int height, int edge, Color color, Color glow) {
        Graphics2D g2d = setRenderingHints(g);

        float x1 = (float) x;

        float edge0 = x1 + width;
        float edge1 = x1 + width + edge;

        float red1 = 1.0f / 255 * color.getRed();
        float green1 = 1.0f / 255 * color.getGreen();
        float blue1 = 1.0f / 255 * color.getBlue();

        float red2 = 1.0f / 255 * glow.getRed();
        float green2 = 1.0f / 255 * glow.getGreen();
        float blue2 = 1.0f / 255 * glow.getBlue();

        for (float i = x1; i < edge1; i++) {
            if (i >= x1 && i < edge0) g2d.setColor(new Color(red1, green1, blue1, 1.0f));
            if (i >= edge0 && i < edge1) {
                float clamped = Math.min(Math.max((i - edge0) / (edge1 - edge0), 0.0f), 1.0f);
                float distance = clamped * clamped * (3 - 2 * clamped);
                g2d.setColor(new Color(red2, green2, blue2, 1.0f - distance));
            }
            g2d.fillRect((int) i, y, 1, height);
        }
    }

    public static int getCenteredTextX(int x, int width, Graphics g, String title) {
        return ((x + (width / 2)) - (g.getFontMetrics().stringWidth(title) / 2));
    }

    public static int getCenteredTextY(int y, int height, Graphics g) {
        return ((y + (height / 2)) - (g.getFontMetrics().getHeight() / 2) + (g.getFontMetrics().getAscent()) - (g.getFontMetrics().getDescent()));
    }

    public static int getCenteredTextY(int y, int height, Graphics g, String title) {
        return ((y + (height / 2)) - ((int) g.getFontMetrics().getLineMetrics(title, g).getHeight() / 2) + (g.getFontMetrics().getAscent()) - (g.getFontMetrics().getDescent()));
    }

    public static int getCenteredRectX(int x, int width, int rectwidth) {
        return ((x + (width / 2)) - (rectwidth / 2));
    }

    public static int getCenteredRectY(int y, int height, int rectheight) {
        return ((y + (height / 2)) - (rectheight / 2));
    }

    public static boolean chanceOf(int num, int total) {
        Random rng = new Random();
        return rng.nextInt(total+1) == num+1;
    }

    public static boolean compareFlags(int flag1, int flag2) { // flag1 contains flag2
        String bin1 = Integer.toBinaryString(flag1);
        String bin2 = Integer.toBinaryString(flag2);

        while (bin2.length() < bin1.length()) bin2 = 0 + bin2;

        for (int i = bin1.length() - 1; i >= 0; i--) {
            char c1 = bin1.charAt(i);
            char c2;

            try {
                c2 = bin2.charAt(i);
            } catch (StringIndexOutOfBoundsException e) {
                c2 = '0';
            }

            if (c2 == '0') continue;
            if (c1 != c2) return false;
        }
        return true;
    }

    public static JButton attachKey(JButton btn, int... keys) {
        for (int i = 0; i < keys.length; i++) {
            btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keys[i], 0), "attachedbuttondontworry"+i);
            btn.getActionMap().put("attachedbuttondontworry" + i, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btn.doClick();
                }
            });
        }
        return btn;
    }
}