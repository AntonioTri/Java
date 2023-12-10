package Progetto_prog_3.utils;

import static Progetto_prog_3.utils.Constants.EnemtConstants.NIGHT_BORNE;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Progetto_prog_3.Game;
import Progetto_prog_3.entities.NightBorne;

public class LoadSave {

    //Stringe rapparesentative per ottenere una specifica immagine png da caricare
    //public static final String LEVEL_1_DATA = "level_one_data.png";
    
    //Assest
    public static final String LEVEL_1_DATA = "level_one_data_long.png";
    public static final String LEVEL_ATLAS = "Terrain.png";
    public static final String PLAYING_BACKGROUND_IMAGE = "playing_bkgd_img.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    
    //UI
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String SOUND_BUTTON = "sound_button.png";
    public static final String VOLUME_BUTTON = "volume_buttons.png";
    public static final String PRH_BUTTONS = "prh_buttons.png";
    public static final String HOME_BACKGROUND_IMAGE = "background_menu.png";
    public static final String HEALT_POWER_BAR = "health_power_bar.png";
    public static final String LEVEL_COMPLITED = "level_completed_sprite.png";

    //Entity
    public static final String PLAYER_ATLAS = "Animations.png";
    public static final String NIGHT_BORNE_ATLAS = "NightBorne.png";

    

    public static BufferedImage[] getAllLevels(){

        //Dichiariamo un URL contenente le risorse per il livello
        URL url = LoadSave.class.getResource("/Progetto_prog_3/res/lvls");
        File file = null;

        //Try catch per gestire gli errori
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //Dichiariamo due array di File, il primo importa tramite il metodo listFile, i file dell'url dichiarato sopra
        //Il secondo è semplicemente vuoto
        File[] files = file.listFiles();
        File[] sortedFiles = new File[files.length];

        //Viene eseguito un algoritmo poco efficiente per ordinare i file nel caso non vengano ordinati di default
        //In ogni caso fa 6 iterazioni
        for (int i = 0; i < sortedFiles.length; i++) {
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png")) {
                    sortedFiles[i] = files[j];
                }
            }
        }

        //Check per controllare se sono stati effettivamente presi i file oppure no
        // for (File f : sortedFiles) {
        //     System.out.println("File: " + f.getName());
        // }

        //Memorizziamo i file come immagini in un aray di immagini
        BufferedImage[] imgs = new BufferedImage[sortedFiles.length];
        
        for (int i = 0; i < imgs.length; i++) {

            try {
                imgs[i] = ImageIO.read(sortedFiles[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return imgs;

    }


    //Questa funzione carica i dati di un png in una immagine, date delle varibili, quelle sopra, sceglie quale immagine caricare
    //Questa dovrebbe essere una factory 
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
    

    


}
