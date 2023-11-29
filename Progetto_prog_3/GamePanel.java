package Progetto_prog_3;

import Progetto_prog_3.Inputs.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import static Progetto_prog_3.Game.GAME_HEIGHT;
import static Progetto_prog_3.Game.GAME_WIDTH;


public class GamePanel extends JPanel {
    
    //Variabili di ambiente
    private MouseInputs mouseInputs;

    private Game game;

    //Costruttore che aggiunge alla creazione un mouseListener per osservare
    //i cambiamenti del mouse,un keyboardListener per ascoltare i tasti premuti dalla tastiera
    public GamePanel(Game game){

        this.game = game;

        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);


    }


    //Questa invece serve solo a settare la grandezza del pannello di gioco
    private void setPanelSize() {

        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }
    
    public void updateGame(){

        

    };

    /* ATTENZIONE! LA FUNZIONE REPAINT SERVE AD AGGIORNARE QUELLO CHE VEDIAMO A SCHERMO */

    //Qui invece disegnamo il rettangolo e gli elementi di gioco
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        game.render(g);

    }

    public Game getGame(){
        return game;
    }

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