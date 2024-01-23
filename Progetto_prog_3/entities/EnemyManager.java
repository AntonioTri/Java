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
