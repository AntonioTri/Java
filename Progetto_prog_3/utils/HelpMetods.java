package Progetto_prog_3.utils;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Progetto_prog_3.Game;

public class HelpMetods {

    //Questa funzione ci indica de gli spazi attorno al nostro giocatore sono solidi oppure no
    //Nel caso siano disponibili spazi in cui muoversi, viene ritornato vero, altrimenti se viene
    //ritornato falso, significa che la hitbox del personagio si sta compenetrando con un muro e viene impedito il movimento
    //in quella direzione
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!isSolid(x, y, lvlData))
			if (!isSolid(x + width, y + height, lvlData))
				if (!isSolid(x + width, y, lvlData))
					if (!isSolid(x, y + height, lvlData))
						return true;
		return false;
	}

    //Questa funzione viene utilizzata dalla precedente per osservare se il pixel della direzione in cui ci si muove
    //appartenga ad un muro oppure no, viene verificata la non appartenenza al level data corrente
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

    //Questa funzione serve per le colisioni sui muri destra e sinistra, vengono calcolati gli offset, ovvero la distanza tra gli elementi
    //player e muri, e vengono risommati al movimento se questo sta facendo overlapping con del terreno
    public static float getEntityXPosNextWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
		if (xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		} else
			// Left
			return currentTile * Game.TILES_SIZE;
	}

    //La successiva è identica alla precedente, soltanto che analizza la possibilità del movimento in verticale, anzicchè in orizzontale
	public static float getEntityYPosFloorRoofRelative(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			// Falling - touching floor
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1 + Game.TILES_SIZE;
		} else
			// Jumping
			return currentTile * Game.TILES_SIZE;

	}

    //Questa qui invece osserva se il player o in generale l'entità, sta toccando il terreno
    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
        
        //Controllo sul pixel di estrema destra ed estrema sinistra, se sono entrambi non blocco, allora siamo in aria
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData) && !isSolid(hitbox.x + hitbox.width + 1, hitbox.y + hitbox.height, levelData)) {
            return false;
        }
        
        return true;
    }



}
