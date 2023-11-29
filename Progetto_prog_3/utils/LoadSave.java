package Progetto_prog_3.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Progetto_prog_3.Game;

public class LoadSave {

    public static final String PLAYER_ATLAS = "Animations.png";
    public static final String LEVEL_ATLAS = "Terrain.png";
    public static final String LEVEL_1_DATA = "level_one_data.png";


    
    public static BufferedImage getSpriteAtlas(String fileName){

        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/Progetto_prog_3/res/" + fileName);

        try {

            img = ImageIO.read(is);

        } catch (IOException e) {
            System.out.println("Mammt annur!!!");
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return img;
    }

    public static int[][] getLevelData(){

        int[][] levelData = new int [Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_1_DATA);

        for( int j = 0; j<img.getHeight(); j++){
            for (int i = 0; i < img.getWidth(); i++) {

                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                
                if(value == 48)
                    value =0;

                levelData[j][i] = value; 
            }
        }

        return levelData;

    }


}
