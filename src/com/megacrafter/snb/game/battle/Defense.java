package com.megacrafter.snb.game.battle;

import java.util.ArrayList;

public class Defense {

    private String name;
    private ArrayList<Element> types = new ArrayList<>();
    private int flag = 0;

    public Defense(String name, Element... type) {
        this.name = name;
        for (Element type1 : type) {
            types.add(type1);
            this.flag += type1.getFlag();
        }
    }

    public String getName() {
        return name;
    }
}