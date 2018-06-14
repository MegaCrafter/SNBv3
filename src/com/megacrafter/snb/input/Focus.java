package com.megacrafter.snb.input;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Focus extends FocusAdapter {

    @Override
    public void focusLost(FocusEvent e) {
        for (int i = 0; i < Keyboard.keys.length; i++) {
            Keyboard.keys[i] = false;
        }
    }
}