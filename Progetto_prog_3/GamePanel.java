package Progetto_prog_3;
import Progetto_prog_3.Inputs.*;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    
    //Variabili di ambiente
    private MouseInputs mouseInputs;
    private int deltaX = 100, deltaY = 100;
    private int XDir = 1, YDir = 1;
    
    private int frames = 0;
    private long lastCheck = 0;

    //Costruttore che aggiunge alla creazione un mouseListener per osservare
    //i cambiamenti del mouse,un keyboardListener per ascoltare i tasti premuti dalla tastiera
    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    //Questo metodo viene richiamato all'interno del keyListener per modificare
    //La posizione del rettangolo
    public void changeDeltaX(int value){
        this.deltaX += value;
    }

    //Questo metodo viene richiamato all'interno del keyListener per modificare
    //La posizione del rettangolo
    public void changeDeltaY(int value){
        this.deltaY += value;
    }

    //Questo metodo invece all'interno del mouseListener per settare la posizione
    //corrente dell'ovale uguale a quella del mouse
    public void updatePosition(int x, int y){
        this.deltaX = x;
        this.deltaY = y;
    }

    /* ATTENZIONE! LA FUNZIONE REPAINT SERVE AD AGGIORNARE QUELLO CHE VEDIAMO A SCHERMO */

    //Qui invece disegnamo il rettangolo e gli elementi di gioco
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.fillOval(deltaX, deltaY, 100, 100);
        updateRectangle();

        //Fps Counter
        frames++;
        if(System.currentTimeMillis() - lastCheck > 1000){
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }

        //E' come una chiamata ricorsiva, richiama paintComponent();

    }

    private void updateRectangle() {
        deltaX += XDir;
        if (deltaX > 400 || deltaX < 0) {
            XDir *= -1;
        }

        deltaY += YDir;
        if (deltaY > 400 || deltaY < 0) {
            YDir *= -1;
        }
    }

}
