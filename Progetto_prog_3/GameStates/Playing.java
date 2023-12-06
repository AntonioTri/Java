package Progetto_prog_3.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Progetto_prog_3.Game;
import Progetto_prog_3.UI.PauseOverlay;
import Progetto_prog_3.entities.Player;
import Progetto_prog_3.levels.LevelManager;
import Progetto_prog_3.utils.LoadSave;

public class Playing extends State implements StateMethods{
    
    private Player player;
    private LevelManager levelManager;

    private boolean paused = false;
    private PauseOverlay pauseOverlay;

    //Queste variabili mi servono a gestire il movimento della telecamera nel mondo,
    //CAPTAZIONE WORK IN PROGRESS, ANCORA NON HO ANCORA CAPITO BENE
    private int xLevelOffset;
    //Queste due variabili definiscono il bordo dopo il quale la visuale viene regolata e spostata di conseguenza
    private int leftBorder = (int)(0.5 * Game.GAME_WIDTH);
    private int rightBorder = (int)(0.5 * Game.GAME_WIDTH);
    //Questa variabile tramite il level data, ci permete di accedere alla lunghezza del livello
    private int levelTileWide = LoadSave.getLevelData()[0].length;
    //Queste servono a definire entro quale limite non bisonga più spostare la telecamera
    private int maxTileOffset = levelTileWide - Game.TILES_IN_WIDTH;
    private int maxLevelOffsetX = maxTileOffset * Game.TILES_SIZE;
    //Immagini di baground
    private BufferedImage backgroundImage;
    
    public Playing(Game game) {
        super(game);
        initClasses();
        backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMAGE);
    }

    //Funzione per inizializzare le classi delle entita presenti
    private void initClasses() { 
        levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (64*Game.SCALE), (int)(64*Game.SCALE) ); 
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
    }

    public void unpauseGame(){
        paused = false;
    }


    public Player getPlayer(){
        return player;

    }

    public void windowFocusLost() { 
        player.resetMovement(); 
    }

    public void mouseDragged(MouseEvent e){
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    //Questa funzione serve a definire la distanza tra il player ed il bordo,
    //In caso avvengano alcune verita' vengono definiti i valori per spostare la videocamera
    private void checkCloseToBorder() {

        //Si estrae la posizione in x del player
        int playerX = (int) player.getHitbox().x;
        //Si calcola la differenza tra la posizione attuale e la variabile offset del livello
        int difference = playerX - xLevelOffset;

        //Se la differenza è maggiore del bordo di destra, al level offset viene aggiunta la differenza
        //più i lvalore del bordo destro/sinistro 
        if (difference > rightBorder) {
            xLevelOffset += difference - rightBorder;
        } else if(difference < leftBorder){
            xLevelOffset += difference - leftBorder;
        }

        //Se il valore invece supera la grandezza massima dei bordi del livello, l'offset viene
        //settato come la posizione del muro che sta provando ad atraversare così da nono mostrare 
        //una parte del livello vuota
        if (xLevelOffset > maxLevelOffsetX) {
            xLevelOffset = maxLevelOffsetX;
        } else if (xLevelOffset < 0) {
            xLevelOffset  = 0;
        }

    }

    @Override
    public void update() {

        if (!paused) {
            levelManager.update();
            player.update();
            checkCloseToBorder();
        } else {
            pauseOverlay.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        //Viene disegnato il background
        //g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        //Durante il draw, vengono aggiunti gli offset per disegnare la parte di mappa corretta
        levelManager.draw(g, xLevelOffset);
        player.render(g, xLevelOffset);

        if (paused) {
            //Se il gioco viene messo in pausa, viene disegnato un rettangolo tra il game ed il menù di pausa
            //Per dare un effetto piacevole alla vista
            g.setColor(new Color(0,0,0, 140));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            //Si disegna il menù di pausa sopra al rettangolo opaco precedente
            pauseOverlay.draw(g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //gamePanel.updatePosition(e.getX(), e.getY());
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttck(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
            default:
                break;
        }
    }
}
