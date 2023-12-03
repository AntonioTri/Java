package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.PlayerConstants;
import static Progetto_prog_3.utils.Constants.PlayerConstants.*;
import static Progetto_prog_3.utils.HelpMetods.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import Progetto_prog_3.Game;
import Progetto_prog_3.utils.LoadSave;

public class Player extends Entity{

    //Variabili per la gestione dei frame
    private int aniTick, aniIndex, aniSpeed = 15;

    //Variabile per definire l'azione del player
    private int playerAction = JUMPING_DOWN;
    private boolean left, right, up, down, jump;
    private float playerSpeed = Game.SCALE;
    private boolean moving = false, attacking = false;

    //Variabili per la memorizzazione di frame
    private BufferedImage[][] animations;
    private int[][] levelData;

    //Variabili per le hitbox
    private float XOffset = 11 * Game.SCALE;
    private float YOffset = 25 * Game.SCALE;

    //Variabile per la gravita
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;

    //Variabili per il salto
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    

    //Costruttore richiamante la classe estesa
    public Player(int x, int y, int width, int height){
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 25 * Game.SCALE, 37 * Game.SCALE);
    }

    //funzione per fare l'update delle caratterisctiche del personaggio
    public void update(){

        updatePosition();
        updateAnimationTick();
        setAnimation();
    
    }
    
    //Dato che il programma viene refreshato 120 volte al secondo dato il game loop, aniIndex verrà modificato 
    //mano mano che avanzano i tick di gioco e verra' quindi mostrata una immagine differente ogni 40 tick
    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - XOffset), (int)(hitbox.y - YOffset), hitBoxWidth, hitBoxHeight, null);
        drawHitbox(g);
        
    }

    //Questa funzione fa avanzare il frame di animazione del personaggio ogni 40 tick del programma
    //Se l'indice diventa magiore del numero di frame viene ripristinato a 0 e si riparte da capo
    private void updateAnimationTick() {

        aniTick++;
        if (aniTick >= aniSpeed) {

            aniTick = 0;
            aniIndex++;

            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    //Qui viene settata l'animazione in base all'evento di gioco
    private void setAnimation() {

        int startAnimation = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if (inAir) {
            if (airSpeed<0) {
                playerAction = JUMPING_UP;
            } else {
                playerAction = JUMPING_DOWN;
            }
        }

        if (attacking) {
            playerAction = ATTACK2;
        }

        /*Se la animazione di arrivo e' diversa dalla animazione di fine funzione
            allora si e' creato un cambiamento di stato e vengono resettati i valori
            di scelta fotogramma e di tick di animazione per permettere alla animazione
            di incominciare dall'inizio e di non fare glitch strani
        */
        if (startAnimation != playerAction) {
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

		if (jump)
			jump();
		if (!left && !right && !inAir)
			return;

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;

		if (!inAir){
			if (!isEntityOnFloor(hitbox, levelData)){
				inAir = true;
            }
        }


		if (inAir) {
			if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
                //Qui c'e un bug strano, il personaggio viene teletrasportato ad una altezza di un tile size in piu'
				hitbox.y = getEntityYPosFloorRoofRelative(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
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


    //Questa funzione invece fa il load dei frame di una animazione e li carica in un buffer di immagini
    //La funzione precedente fa uso di 'img', ovvero l'intera immagine che viene importata nel programma come un 
    //file stream gtramite questa funzione
    private void loadAnimations() {

        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[10][8];

            for(int j=0; j< animations.length ; j++){
                for(int i=0; i<animations[j].length; i++){
                    animations[j][i] = img.getSubimage(i*128, j*128, 128, 128);

                }
            }

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

    //Funzioni get e set per ottenere e settare lo stato attuale dei movimenti
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

    

}
