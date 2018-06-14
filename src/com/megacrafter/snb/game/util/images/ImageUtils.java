package com.megacrafter.snb.game.util.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageUtils.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}