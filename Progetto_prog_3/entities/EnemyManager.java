package Progetto_prog_3.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.utils.LoadSave;
import static Progetto_prog_3.utils.Constants.EnemtConstants.*;

public class EnemyManager {
    
    private Playing playing;
    private BufferedImage[][] nightBorneArray;
    private ArrayList<NightBorne> nightBornes = new ArrayList<>();

    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImages();
        addEnemies();
    }

    private void addEnemies() {
        nightBornes = LoadSave.getNightBornes();
        System.out.println("Enemy Number = " + nightBornes.size());
    }

    public void update(int[][] levelData, Player player){

        for(NightBorne nb : nightBornes){
            nb.update(levelData, player);
        }

    }

    public void draw(Graphics g, int xLevelOffset){

        drawNightBornes(g, xLevelOffset);

    };

    //  ATTENZIONE !!!!!!!!!
    //LA HITBOX STA SEMPRE NELLO STESSO MODO E NON C'E' MODO DI SPOSTARLA, SE SI VUOLE CENTRARE IL TIZIO DENTRO LA HITBOX, BISOGNA
    //SPOSTARE IL DISEGNO QUANDO VIENE DISEGNATO LO SPRITE, ALTRIMENTI A VOGLIA DI IMPAZZIRE
    private void drawNightBornes(Graphics g, int xLevelOffset) {

        for(NightBorne nb : nightBornes){
            //QUA DENTRO, VA AGGIUNTO L'OFFSET PER IL DISEGNO PORCA LA MAZZONNA
            g.drawImage(nightBorneArray[nb.getEnemyState()][nb.getAniIndex()], (int)nb.getHitbox().x - xLevelOffset - NIGHT_BORNE_DROW_OFFSET_X, (int)nb.getHitbox().y - NIGHT_BORNE_DROW_OFFSET_Y, NIGHT_BORNE_WIDHT, NIGHT_BORNE_HEIGHT, null);
            nb.drawHitbox(g, xLevelOffset);
        }

    }

    private void loadEnemyImages() {
        
        nightBorneArray = new BufferedImage[5][23];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.NIGHT_BORNE_ATLAS);
        for(int j = 0; j < nightBorneArray.length; j++){
            for (int i = 0; i < nightBorneArray[j].length; i++) {
                nightBorneArray[j][i] = temp.getSubimage(i * NIGHT_BORNE_DEFAULT_WIDHT, j * NIGHT_BORNE_DEFAULT_HEIGHT, NIGHT_BORNE_DEFAULT_HEIGHT, NIGHT_BORNE_DEFAULT_HEIGHT );
            }
        }
    }
}
