package Progetto_prog_3;
import java.awt.Graphics;

import Progetto_prog_3.GameStates.GameOptions;
import Progetto_prog_3.GameStates.GameState;
import Progetto_prog_3.GameStates.Menu;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.UI.AudioOptions;
import Progetto_prog_3.UI.GameOverOverlay;

public class Game implements Runnable{

    //Variabili di ambiente
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private final int SET_FPS = 120;
    private final int SET_UPS = 180;
    public int frame = 0;
    public int update = 0;
    

    //Variabili per la mappa
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = (TILES_SIZE * TILES_IN_WIDTH);
    public final static int GAME_HEIGHT = (TILES_SIZE * TILES_IN_HEIGHT);

    //
    private Playing playing;
    private Menu menu;
    private AudioOptions audioOptions;
    private GameOptions gameOptions;

    public Game(){

        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        StartGameLoop();

    }

    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Funzione per inizializzare le classi delle entita presenti
    private void initClasses() { 
        audioOptions = new AudioOptions();
        gameOptions = new GameOptions(this);
        menu = new Menu(this);
        playing = new Playing(this);
    }

    //Funzione per updatare lo stato degli elementi inizializzati correnti
    private void update() { 
        //Lo switch osserva il current game state ed eseguirà soltanto specifiche azioni, qusto ci permette si eseguire 
        //Specifici stati come il menù di pausa, di inizio oppure il gioco stesso
        switch (GameState.state) {
            case MENU:
                menu.update();
                break;

            case PLAYING:
                playing.update();
                break;

            case OPTION:
                gameOptions.update();
                break;

            case QUIT:
                System.exit(0);
                break;

            default:
                //Esce dal programma, lo termina
                System.exit(0);
                break;
        }
    }
    //Funzione per fare la paint degli elementi correnti
    public void render(Graphics g){ 
        //Lo switch osserva il current game state ed eseguirà soltanto specifiche azioni, qusto ci permette si eseguire 
        //Specifici stati come il menù di pausa, di inizio oppure il gioco stesso
        switch (GameState.state) {
            case MENU:
                menu.draw(g);
                break;
            
            case PLAYING:
                playing.draw(g);
                break;

            case OPTION:
                gameOptions.draw(g);
                break;

            default:
                break;
        }
    }
    
    //Funzione per gestire la perdita del focus dalla finestra di gioco
    public void windowFocusLost() { 
        if (GameState.state == GameState.PLAYING) {
            playing.getPlayer().resetMovement();
        }
    }

    //Funzione per gestire i frame per secondo e gli update per secondo
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / SET_FPS;
        double timePerUpdate = 1000000000.0 / SET_UPS;

        int frames = 0;
        int updates = 0;
        
        long previousTime = System.nanoTime();
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
                frame = frames;
                update = updates;
                frames = 0;
                updates = 0;
            }
        }
    }

    //Qusto metodo ritorna una stringa formattata che contiene gli FPS e gli UPS
    public String getFpsUps(){
        return "FPS: " + frame + " | UPS: " + update;
    }

    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }

    public GameOptions getGameOptions(){
        return gameOptions;
    }

    public AudioOptions geAudioOptions(){
        return audioOptions;
    }
}
