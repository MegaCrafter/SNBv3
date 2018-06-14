package com.megacrafter.snb.game.tiles;

import com.megacrafter.snb.game.util.images.Assets;

import java.awt.*;

public enum TileType {

    GRASS(Assets.spr_grasstile, false, false),
    FILLED_GRASS(Assets.spr_filledgrasstile, false, false),
    FLOWER_GRASS_1(Assets.spr_flowergrasstile1, false, false),
    FLOWER_GRASS_2(Assets.spr_flowergrasstile2, false, false),
    SHOP(Assets.spr_shoptile, true, false),
    TREE_GRASS_PART1(Assets.spr_treetile_part1, true, new Rectangle(15, 7, 17, 25)),
    TREE_GRASS_PART2(Assets.spr_treetile_part2, true, new Rectangle(0, 3, 32, 29)),
    TREE_GRASS_PART3(Assets.spr_treetile_part3, true, new Rectangle(0, 7, 21, 25)),
    TREE_GRASS_PART4(Assets.spr_treetile_part4, true, new Rectangle(13, 0, 19, 32)),
    TREE_GRASS_PART5(Assets.spr_treetile_part5, true, true),
    TREE_GRASS_PART6(Assets.spr_treetile_part6, true, new Rectangle(0, 0, 21, 22)),
    TREE_GRASS_PART7(Assets.spr_treetile_part7, false, true),
    TREE_GRASS_PART8(Assets.spr_treetile_part8, true, new Rectangle(0, 0, 32, 27)),
    TREE_GRASS_PART9(Assets.spr_treetile_part9, true, new Rectangle(0, 0, 3, 4));

    public Image texture;
    public boolean solid;
    public Rectangle solidbox = new Rectangle(0, 0, 32, 32);
    public boolean isPart;
    public Runnable interaction;

    TileType(Image texture, boolean solid, boolean isPart) {
        this.texture = texture;
        this.solid = solid;
        this.isPart = isPart;
        this.interaction = () -> {};
    }

    TileType(Image texture, boolean isPart, Rectangle solidbox) {
        this.texture = texture;
        this.solid = true;
        this.solidbox = solidbox;
        this.isPart = isPart;
        this.interaction = () -> {};
    }

    TileType(Image texture, boolean solid, boolean isPart, Runnable interaction) {
        this.texture = texture;
        this.solid = solid;
        this.isPart = isPart;
        this.interaction = interaction;
    }

    TileType(Image texture, boolean isPart, Rectangle solidbox, Runnable interaction) {
        this.texture = texture;
        this.solid = true;
        this.solidbox = solidbox;
        this.isPart = isPart;
        this.interaction = interaction;
    }

    public static TileType getByTexture(Image texture) {
        for (TileType type : TileType.values()) {
            if (type.texture == texture) return type;
        }
        return null;
    }

}