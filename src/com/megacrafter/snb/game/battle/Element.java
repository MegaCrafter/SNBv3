package com.megacrafter.snb.game.battle;

/*
Ateş (Güç)
Su (Dikkat Dağıtma)
Işık (Hız)
Elektrik (Hız)
Karanlık (Dikkat Dağıtma)
Rüzgar (Hız)
Akıl (Dikkat Dağıtma)
Toprak (Güç)
Radyasyon (Güç)

Ateş Savunması (Su, Karanlık, Akıl, Elektrik)
Su Savunması (Ateş, Işık, Elektrik, Toprak)
Işık Savunması (Karanlık, )
Elektrik Savunması ()
Karanlık Savunma ()
Rüzgar Savunması ()
Akıl Savunması ()
Toprak Savunması (Elektrik, )
Radyasyon Savunması (Elektrik, )
 */
public enum Element {

    FIRE(1, "Ateş"),
    WATER(2, "Su"),
    LIGHT(4, "Işık"),
    ELECTRIC(8, "Elektrik"),
    DARKNESS(16, "Karanlık"),
    WIND(32, "Rüzgar"),
    MIND(64, "Akıl"),
    EARTH(128, "Toprak"),
    RADIATION(256, "Radyasyon");

    int flag;
    String name;

    Element(int flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    public int getFlag() { return this.flag; }
    public String getName() { return this.name; }

}