package Progetto_prog_3;
import java.awt.Graphics;

import Progetto_prog_3.entities.Player;

public class Game implements Runnable{

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private final int SET_FPS = 120;
    private final int SET_UPS = 120;

    private Player player;

    public Game(){

        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        StartGameLoop();

    }

    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Funzione per inizializzare le classi delle entita presenti
    private void initClasses() { player = new Player(200, 200); }
    //Funzione per updatare lo stato degli elementi inizializzati correnti
    private void update() { player.update(); }
    //Funzione per fare la paint degli elementi correnti
    public void render(Graphics g){ player.render(g); }
    //Funzione per gestire la perdita del focus dalla finestra di gioco
    public void windowFocusLost() { player.resetMovement(); }

    //Funzione per gestire i frame per secondo e gli update per secondo
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / SET_FPS;
        double timePerUpdate = 1000000000.0 / SET_UPS;
        
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            //Fps Counter
            
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer(){
        return player;

    }

	
    
}
