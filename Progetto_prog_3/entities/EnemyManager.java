package Progetto_prog_3.entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Progetto_prog_3.Audio.AudioPlayer;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.entities.enemies.Ghost;
import Progetto_prog_3.entities.enemies.HellBound;
import Progetto_prog_3.levels.Level;
import Progetto_prog_3.utils.LoadSave;
import static Progetto_prog_3.utils.Constants.EnemtConstants.HellBound.*;
import static Progetto_prog_3.utils.Constants.EnemtConstants.NightBorne.*;
import static Progetto_prog_3.utils.Constants.EnemtConstants.Ghost.*;

public class EnemyManager {
    
    private Playing playing;
    private BufferedImage[][] nightBorneImages, hellBoundsImage, ghostImage;
    private ArrayList<NightBorne> nightBornes = new ArrayList<>();
    private ArrayList<HellBound> hellBounds = new ArrayList<>();
    private ArrayList<Ghost> ghosts = new ArrayList<>();


    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImages();
    }

    public void addEnemies(Level level) {
        nightBornes = level.getnNightBornes();
        hellBounds = level.getHellBounds();
        ghosts = level.getGhosts();
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

        for (HellBound hb : hellBounds) {
            if (hb.getActive()) {
                hb.update(levelData, player);
                isAnyActive = true;
            }
        }

        for (Ghost gh : ghosts) {
            if (gh.getActive()) {
                gh.update(levelData, player);
                isAnyActive = true;
            }
        }

        if (!isAnyActive) {
            playing.setLevelComplited(true);
        }

    }

    public void draw(Graphics g, int xLevelOffset, int yLevelOffset){

        drawEnemies(g, xLevelOffset, yLevelOffset);
        

    };

    //  ATTENZIONE !!!!!!!!!
    //LA HITBOX STA SEMPRE NELLO STESSO MODO E NON C'E' MODO DI SPOSTARLA, SE SI VUOLE CENTRARE IL TIZIO DENTRO LA HITBOX, BISOGNA
    //SPOSTARE IL DISEGNO QUANDO VIENE DISEGNATO LO SPRITE, ALTRIMENTI A VOGLIA DI IMPAZZIRE
    private void drawEnemies(Graphics g, int xLevelOffset, int yLevelOffset) {

        for(NightBorne nb : nightBornes){
            //Se il nemico è attivo allora viene fatto un repaint
            if (nb.getActive()){
                //QUA DENTRO, VA AGGIUNTO L'OFFSET PER IL DISEGNO PORCA LA MAZZONNA
                g.drawImage(nightBorneImages[nb.getState()][nb.getAniIndex()], 
                            (int)nb.getHitbox().x - xLevelOffset - NIGHT_BORNE_DROW_OFFSET_X + nb.flipX(), 
                            ((int)nb.getHitbox().y - yLevelOffset - NIGHT_BORNE_DROW_OFFSET_Y),
                            (NIGHT_BORNE_WIDHT + 25) * nb.flipW(),
                             NIGHT_BORNE_HEIGHT + 25, null);

                nb.drawHitbox(g, xLevelOffset, yLevelOffset);
                nb.drowAttackBox(g, xLevelOffset, yLevelOffset);

                if (nb.getState() == NIGHT_BORNE_DIE) {
                    nb.setAniSpeed(20);
                    nb.setInvulnerability(true);
                    
                }
            }
        }

        for (HellBound hb : hellBounds) {
            
            if (hb.getActive()) {
                
                g.drawImage(hellBoundsImage[hb.getState()][hb.getAniIndex()],
                            (int)hb.getHitbox().x - xLevelOffset - HELL_BOUND_DROW_OFFSET_X + hb.flipX(),
                            ((int) hb.getHitbox().y - yLevelOffset - HELL_BOUND_DROW_OFFSET_Y),
                            HELL_BOUND_WIDTH * hb.flipW(),
                            HELL_BOUND_HEIGHT, null);

                hb.drawHitbox(g, xLevelOffset, yLevelOffset);
                hb.drowAttackBox(g, xLevelOffset, yLevelOffset);

                if (hb.getState() == HELL_BOUND_DIE) {
                    hb.setAniSpeed(20);
                    hb.setInvulnerability(true);
                }
            }
        }

        for (Ghost gh : ghosts) {
            
            if (gh.getActive()) {
                
                g.drawImage(ghostImage[gh.getState()][gh.getAniIndex()],
                            (int)gh.getHitbox().x - xLevelOffset - GHOST_DRAW_OFFSET_X + gh.flipX(playing.getPlayer()), 
                            ((int) gh.getHitbox().y - yLevelOffset - GHOST_DRAW_OFFSET_Y), 
                            GHOST_WIDTH * gh.flipW(playing.getPlayer()),
                            GHOST_HEIGHT, null);

                gh.drawHitbox(g, xLevelOffset, yLevelOffset);
                gh.drowAttackBox(g, xLevelOffset, yLevelOffset);

                if (gh.getState() == NIGHT_BORNE_DIE) {
                    gh.setAniSpeed(20);
                    gh.setInvulnerability(true);
                    
                }
            }
        }


    }

    //Se il player attacca il nemico a questo viene applicato il danno del player
    public void checkPlayerHitEnemy(Rectangle2D.Float attackBox, int areaAttack){
        for (NightBorne nb : nightBornes) {
            //Se il nemico è: ATTIVO, NON MORTO E NON è INVULNERABILE, VIENE APPLICATO IL DANNO DEL PLAYER
            if (nb.getActive() && attackBox.intersects(nb.getHitbox()) && nb.getCurrentHealth() > 0 && !nb.getInvulnerability()) {

                nb.hurt(playing.getPlayer().getDamage(), playing.getGame().getAudioPlayer());
                playing.getGame().getAudioPlayer().playSetOfEffect(AudioPlayer.NIGHTBORNE_HURT);
                
                //Nel caso arrivi una flag di attacco ad area, viene fatto il controllo su tutti i nemici
                //invece che fermarsi al primo nemico colpito
                if (areaAttack == 0) return;
            
            }
        }

        for (HellBound hb : hellBounds) {
            if (hb.getActive() && attackBox.intersects(hb.getHitbox()) && hb.getCurrentHealth() > 0 && !hb.getInvulnerability()) {
                hb.hurt(playing.getPlayer().getDamage(), playing.getGame().getAudioPlayer());
                if (areaAttack == 0) return;
            }
        }

        for (Ghost gh : ghosts) {
            if (gh.getActive() && attackBox.intersects(gh.getHitbox()) && gh.getCurrentHealth() > 0 && !gh.getInvulnerability()) {
                gh.hurt(playing.getPlayer().getDamage(), playing.getGame().getAudioPlayer());
                if (areaAttack == 0) return;
            }
        }

    }

    public void resetAllEnemyes(){

        for (AbstractEnemy nb : nightBornes) {
            nb.resetEnemy();
        }

        for (AbstractEnemy hb : hellBounds) {
            hb.resetEnemy();
        }

        for (AbstractEnemy gh : ghosts) {
            gh.resetEnemy();
        }

    }



    private void loadEnemyImages() {
        
        nightBorneImages = new BufferedImage[5][23];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.NIGHT_BORNE_ATLAS);
        for(int j = 0; j < nightBorneImages.length; j++){
            for (int i = 0; i < nightBorneImages[j].length; i++) {
                nightBorneImages[j][i] = temp.getSubimage(i * NIGHT_BORNE_DEFAULT_WIDHT, j * NIGHT_BORNE_DEFAULT_HEIGHT, NIGHT_BORNE_DEFAULT_HEIGHT, NIGHT_BORNE_DEFAULT_HEIGHT );
            }
        }

        hellBoundsImage = new BufferedImage[6][12];
        temp = LoadSave.getSpriteAtlas(LoadSave.HELL_BOUND_ATLAS);
        for (int j = 0; j < hellBoundsImage.length; j++) {
            for (int i = 0; i < hellBoundsImage[j].length; i++) {
                hellBoundsImage[j][i] = temp.getSubimage( i * HELL_BOUND_DEAFULT_WIDTH, j* HELL_BOUND_DEAFULT_HEIGHT, HELL_BOUND_DEAFULT_WIDTH, HELL_BOUND_DEAFULT_HEIGHT);
            }
        }

        ghostImage = new BufferedImage[7][13];
        temp = LoadSave.getSpriteAtlas(LoadSave.GHOST_ATLAS);
        for(int j = 0; j < ghostImage.length; j++){
            for (int i = 0; i < ghostImage[j].length; i++) {
                ghostImage[j][i] = temp.getSubimage(i * GHOST_DEFAULT_WIDTH, j * GHOST_DEFAULT_HEIGHT, GHOST_DEFAULT_WIDTH, GHOST_DEFAULT_HEIGHT);
            }
        }

    }
}
