package Progetto_prog_3.utils;

import static Progetto_prog_3.utils.Constants.EnemtConstants.NIGHT_BORNE;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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

    //Entity
    public static final String PLAYER_ATLAS = "Animations.png";
    public static final String NIGHT_BORNE_ATLAS = "NightBorne.png";
    
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

    //Questa funzione invece l'ho trovata su internet, non so come funzioni di preciso, utilizza i colori rgb per mappare le
    //caratteristiceh del livello dallàimmagine level_one_data.png, mappato il terreno poi possono essere posizionati i mattoncini giusti
    //per la costruzione del livello
    public static int[][] getLevelData(){

        BufferedImage img = getSpriteAtlas(LEVEL_1_DATA);
        int[][] levelData = new int [img.getHeight()][img.getWidth()];

        for( int j = 0; j<img.getHeight(); j++){
            for (int i = 0; i < img.getWidth(); i++) {

                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                
                if(value >= 48)
                    value = 11;

                levelData[j][i] = value; 
            }
        }

        return levelData;

    }

    public static ArrayList<NightBorne> getNightBornes(){

        BufferedImage img = getSpriteAtlas(LEVEL_1_DATA);
        ArrayList<NightBorne> list = new ArrayList<>();

        for( int j = 0; j<img.getHeight(); j++){
            for (int i = 0; i < img.getWidth(); i++) {

                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                
                if(value == NIGHT_BORNE){
                    list.add(new NightBorne(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
                }
            }
        }

        return list;

    }


}
