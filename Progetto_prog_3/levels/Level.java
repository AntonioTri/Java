package Progetto_prog_3.levels;

import java.awt.Point;
import java.awt.image.BufferedImage;

import Progetto_prog_3.Game;
import Progetto_prog_3.entities.NightBorne;
import static Progetto_prog_3.utils.HelpMetods.getLevelData;
import static Progetto_prog_3.utils.HelpMetods.getNightBornes;
import static Progetto_prog_3.utils.HelpMetods.GetPlayerSpawnPoint;

import java.util.ArrayList;

public class Level {

    private BufferedImage image;
    private ArrayList<NightBorne> nightBornes;

    //Questa variabile tramite il level data, ci permete di accedere alla lunghezza del livello
    private int levelTileWide;
    //Queste servono a definire entro quale limite non bisonga più spostare la telecamera
    private int maxTileOffset;
    private int maxLevelOffsetX;

    //Punto di spawn del player
    Point playerSpawnPoint;

    private int[][] levelData;
    
    public Level(BufferedImage image){
        this.image = image;
        createLevelData();
        createEnemyes();
        calculateLevelOffsets();
        calcualtePlayerSpawnPoint();

    }
    
    private void calcualtePlayerSpawnPoint() {
        playerSpawnPoint = GetPlayerSpawnPoint(image);
    }

    private void createLevelData() {
        levelData = getLevelData(image);
    }
    
    private void createEnemyes() {
        nightBornes = getNightBornes(image);
    }

    private void calculateLevelOffsets() {
        levelTileWide = image.getWidth();
        maxTileOffset = levelTileWide - Game.TILES_IN_WIDTH;
        maxLevelOffsetX = Game.TILES_SIZE * maxTileOffset;                                                                                                                                                                                                                                                                                                           
    }

    public int getSpriteIndex(int x, int y){
        return levelData[y][x];
    }

    public int[][] getLD(){
        return levelData;
    }

    public int getLevelOffset(){
        return maxLevelOffsetX;
    }

    public ArrayList<NightBorne> getnNightBornes(){
        return nightBornes;
    }

    public Point getPlayerSpawnPoint(){
        return playerSpawnPoint;
    }


}
