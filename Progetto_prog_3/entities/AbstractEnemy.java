package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.*;
import static Progetto_prog_3.utils.Constants.EnemtConstants.Ghost.*;
import static Progetto_prog_3.utils.Constants.EnemtConstants.NightBorne.*;
import static Progetto_prog_3.utils.Constants.EnemtConstants.HellBound.*;
import static Progetto_prog_3.utils.Constants.PlayerConstants.IDLE;
import static Progetto_prog_3.utils.HelpMetods.*;
import static Progetto_prog_3.utils.Constants.Directions.*;
import static Progetto_prog_3.utils.Constants.GRAVITY;
import java.awt.geom.Rectangle2D;
import Progetto_prog_3.Game;
import Progetto_prog_3.Audio.AudioPlayer;

public abstract class AbstractEnemy extends Entity{

    //Variabili di ambiente
    protected int enemyType;
    protected int aniSpeed = 20;
    protected boolean firstUpdate = true;
    protected int wlakDir = LEFT;
    protected int enemyTileY;
    protected float attackDistance, visionDistance;
    protected boolean attackChecked = false, invulnerability = false;

    //Variabile per osservare se è morto oppure no
    protected boolean active = true;

    public AbstractEnemy(float x, float y, int width, int height, int enemyType) {
        //In base al tipo di nemico che stiamo instanziando, vengono definite caratteristiche uniche come il danno o la vita massima
        super(x, y, width, height);
        this.enemyType = enemyType;
        this.walkSpeed *= 0.8f;
        initHitbox(x, y, width, height);
        maxHealth = getMaxHealth(enemyType);
        visionDistance = getVisionDistance(enemyType);
        attackDistance = getAttackDistance(enemyType);
        currentHealth = maxHealth;
    
    }

    public abstract void update(int[][] levelData, Player player);

    //Troviamo qui il metodo per permettere ad un nemico di muoversi verso il nostro player 
    protected void turnTowardsPlayer(Player player){

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
        int playerYPos = (int) ((player.getHitbox().y + player.getHitbox().height - 1)/ Game.TILES_SIZE);

        if (playerYPos == enemyTileY) {
            //Se è così controlliamo che il player sia in range di visione e che il percorso verso il player sia percorribile
            if (isPlayerInRangeOfVision(player) && isPathClear(levelData, hitbox, player.hitbox, enemyTileY)) {
                //Se tutte le condizioni sono vere ritorniamo vero ed il nemico può vedere il player e raggiungerlo
                return true; 
            }
        }

        return false;
    }

    //Questo metodo ci dice se il player si trova in range di visione per far muovere il nemico verso il player
    protected boolean isPlayerInRangeOfVision(Player player) {

        //La distanza viene calcolata in base al centro della hitbox delle entità
        int absValue = (int)Math.abs((player.getHitbox().x + player.getHitbox().width / 2) - (hitbox.x + hitbox.width / 2));
        //Se la distanza in orizzontale è minore di una lungheza di attacco che vale un blocco
        //per 5, la condizione è vera e ritora vero, altrimenti falso
        return absValue <= visionDistance;
    }


    //Questo metodo ci permette di osservare se il player è in range di attacco, il metodo è uguale a quello di prima
    //Con l'unica differenza che viene fatto il controllo non su una distanza di 5 blocchi ma su una distanza di 1 ebbasta
    protected boolean isPlayerCloseForAttack(Player player){

        //La distanza viene calcolata in base al centro della hitbox delle entità
        int absValue = (int)Math.abs((player.getHitbox().x + player.getHitbox().width / 2) - (hitbox.x + hitbox.width / 2));
        //Se la distanza in orizzontale è minore di una lungheza di attacco che vale un blocco
        return absValue <= attackDistance;
    }

    //Metodo che cambia lo stato del nemico in questione
    protected void newState(int state){
        this.state = state;
        aniIndex = 0;
        aniTick = 0;
    }

    //Metodo che fa muover il nemico a destra o sinistra (vengono anche fatti i controlli per evitare di farlo cadere in un fossato)
    protected void move(int[][] levelData){

        float xSpeed = 0;

            //In base alla direzione corrente viene fato muovere a destra o a sinistra
            if (wlakDir == LEFT) {
                xSpeed = -walkSpeed;
            } else {
                xSpeed = walkSpeed;
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

    //Questo metodo ci poermette di causare danno ad un nemico se questo viene colpito dal player
    public void hurt(int amount, AudioPlayer ap){

        currentHealth -= amount;

        if (currentHealth <= 0) {
            newState(NIGHT_BORNE_DIE);
            ap.playEffect(AudioPlayer.NIGHTBORRNE_DIE);
        } else {
            newState(NIGHT_BORNE_HITTED);
        }
        
    }

    //questo metodo invece ci permette di applicare danno al player se il nemico lo colpisce
    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitbox)) {
            //Il segno meno serve a mandare una somma negativa alla vita del player, non lo stiamo curando, lo stiamo picchindo
            player.changeHealth(-getEnemyDamage(enemyType));
            attackChecked = true;
        }
    }

    protected void updateAnimationTick(){

        aniTick++;
        if (aniTick >= aniSpeed) {

            aniTick = 0;
            aniIndex++;

            if (aniIndex >= getSpriteAmount(enemyType, state)) {
                aniIndex = 0;
                //Se avviene un cambiamento di stato, andremo a fare solo una animazione di quello stato
                if (enemyType == NIGHT_BORNE) {
                    switch (state) {
                        case NIGHT_BORNE_ATTACK, NIGHT_BORNE_HITTED -> state = IDLE;
                        case NIGHT_BORNE_DIE -> this.active = false;
                    }

                } else if (enemyType == HELL_BOUND) {
                    switch (state) {
                        case HELL_BOUND_JUMP, HELL_BOUND_RUN, HELL_BOUND_SLIDE -> state = HELL_BOUND_WALK;
                        case HELL_BOUND_HIT -> {
                            state = HELL_BOUND_WALK;
                            aniSpeed = 20;
                        }
                        case HELL_BOUND_DIE -> {
                            this.active = false;
                        }
                    }

                } 
            }
        }
    }

    //Metodo che permette la caduta del nemico sul terreno al momento dello spawn
    protected void updateInAir(int[][] levelData){

        if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
            hitbox.y += airSpeed;
            airSpeed += GRAVITY;
        } else {
            inAir = false;
            hitbox.y = getEntityYPosFloorRoofRelative(hitbox, airSpeed);
            //Otteniamo in questo modo la posiizone in y
            enemyTileY = (int)((hitbox.y + hitbox.height - 1) / Game.TILES_SIZE);
        }

    }

    //Questo metodo viene eseguito una volta sola al momento di spawn per permettere ad un nemico di cadere sul terreno da che era in aria
    protected void firstUpdateCheck(int[][] levelData){

        if (!isEntityOnFloor(hitbox, levelData)) {
            inAir = true;
        }
        firstUpdate = false;
    
    }
    
    //Questo metodo ci serve a cambiare direzione del movimento quando ne si trova bisogno
    protected void changeWalkDir() {
        if (wlakDir == LEFT) {
            wlakDir = RIGHT;
        } else {
            wlakDir = LEFT;
        }
    }

    //Questo metodo ci permette di resettare i valori del nemico in questione ai valori di partenza del livello quando ne si trova bisogno
    public void resetEnemy(){

        hitbox.x = x;
        hitbox.y = y;
        airSpeed = 0;
        active = true;
        firstUpdate = true;
        invulnerability = false;
        currentHealth = maxHealth;
        newState(NIGHT_BORNE_IDLE);
    }

    public boolean getActive(){
        return active;
    }

    public int getAniIndex(){
        return aniIndex;
    }

    public int getAniSpeed(){
        return aniSpeed;
    }

    public void setAniSpeed(int value){
        this.aniSpeed = value;
    }

    public void setInvulnerability(boolean invulnerability) {
        this.invulnerability = invulnerability;
    }

    public boolean getInvulnerability(){
        return invulnerability;
    }

    


}
