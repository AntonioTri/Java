package Progetto_prog_3.levels;

import java.awt.Point;
import java.awt.image.BufferedImage;

import Progetto_prog_3.Game;
import Progetto_prog_3.entities.NightBorne;
import static Progetto_prog_3.utils.HelpMetods.getLevelData;
import static Progetto_prog_3.utils.HelpMetods.getNightBornes;
import static Progetto_prog_3.utils.HelpMetods.GetPlayerSpawnPoint;

import java.util.ArrayList;

//Classe Level, memorizza le informazioni utili per la creazione di un livello e la gestione di alcuen sue caratteristiche
public class Level {

    //Làimmagine conserva il level data
    private BufferedImage image;
    //Il seguente array, i nemici di tipo nightborne
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
    
    //Funzione per calcolare il punto di spawn del player
    private void calcualtePlayerSpawnPoint() {
        playerSpawnPoint = GetPlayerSpawnPoint(image);
    }

    //Funzione per ottenere i dati del livello
    private void createLevelData() {
        levelData = getLevelData(image);
    }
    
    //Funzione per creare i nemici, ogni array viene associato ad una specifica funzione che genera quel tipo di nemico
    //!!!!! QUA PUò ESSERE IMPLEMENTATA UNA FACTORY AL 100%
    private void createEnemyes() {
        nightBornes = getNightBornes(image);
    }

    //Metodo che sposta la camera dipendentemente dalla posiizone del player
    private void calculateLevelOffsets() {
        levelTileWide = image.getWidth();
        maxTileOffset = levelTileWide - Game.TILES_IN_WIDTH;
        maxLevelOffsetX = Game.TILES_SIZE * maxTileOffset;                                                                                                                                                                                                                                                                                                           
    }

    //Funzione che ritorna il valore RGB riconosciuto durante l'estrazione delle informazioni dalle immagini levelData
    //della posizione scelta
    public int getSpriteIndex(int x, int y){
        return levelData[y][x];
    }

    //Getters e setters
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
