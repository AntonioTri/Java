package Progetto_prog_3.entities;

import java.awt.Graphics;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Progetto_prog_3.Game;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.entities.RenderChain.RenderGhost;
import Progetto_prog_3.entities.RenderChain.RenderHellBound;
import Progetto_prog_3.entities.RenderChain.RenderInterface;
import Progetto_prog_3.entities.RenderChain.RenderNightBorne;
import Progetto_prog_3.entities.RenderChain.RenderingRequest;
import Progetto_prog_3.entities.enemies.AbstractEnemy;
import Progetto_prog_3.entities.enemies.Ghost;
import Progetto_prog_3.entities.enemies.HellBound;
import Progetto_prog_3.entities.enemies.NightBorne;
import Progetto_prog_3.levels.Level;
import Progetto_prog_3.utils.LoadSave;
import static Progetto_prog_3.utils.Constants.EnemtConstants.HellBound.*;
import static Progetto_prog_3.utils.Constants.EnemtConstants.NightBorne.*;
import static Progetto_prog_3.utils.Constants.EnemtConstants.Ghost.*;

public class EnemyManager {
    
    private Playing playing;
    private BufferedImage[][] nightBorneImages, hellBoundsImage, ghostImage;
    private BufferedImage[] ghostAttack;
    private ArrayList<NightBorne> nightBornes = new ArrayList<>();
    private ArrayList<HellBound> hellBounds = new ArrayList<>();
    private ArrayList<Ghost> ghosts = new ArrayList<>();

    private ArrayList<AbstractEnemy> enemyList;
    
    //Istannze dei renderer
    RenderInterface nightborneRendered;
    RenderInterface hellBoundRendered;
    RenderInterface ghostRenderer;
    //Pacchetti di richieste contenenti l'array delle animazioni ed il tipo di nemico
    RenderingRequest[] requests;

    //Costruttore
    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImages();
        initRenderRequests();
        initRenderers();
    }

    public void addEnemies(Level level) {
        enemyList = level.getEnemies();
    }

    public void update(int[][] levelData, Player player){

        boolean isAnyActive = false;

        for(AbstractEnemy ab : enemyList){
            if (ab.getActive()) {
                ab.update(levelData, player);
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

        for(AbstractEnemy ab : enemyList){
            if (ab.getActive()) {
                nightborneRendered.renderEntity(g, playing.getPlayer(), requests, ab, xLevelOffset, yLevelOffset);
            }
        }


        // for(NightBorne nb : nightBornes){
        //     //Se il nemico è attivo allora viene fatto un repaint
        //     if (nb.getActive()){
        //         //QUA DENTRO, VA AGGIUNTO L'OFFSET PER IL DISEGNO PORCA LA MAZZONNA
        //         g.drawImage(nightBorneImages[nb.getState()][nb.getAniIndex()], 
        //                     (int)nb.getHitbox().x - xLevelOffset - NIGHT_BORNE_DROW_OFFSET_X + nb.flipX(), 
        //                     ((int)nb.getHitbox().y - yLevelOffset - NIGHT_BORNE_DROW_OFFSET_Y),
        //                     (NIGHT_BORNE_WIDHT + 25) * nb.flipW(),
        //                      NIGHT_BORNE_HEIGHT + 25, null);

        //         nb.drawHitbox(g, xLevelOffset, yLevelOffset);
        //         nb.drowAttackBox(g, xLevelOffset, yLevelOffset);

        //         if (nb.getState() == NIGHT_BORNE_DIE) {
        //             nb.setAniSpeed(20);
        //             nb.setInvulnerability(true);
                    
        //         }
        //     }
        // }

        // for (HellBound hb : hellBounds) {
            
        //     if (hb.getActive()) {
                
        //         g.drawImage(hellBoundsImage[hb.getState()][hb.getAniIndex()],
        //                     (int)hb.getHitbox().x - xLevelOffset - HELL_BOUND_DROW_OFFSET_X + hb.flipX(),
        //                     ((int) hb.getHitbox().y - yLevelOffset - HELL_BOUND_DROW_OFFSET_Y),
        //                     HELL_BOUND_WIDTH * hb.flipW(),
        //                     HELL_BOUND_HEIGHT, null);

        //         hb.drawHitbox(g, xLevelOffset, yLevelOffset);
        //         hb.drowAttackBox(g, xLevelOffset, yLevelOffset);

        //         if (hb.getState() == HELL_BOUND_DIE) {
        //             hb.setAniSpeed(20);
        //             hb.setInvulnerability(true);
        //         }
        //     }
        // }

        // for (Ghost gh : ghosts) {
            
        //     if (gh.getActive()) {
                
        //         g.drawImage(ghostImage[gh.getState()][gh.getAniIndex()],
        //                     (int)gh.getHitbox().x - xLevelOffset - GHOST_DRAW_OFFSET_X + gh.flipXP(playing.getPlayer()), 
        //                     ((int) gh.getHitbox().y - yLevelOffset - GHOST_DRAW_OFFSET_Y), 
        //                     GHOST_WIDTH * gh.flipWP(playing.getPlayer()),
        //                     GHOST_HEIGHT, null);

        //         gh.drawHitbox(g, xLevelOffset, yLevelOffset);
        //         gh.drowCircularAttackBox(g, xLevelOffset, yLevelOffset);

        //         if (gh.getState() == GHOST_ATTACK) {
        //             g.drawImage(ghostAttack[gh.getAniIndex()],
        //                         (int)(gh.getHitbox().x - (113 * Game.SCALE) - xLevelOffset), 
        //                         (int)(gh.getHitbox().y - (100 * Game.SCALE) - yLevelOffset), 
        //                         GHOST_ELECTRIC_BALL_LENGHT, 
        //                         GHOST_ELECTRIC_BALL_LENGHT, null);
        //         }

        //         if (gh.getState() == GHOST_DIE) {
        //             gh.setAniSpeed(20);
        //             gh.setInvulnerability(true);
                    
        //         }
        //     }
        // }


    }

    //Se il player attacca il nemico a questo viene applicato il danno del player
    public void checkPlayerHitEnemy(RectangularShape attackBox, int areaAttack){
        for (AbstractEnemy ab : enemyList) {
            //Se il nemico è: ATTIVO, NON MORTO E NON è INVULNERABILE, VIENE APPLICATO IL DANNO DEL PLAYER
            if (ab.getActive() && attackBox.intersects(ab.getHitbox()) && ab.getCurrentHealth() > 0 && !ab.getInvulnerability()) {

                ab.hurt(playing.getPlayer().getDamage(), playing.getGame().getAudioPlayer());

                //!!!QUA CI METTOOO UNA FUNZIONE CHE GESTISCE I SUONI IN BAASE AL TIPO DI NEMICO

                //Nel caso arrivi una flag di attacco ad area, viene fatto il controllo su tutti i nemici
                //invece che fermarsi al primo nemico colpito
                if (areaAttack == 0) return;
            
            }
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

        ghostImage = new BufferedImage[7][15];
        temp = LoadSave.getSpriteAtlas(LoadSave.GHOST_ATLAS);
        for(int j = 0; j < ghostImage.length; j++){
            for (int i = 0; i < ghostImage[j].length; i++) {
                ghostImage[j][i] = temp.getSubimage(i * GHOST_DEFAULT_WIDTH, j * GHOST_DEFAULT_HEIGHT, GHOST_DEFAULT_WIDTH, GHOST_DEFAULT_HEIGHT);
            }
        }

        ghostAttack = new BufferedImage[15];
        temp = LoadSave.getSpriteAtlas(LoadSave.GHOST_ATTACK_BALL);
        for (int i = 0; i < ghostAttack.length; i++) {
            ghostAttack[i] = temp.getSubimage(i * GHOST_ELECTRIC_BALL_DEFAULT_LENGHT, 0, GHOST_ELECTRIC_BALL_DEFAULT_LENGHT, GHOST_ELECTRIC_BALL_DEFAULT_LENGHT);
        }

    }
    
    private void initRenderers(){

        nightborneRendered = new RenderNightBorne();
        hellBoundRendered = new RenderHellBound();
        ghostRenderer = new RenderGhost();
        
        nightborneRendered.setNextHandler(hellBoundRendered);
        hellBoundRendered.setNextHandler(ghostRenderer);

    }

    private void initRenderRequests() {

        requests = new RenderingRequest[3];

        requests[0] = new RenderingRequest(nightBorneImages, HELL_BOUND);
        requests[1] = new RenderingRequest(hellBoundsImage, NIGHT_BORNE);
        requests[2] = new RenderingRequest(ghostImage, ghostAttack, GHOST);
    }
    
    public void resetAllEnemyes(){
    
        for (AbstractEnemy ab : enemyList){
            ab.resetEnemy();
        }
    
    }
}
