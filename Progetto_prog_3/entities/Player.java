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


    private boolean moving = false;

    //Variabili per la memorizzazione di frame
    private BufferedImage[][] animations;
    
    //Costruttore richiamante la classe estesa
    public Player(int x, int y){
        super(x, y);
        loadAnimations();
    }

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
            }
        }
    }



    private void setAnimation() {

        if (moving) {
            playerAction = ATTACK2;
        } else {
            playerAction = IDLE;
        }

    }

    private void updatePosition() {

        
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

    

}
