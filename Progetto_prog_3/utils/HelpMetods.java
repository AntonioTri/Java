package Progetto_prog_3.utils;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Progetto_prog_3.Game;

public class HelpMetods {

    public static boolean canMoveHere(float x, float y, float width, float height, int [][]levelData){

        if (!isSolid(x, y, levelData) && !isSolid(x + width, y + height, levelData) && !isSolid(x + width, y, levelData) && !isSolid(x, y + height, levelData)) {
            return true;
        }

        return false;
    }

    private static boolean isSolid(float x , float y, int[][] levelData){

        if (x< 0 || x>= Game.GAME_WIDTH) {
            return true;
        }
        if (y<0 || y>= Game.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];

        if (value >= 48 || value <0 || value != 11) {
            return true;
        } 

        return false;
    }



    public static float getEntityXPosNextWall(Rectangle2D.Float hitbox, float xSpeed){

        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);

        if (xSpeed>0) {
            //Destra
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else {
            //Sinistra
            return currentTile * Game.TILES_SIZE;
        }


    }

    public static float getEntityYPosFloorRoofRelative(Rectangle2D.Float hitbox, float airSpeed){

        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
        
        if (airSpeed>0){
            //Caduta Libera - sul terreno
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset -1;

        } else {
            //Salto
            return currentTile * Game.TILES_SIZE;
        }


    }


}
