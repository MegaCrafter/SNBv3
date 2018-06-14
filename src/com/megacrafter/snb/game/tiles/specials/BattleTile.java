package com.megacrafter.snb.game.tiles.specials;

import com.megacrafter.snb.game.battle.AIBPlayer;
import com.megacrafter.snb.game.battle.BPlayer;
import com.megacrafter.snb.game.tiles.Tile;
import com.megacrafter.snb.game.tiles.TileType;
import com.megacrafter.snb.game.util.images.Assets;
import com.megacrafter.snb.panels.GamePanel;
import com.megacrafter.snb.panels.battle.BattleConPanel;

import java.awt.*;

public class BattleTile extends Tile {

    private AIBPlayer battleplayer;

    public BattleTile(int x, int y, int difficulty) {
        super(x, y, Assets.spr_battletile, true, new Rectangle(0, 0, 32, 32));
        if (this.getTexture() == Assets.spr_battletile) {
            battleplayer = new AIBPlayer(difficulty, this);
            setInteraction(() -> {
                GamePanel.game.pause();
                BattleConPanel.p2 = battleplayer;
                BattleConPanel.resetComponents();
                BattleConPanel.battlecon_menu.forward();
            });
        }
    }

    public BPlayer getBPlayer() { return this.battleplayer; }
    public void lost() {
        GamePanel.game.tiles[x/32][y/32] = new Tile(x/32, y/32, TileType.GRASS);
    }

}