package Progetto_prog_3;
import java.awt.Graphics;

import Progetto_prog_3.entities.Player;
import Progetto_prog_3.levels.LevelManager;

public class Game implements Runnable{

    //Variabili di ambiente
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private final int SET_FPS = 120;
    private final int SET_UPS = 180;
    private LevelManager levelManager;
    private Player player;

    //Variabili per la mappa
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = (TILES_SIZE * TILES_IN_WIDTH);
    public final static int GAME_HEIGHT = (TILES_SIZE * TILES_IN_HEIGHT);

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
    private void initClasses() { 
        levelManager = new LevelManager(this);
        player = new Player(200, 400, (int) (64*SCALE), (int)(64*SCALE) ); 
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }
    //Funzione per updatare lo stato degli elementi inizializzati correnti
    private void update() { 
        player.update(); 
        levelManager.update();
    }
    //Funzione per fare la paint degli elementi correnti
    public void render(Graphics g){ 
        levelManager.draw(g);
        player.render(g); 
    }
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
