package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.Directions.*;
import static Progetto_prog_3.utils.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{

    //Variabili per la gestione dei frame
    private int aniTick, aniIndex, aniSpeed = 15;
    //Variabile per definire l'azione del player
    private int playerAction = TROW_SWORD;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;
    private boolean moving = false, attacking = false;
    //Variabili per la memorizzazione di frame
    private BufferedImage[][] animations;
    

    //Costruttore richiamante la classe estesa
    public Player(int x, int y){
        super(x, y);
        loadAnimations();
    }

    //funzione per fare l'update delle caratterisctiche del personaggio
    public void update(){
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }
    
    //Dato che il programma viene refreshato 120 volte al secondo dato il game loop, aniIndex verrà modificato 
    //mano mano che avanzano i tick di gioco e verra' quindi mostrata una immagine differente ogni 40 tick
    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], x, y, null);
        
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
            playerAction = WALKING;
        } else {
            playerAction = IDLE;
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

        //Movimenti destra e sinistra concorrenti non permessi
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        //Movimenti sopra e sotto concorrenti non permessi
        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
        
    }


    //Questa funzione invece fa il load dei frame di una animazione e li carica in un buffer di immagini
    //La funzione precedente fa uso di 'img', ovvero l'intera immagine che viene importata nel programma come un 
    //file stream gtramite questa funzione
    private void loadAnimations() {

        InputStream is = getClass().getResourceAsStream("/Progetto_prog_3/res/Animations.png");

        try {

            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[10][8];

            for(int j=0; j< animations.length ; j++){
                for(int i=0; i<animations[j].length; i++){
                    animations[j][i] = img.getSubimage(i*128, j*122, 125, 125);

                }
            }

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

    public void setAttck(boolean attacking){
        this.attacking = attacking;
    }

    

}
