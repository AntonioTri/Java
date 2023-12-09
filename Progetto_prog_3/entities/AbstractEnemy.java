package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.*;
import static Progetto_prog_3.utils.HelpMetods.*;

import static Progetto_prog_3.utils.Constants.Directions.*;

import Progetto_prog_3.Game;

public abstract class AbstractEnemy extends Entity{

    //Variabili di ambiente
    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 20;
    protected boolean firstUpdate = true, inAir = false;
    protected float fallSpeed, gravity = (float) 0.04 * Game.SCALE;
    protected float wlakSpeed = 0.8f * Game.SCALE;
    protected int wlakDir = LEFT;
    protected int enemyTileY;
    protected float attackDistance = Game.TILES_SIZE;

    public AbstractEnemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
    
    }

    //Troviamo qui il metodo per permettere ad un nemico di muoversi verso il nostro player 
    protected void turnTowardsPlayer(Player player){

        System.out.println("Turning towards player");
        if (player.hitbox.x > hitbox.x) {
            wlakDir = RIGHT;
        } else {
            wlakDir = LEFT;
        }

    }

    //Questo metodo controlla se il nemico in questione può vedere il nostro player, applicando due logiche
    //Viene sontrollato se il giocatore è in range d visione del nemico e se il percorso verso di lui sia libero
    //Per libero si intende che non ci sono blocchi tra di loro e che ci sia un percorso attraversabile
    //un burrone non permetterebbe il movimento in quella direzione per esempio
    protected boolean canSeePlayer(int[][] levelData, Player player){

        //Controlliamo che siano nello stesso livello in y del player
        int playerYPos = (int) (player.getHitbox().y / Game.TILES_SIZE);
        if (playerYPos + 1 == enemyTileY) {
            if (isPlayerInRange(player) && isPathClear(levelData, hitbox, player.hitbox, enemyTileY)) {
                return true;
            }
        }
        return false;
    }
    
    

    //Questo metodo ci dice se il player si trova in range di visione per far muovere il nemico verso il player
    protected boolean isPlayerInRange(Player player) {

        int absValue = (int)Math.abs(player.hitbox.x - hitbox.x);
        //Se la distanza in orizzontale è minore di una lungheza di attacco che vale un blocco
        //per 5, la condizione è vera e ritora vero, altrimenti falso
        return absValue <= attackDistance * 5;
    }


    //Questo metodo ci permette di osservare se il player è in range di attacco, il metodo è uguale a quello di prima
    //Con l'unica differenza che viene fatto il controllo non su una distanza di 5 blocchi ma su una distanza di 1 ebbasta
    protected boolean isPlayerCloseForAttack(Player player){
        
        int absValue = (int)Math.abs(player.hitbox.x - hitbox.x);
        //Se la distanza in orizzontale è minore di una lungheza di attacco che vale un blocco
        return absValue <= attackDistance * 1.7;
    }

    //Metodo che cambia lo stato del nemico in questione
    protected void newState(int enemyState){
        this.enemyState = enemyState;
        aniIndex = 0;
        aniTick = 0;
    }

    //Metodo che fa muover il nemico a destra o sinistra (vengono anche fatti i controlli per evitare di farlo cadere in un fossato)
    protected void move(int[][] levelData){

        float xSpeed = 0;

            //In base alla direzione corrente viene fato muovere a destra o a sinistra
            if (wlakDir == LEFT) {
                xSpeed = -wlakSpeed;
            } else {
                xSpeed = wlakSpeed;
            }
            
            //Viene controllata la prossima posizione in cui muoversi
            if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
                if (isFloor(hitbox, xSpeed, levelData)) {
                    hitbox.x += xSpeed;
                    return;
                }
            }
        
        //Se non è stato fatto il return dal controllo di sopra, viene cambiata la direzione del movimento
        changeWalkDir();

    }

    protected void updateAnimationTick(){

        aniTick++;
        if (aniTick >= aniSpeed) {

            aniTick = 0;
            aniIndex++;

            if (aniIndex >= getSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
                if (enemyState == NIGHT_BORNE_ATTACK) {
                    enemyState = NIGHT_BORNE_IDLE;
                }
            }
        }
    }

    protected void updateInAir(int[][] levelData){

        if (canMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, levelData)) {
            hitbox.y += fallSpeed;
            fallSpeed += gravity;
        } else {
            inAir = false;
            hitbox.y = getEntityYPosFloorRoofRelative(hitbox, fallSpeed);
            //Otteniamo in questo modo la posiizone in y, che rimane costante, il nemico nono salta, si muove solo a destra e a sinistra
            //La calcoliamo solo una volta
            enemyTileY = (int)(hitbox.y / Game.TILES_SIZE);
        }

    }

    //Questo metodo viene eseguito una volta sola al momento di spawn per permettere ad un nemico di cadere sul terreno da che era in aria
    protected void firstUpdateCheck(int[][] levelData){

        if (!isEntityOnFloor(hitbox, levelData)) {
            inAir = true;
        }
        firstUpdate = false;
    
    }
    
    protected void changeWalkDir() {
        if (wlakDir == LEFT) {
            wlakDir = RIGHT;
        } else {
            wlakDir = LEFT;
        }
    }


    public int getAniIndex(){
        return aniIndex;
    }

    public int getEnemyState(){
        return enemyState;
    }


}
