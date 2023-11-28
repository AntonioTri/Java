package Progetto_prog_3;
import Progetto_prog_3.Inputs.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Progetto_prog_3.utils.Constants.PlayerConstants.*;
import static Progetto_prog_3.utils.Constants.Directions.*;

public class GamePanel extends JPanel {
    
    //Variabili di ambiente
    private MouseInputs mouseInputs;
    private int deltaX = 100, deltaY = 100;

    //Variabili per la memorizzazione di frame
    private BufferedImage img;
    private BufferedImage[][] animations;

    //Variabili per la gestione dei frame
    private int aniTick, aniIndex, aniSpeed = 15;

    //Variabile per definire l'azione del player
    private int playerAction = WALKING;
    private int playerDir = -1;
    private boolean moving = false;
    
    

    //Costruttore che aggiunge alla creazione un mouseListener per osservare
    //i cambiamenti del mouse,un keyboardListener per ascoltare i tasti premuti dalla tastiera
    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        importimage();
        loadAnimations();

    }

    public void setDirection(int direction){

        this.playerDir = direction;
        moving = true;

    }

    public void setMoving(boolean moving){
        this.moving = moving;
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

    //Questa funzione invece fa il load dei frame di una animazione e li carica in un buffer di immagini
    private void loadAnimations() {

        animations = new BufferedImage[10][8];

        for(int j=0; j< animations.length ; j++){
            for(int i=0; i<animations[j].length; i++){
                animations[j][i] = img.getSubimage(i*128, j*122, 125, 125);

            }
        }
    }

    //La funzione precedente fa uso di 'img', ovvero l'intera immagine che viene importata nel programma come un 
    //file stream gtramite questa funzione 
    private void importimage() {

        InputStream is = getClass().getResourceAsStream("/Progetto_prog_3/res/Animations.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Mammt annur!!!");
            e.printStackTrace();
        }
    
    }

    //Questa invece serve solo a settare la grandezza del pannello di gioco
    private void setPanelSize() {

        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    private void setAnimation() {

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

    }

    private void updatePosition() {

        if (moving) {
            switch (playerDir) {

                case LEFT:
                    deltaX -= 5;
                    break;

                case RIGHT:
                    deltaX +=5;
                    break;

                case DOWN:
                    deltaY +=5;
                    break;

                case UP:
                    deltaY -=5;
                    break;
        
            }
        }
    }



    

    /* ATTENZIONE! LA FUNZIONE REPAINT SERVE AD AGGIORNARE QUELLO CHE VEDIAMO A SCHERMO */

    //Qui invece disegnamo il rettangolo e gli elementi di gioco
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        setAnimation();
        updatePosition();

        updateAnimationTick();
        //Dato che il programma viene refreshato 120 volte al secondo dato il game loop, aniIndex verrà modificato 
        //mano mano che avanzano i tick di gioco e verra' quindi mostrata una immagine differente ogni 40 tick
        g.drawImage(animations[playerAction][aniIndex], deltaX, deltaY, null);
        g.drawRect(deltaX, deltaY, WIDTH, HEIGHT);



    }

    

    

    

    //FUNZIONI E MOTI VARI CHE POSSONO TORNARE UTILI IN QUALCHE MODO

    /*
     * Questo metodo sottostante invece ci permette di dare movimento 
     * proprio ad un qualche oggetto, in questo caso, dando deltax e deltax come 
     * argomento di ingresso alla posiizone del rettangolo ( vedi sopra ) cambia la
     * la posizione del rettangolo in tempo reale
     * 
     * 
     * private void updateRectangle() {
        deltaX += XDir;
        if (deltaX > 400 || deltaX < 0) {
            XDir *= -1;
        }

        deltaY += YDir;
        if (deltaY > 400 || deltaY < 0) {
            YDir *= -1;
        }
    }
     * 
     * Questo metodo viene richiamato all'interno del keyListener per modificare
       La posizione del rettangolo
       public void changeDeltaX(int value){
        this.deltaX += value;
      }

     Questo metodo viene richiamato all'interno del keyListener per modificare
     La posizione del rettangolo
     public void changeDeltaY(int value){
        this.deltaY += value;
     }

     Questo metodo invece all'interno del mouseListener per settare la posizione
     corrente dell'ovale uguale a quella del mouse
     public void updatePosition(int x, int y){
         this.deltaX = x;
        this.deltaY = y;
     }
     * 
     * 
     * 
     * 
     * Queste righe di codice sottostanti creano un cerchio che si muove 
        Autonomamente nello schermo e che urta nei bordi e gli fa cambiare direzione
        g.fillOval(deltaX, deltaY, 100, 100);
        updateRectangle();


        g.drawImage(null, x, y, null)
     * 
     * 
     * 
     * 
     * 
     * Questa  variabile permette di gestire la direzione in caso di movimento atonomo
        e di invertire questa se l'elemento non deve proseguire oltre, per farlo tornare indietro
        private int XDir = 1, YDir = 1;

     */
    
}
