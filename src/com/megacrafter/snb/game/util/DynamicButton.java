package com.megacrafter.snb.game.util;

import com.megacrafter.programapi.Menu;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

// TODO: Butonların tanımı burası olacak (Optimizasyon)

public class DynamicButton {

    private int x, y, width, height;
    private int tx, ty, twidth, theight;
    private Menu panel;
    private Color background;
    private Color foreground;
    private int delay = 60;
    private int maxSize = 5;
    private String title;
    private Font font;

    private Timer incTimer = new Timer(false);
    private Timer decTimer = new Timer(false);

    private TimerTask increase = new TimerTask() {
        @Override
        public void run() {
            if (tx != x - maxSize && ty != y - maxSize && twidth != width + maxSize * 2 && theight != height + maxSize * 2) {
                tx -= 1;
                ty -= 1;
                twidth += 2;
                theight += 2;
                panel.repaint();
            } else {
                resetIncTimer();
            }
        }
    };

    private TimerTask decrease = new TimerTask() {
        @Override
        public void run() {
            if (tx != x && ty != y && twidth != width && theight != height) {
                tx += 1;
                ty += 1;
                twidth -= 2;
                theight -= 2;
                panel.repaint();
            } else {
                resetDecTimer();
            }
        }
    };

    public DynamicButton(Menu parent, int x, int y, int width, int height, Color background, Color foreground) {
        this(parent, x, y, width, height, background, foreground, "", new Font("Arial", Font.PLAIN, 1));
    }

    public DynamicButton(Menu parent, int x, int y, int width, int height, Color background, Color foreground, String title, Font font) {
        this.panel = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.background = background;
        this.foreground = foreground;

        this.title = title;
        this.font = font;

        tx = x;
        ty = y;
        twidth = width;
        theight = height;
    }

    public void render(Graphics g) {
        Graphics2D g2d = GUtils.setRenderingHints(g);

        g2d.setColor(background);
        g2d.fillRect(tx, ty, twidth, theight);

        g2d.setFont(font);
        g2d.setColor(foreground);

        int textx = GUtils.getCenteredTextX(x, width, g, title);
        int texty = GUtils.getCenteredTextY(y, height, g);

        g2d.drawString(title, textx, texty);

        Shape shape = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), title).getOutline();
        g2d.setStroke(new BasicStroke(0.4f));
        g2d.setColor(Color.BLACK);
        g2d.translate(textx, texty);
        g2d.draw(shape);
        g2d.translate(-textx, -texty);

    }

    public void incSize() {
        resetDecTimer();
        font = new Font(font.getFontName(), font.getStyle(), font.getSize()+1);
        incTimer.scheduleAtFixedRate(increase, 0L, 1000 / delay);
    }

    public void decSize() {
        resetIncTimer();
        font = new Font(font.getFontName(), font.getStyle(), font.getSize()-1);
        decTimer.scheduleAtFixedRate(decrease, 0L, 1000 / delay);
    }

    private void resetIncTimer() {
        incTimer.cancel();
        incTimer = new Timer(false);
        increase = new TimerTask() {
            @Override
            public void run() {
                if (tx != x - maxSize && ty != y - maxSize && twidth != width + maxSize * 2 && theight != height + maxSize * 2) {
                    tx -= 1;
                    ty -= 1;
                    twidth += 2;
                    theight += 2;
                    panel.repaint();
                } else {
                    resetIncTimer();
                }
            }
        };
    }

    private void resetDecTimer() {
        decTimer.cancel();
        decTimer = new Timer(false);
        decrease = new TimerTask() {
            @Override
            public void run() {
                if (tx != x && ty != y && twidth != width && theight != height) {
                    tx += 1;
                    ty += 1;
                    twidth -= 2;
                    theight -= 2;
                    panel.repaint();
                } else {
                    resetDecTimer();
                }
            }
        };
    }


    public int getX() {
        return tx;
    }

    public void setX(int x) {
        this.tx = x;
    }

    public int getY() {
        return ty;
    }

    public void setY(int y) {
        this.ty = y;
    }

    public int getWidth() {
        return twidth;
    }

    public void setWidth(int width) {
        this.twidth = width;
    }

    public int getHeight() {
        return theight;
    }

    public void setHeight(int height) {
        this.theight = height;
    }

    public Menu getPanel() {
        return panel;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public int getDelay() {
        return this.delay;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}