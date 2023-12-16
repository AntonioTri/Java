package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.PlayerConstants.*;
import static Progetto_prog_3.utils.HelpMetods.*;
import static Progetto_prog_3.utils.Constants.GRAVITY;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import Progetto_prog_3.Game;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.utils.LoadSave;

public class Player extends Entity{

    //Variabili per la gestione dei frame
    private int aniSpeed = 15;

    //Variabile per definire l'azione del player
    private boolean left, right, up, down, jump;
    private boolean moving = false, attacking = false;

    //Variabili per la memorizzazione di frame
    private BufferedImage[][] animations;
    private int[][] levelData;

    //Variabili per le hitbox
    private float XOffset = 11 * Game.SCALE;
    private float YOffset = 25 * Game.SCALE;

    //Variabili per il salto
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;

    // StatusBarUI
	private BufferedImage statusBarImg;

	private int statusBarWidth = (int) (192 * Game.SCALE);
	private int statusBarHeight = (int) (58 * Game.SCALE);
	private int statusBarX = (int) (10 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);

	private int healthBarWidth = (int) (150 * Game.SCALE);
	private int healthBarHeight = (int) (4 * Game.SCALE);
	private int healthBarXStart = (int) (34 * Game.SCALE);
	private int healthBarYStart = (int) (14 * Game.SCALE);

    //Variabili per definire la barra della vita del giocatore
	private int healthWidth = healthBarWidth;

	private int flipX = 0;
	private int flipW = 1;

    //Posizione verticale
    private int tyleY = 0;

	private boolean attackChecked;
    private int damage = 5;
    private Playing playing;
    

    //Costruttore richiamante la classe estesa
    public Player(float x, float y, int width, int height, Playing playing){
        super(x, y, width, height);
        this.playing = playing;
        initStates();
        loadAnimations();
        initHitbox(x, y, 25 * Game.SCALE, 37 * Game.SCALE);
        initAttackBox();
    }

    private void initStates(){
        this.state = IDLE;
        this.maxHealth = 100;
        this.currentHealth = 35;
    }

    private void initAttackBox(){
        attackBox = new Rectangle2D.Float(x, y, (int)(16 * Game.SCALE), (int)(30 * Game.SCALE));
    }

    //funzione per fare l'update delle caratterisctiche del personaggio
    public void update(){

        updateHealthBar();

        if (currentHealth <= 0 ) {
            playing.setGameOver(true);
            return;
        }

        updatePosition();

        //Se il player si sta muovendo può interagire con gli oggetti della mappa
        if (moving) {
            checkPotionTouched();
            checkSpikesTouched();
            tyleY = (int)(hitbox.y / Game.TILES_SIZE);
        }

        if (attacking) {
            checkAttack();
        }

        updateAnimationTick();
        setAnimation();
        updateAttackBox();
        
    }

    

    //In questa funzione decidiamo la posizione della attackbox in base al movimento del giocatore e relativamente alla posizione dello stesso    
    private void updateAttackBox() {

        if (right) {
            attackBox.x = hitbox.x + hitbox.width;

        } else if (left) {
            attackBox.x = hitbox.x - hitbox.width + (int)(Game.SCALE * 9);

        }

        attackBox.y = hitbox.y + (Game.SCALE * 5);

    }

    //Dato che il programma viene refreshato 120 volte al secondo dato il game loop, aniIndex verrà modificato 
    //mano mano che avanzano i tick di gioco e verra' quindi mostrata una immagine differente ogni 40 tick
    public void render(Graphics g, int xLevelOffset){

        //In questa draw vi scorrono diverse logiche, la prima è la scelta dello sprite da utilizzare
        //La seconda è che l'immagine viene disegnata con uno spostamento
        //La terza è che  vengono aggiunte variabili di "flip", se il personagio cammina verso destra è tutto apposto
        //Se va verso sinistra, l'immagine viene mostrata riflessa al contrario rispetto al suo asse y, e per ovviare a questo problema
        //Le viene sommato un offset agiuntivo per spostarla di nuovo nella posizione corretta 
        g.drawImage(animations[state][aniIndex], 
                    (int)(hitbox.x - XOffset) - xLevelOffset + flipX + 14, 
                    (int)(hitbox.y - YOffset), 
                    hitBoxWidth * flipW, hitBoxHeight, null);

        drawHitbox(g, xLevelOffset);
        drowAttackBox(g, xLevelOffset);
        drawUI(g);
    }

    //Disegna la UI di gioco, per ora soltanto la health bar e la power bar
    private void drawUI(Graphics g) {

        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.RED);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);

    }

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float)maxHealth) * healthBarWidth);
    }

    //Metodo che ci permette di modifdicare il valore degli HP
    public void changeHealth(int value){

        currentHealth += value;

        if (currentHealth <= 0) {
            currentHealth = 0;
            //GAME OVER HAPPENS

        } else if (currentHealth >= maxHealth){
            currentHealth = maxHealth;
        }

    } 

    //Viene fatto un controllo ogni volta che il player attacca per vdere se sta colpendo un nemico
    //in tal caso deve applicare i danni a quel nemico
    private void checkAttack() {

        if (!attackChecked && aniIndex != 1) {
            return;
        }

        //Viene settato l'attacco a true se il controllo precedente fallisce ( vuol dire che si sta attaccando perchè la flag è !vera ),
        //il game loop lo resetterebbe a falso in tutti i casi con una velocità quasi istantanea, allora serve tenerlo a vero per
        //evitare che la chiamata di questa funzione ritorni nel'update successivo
        attackChecked = true;

        //Viene fatto il controllo sul danno solo quando l'animazione si trova in un certo indice
        if (aniIndex == 2 || aniIndex == 3){

            playing.checkEnemyHit(attackBox);
            playing.checkObjectHit(attackBox);
            attackChecked = false;
            
        }
    }
    
    //Funzione che controlla se il player sta toccando una pozione
    private void checkPotionTouched() {
        playing.checkPotionTouched(hitbox);
    }

    //Funzione che controlla se il player sta toccando una Spina
    private void checkSpikesTouched() {
        playing.checkSpikesTouched(this);
    }
    
    //Questa funzione fa avanzare il frame di animazione del personaggio ogni 40 tick del programma
    //Se l'indice diventa magiore del numero di frame viene ripristinato a 0 e si riparte da capo
    private void updateAnimationTick() {

        aniTick++;
        if (aniTick >= aniSpeed) {

            aniTick = 0;
            aniIndex++;

            if (aniIndex >= getSpriteAmount(state)) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }

    //Qui viene settata l'animazione in base agli imput del giocatore, per ogni azione viene settata una velocità di animazione unica
    private void setAnimation() {

        int startAnimation = state;

        if (moving) {
            aniSpeed = 15;
            state = RUNNING;

        } else {
            aniSpeed = 20;
            state = IDLE;
        }

        if (inAir) {
            if (airSpeed<0) {
                state = JUMPING_UP;
            } else {
                state = JUMPING_DOWN;
            }
        }

        if (attacking) {
            state = LIGHT_ATTACK;
        }

        /*Se la animazione di arrivo e' diversa dalla animazione di fine funzione
            allora si e' creato un cambiamento di stato e vengono resettati i valori
            di scelta fotogramma e di tick di animazione per permettere alla animazione
            di incominciare dall'inizio e di non fare glitch strani
        */
        if (startAnimation != state) {
            resetAnimationTick();
        }

    }

    //Vengono impostati i valori dell'animazione che deve essere eseguita a 0
    private void resetAnimationTick() {
        aniIndex = 0;
        aniTick = 0;
    }

    //Ancora, all'interno di questa funzione viene gestito il movimento, impedendo quelli
    //concorrenti
    private void updatePosition() {
		moving = false;

        //Salto
		if (jump) jump();

        //Se non si sta facendo nessuna azione ritorna, non facendo calcoli
		if (!left && !right && !inAir)
			return;
    
        //Impedimento di movimenti concorrenti
        if (!inAir && ((!left && !right) || (right && left))) {
            return;
        }

        //Cambi del movimento destra e sinistra, si aggiunge una quantità alla velocità
        //e vengono settate le variabili per girare l'immagine, per far voltare il player nella direzione della camminata
		float xSpeed = 0;

        //Questi due if servono a settare delle variabili oltre che al movimento anche al modo in cui vengono disegnati gli sprite
        //Variabili che poi vengono utilizzata funzione draw come addendi o moltiplicatori per flipare le immagini e riposizionarle sull'asse giusto 
		if (left){
			xSpeed -= walkSpeed;
            flipX = hitBoxWidth - 60;
            flipW = -1;
        }
        
        if (right){
			xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }

		if (!inAir){
			if (!isEntityOnFloor(hitbox, levelData)){
				inAir = true;
            }
        }


		if (inAir) {
			if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
				hitbox.y += airSpeed;
				airSpeed += GRAVITY;
				updateXPos(xSpeed);
			} else {
                
				hitbox.y = getEntityYPosFloorRoofRelative(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else updateXPos(xSpeed);
        
		moving = true;
	}



    private void jump() {

        if (inAir) return;
        
        inAir = true;
        airSpeed = jumpSpeed;
    }



    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }



    private void updateXPos(float xSpeed){

        if (canMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = getEntityXPosNextWall(hitbox, xSpeed);
        }
    }



    //Questo metodo ci serve a resettare tutte le caratteristiche del giocatore se ne si trova il bisogno
    public void resetAll() {

        resetMovement();
        inAir = true;
        attacking = false;
        moving = false;
        state = IDLE;
        currentHealth = maxHealth;

        //Resetta la posizione del personaggio nelle variabili x ed y memorizate e mai usate
        hitbox.x = x;
        hitbox.y = y;

        if (isEntityOnFloor(hitbox, levelData)) {
            inAir = true;
        }

    }

    //Questa funzione invece fa il load dei frame di una animazione e li carica in un buffer di immagini
    //La funzione precedente fa uso di 'img', ovvero l'intera immagine che viene importata nel programma come un 
    //file stream gtramite questa funzione
    private void loadAnimations() {

        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[10][10];

            for(int j=0; j< animations.length ; j++){
                for(int i=0; i<animations[j].length; i++){
                    animations[j][i] = img.getSubimage(i*128, j*128, 128, 128);

                }
            }

        statusBarImg = LoadSave.getSpriteAtlas(LoadSave.HEALT_POWER_BAR);

    }

    public void loadLevelData(int [][] levelData){
        this.levelData = levelData;
    }
    
    //Funzione per settare il movimento a 0 quando viene chiamata
    public void resetMovement() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    //Funzioni get e set per ottenere e settare lo stato attuale degli attributi del player
    public void setSpawnPoint(Point spawn){
        this.x = spawn.x;
        this.y = spawn.y;
        hitbox.x = x;
        hitbox.y = y;
    }

    //Questa funzione fa morire il player
    public void die() {
        currentHealth = 0;
        jump = false;
    }

    public boolean getLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean getDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump){
        this.jump = jump;
    }

    public void setAttck(boolean attacking){
        this.attacking = attacking;
    }

    public int getDamage(){
        return damage;
    }

    public int getPlayerTileY(){
        return tyleY;
    }

    public void changePower(int bluePotionValue) {
        System.out.println("Added some Power by piking up the blue potion");
    }

    

    

}
