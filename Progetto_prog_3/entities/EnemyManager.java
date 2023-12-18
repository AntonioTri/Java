package Progetto_prog_3.entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Progetto_prog_3.Audio.AudioPlayer;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.levels.Level;
import Progetto_prog_3.utils.LoadSave;
import static Progetto_prog_3.utils.Constants.EnemtConstants.*;

public class EnemyManager {
    
    private Playing playing;
    private BufferedImage[][] nightBorneArray;
    private ArrayList<NightBorne> nightBornes = new ArrayList<>();

    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImages();
    }

    public void addEnemies(Level level) {
        nightBornes = level.getnNightBornes();
    }

    public void update(int[][] levelData, Player player){

        boolean isAnyActive = false;

        for(NightBorne nb : nightBornes){
            //Se il nemico è attivo allora viene fatto un update
            if (nb.getActive()) {
                nb.update(levelData, player);
                isAnyActive = true;
            }
        }

        if (!isAnyActive) {
            playing.setLevelComplited(true);
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
            //Se il nemico è attivo allora viene fatto un repaint
            if (nb.getActive()){
                //QUA DENTRO, VA AGGIUNTO L'OFFSET PER IL DISEGNO PORCA LA MAZZONNA
                g.drawImage(nightBorneArray[nb.getState()][nb.getAniIndex()], 
                            (int)nb.getHitbox().x - xLevelOffset - NIGHT_BORNE_DROW_OFFSET_X + nb.flipX(), 
                            ((int)nb.getHitbox().y - NIGHT_BORNE_DROW_OFFSET_Y),
                            (NIGHT_BORNE_WIDHT + 25) * nb.flipW() ,
                             NIGHT_BORNE_HEIGHT + 25, null);

                nb.drawHitbox(g, xLevelOffset);
                nb.drowAttackBox(g, xLevelOffset);

                if (nb.getState() == NIGHT_BORNE_DIE) {
                    nb.setAniSpeed(20);
                    nb.setInvulnerability(true);
                    
                }
            }
        }
    }

    //Se il player attacca il nemico a questo viene applicato il danno del player
    public void checkEnemyHit(Rectangle2D.Float attackBox){
        for (NightBorne nb : nightBornes) {
            //Se il nemico è: ATTIVO, NON MORTO E NON è INVULNERABILE, VIENE APPLICATO IL DANNO DEL PLAYER
            if (nb.getActive() && attackBox.intersects(nb.getHitbox()) && nb.getCurrentHealth() > 0 && !nb.getInvulnerability()) {

                nb.hurt(playing.getPlayer().getDamage(), playing.getGame().getAudioPlayer());
                playing.getGame().getAudioPlayer().playSetOfEffect(AudioPlayer.NIGHTBORNE_HURT);
                return;
                
            }
        }
    }

    public void resetAllEnemyes(){

        for (AbstractEnemy nb : nightBornes) {
            nb.resetEnemy();
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
