package Progetto_prog_3.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.util.Random;

import Progetto_prog_3.Game;
import Progetto_prog_3.UI.GameOverOverlay;
import Progetto_prog_3.UI.PauseOverlay;
import Progetto_prog_3.entities.EnemyManager;
import Progetto_prog_3.entities.Player;
import Progetto_prog_3.levels.LevelManager;
import Progetto_prog_3.utils.LoadSave;
import static Progetto_prog_3.utils.Constants.Environment.*;

public class Playing extends State implements StateMethods{
    
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private GameOverOverlay gameOverOverlay;

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
    private BufferedImage backgroundImage, bigClouds, smallClouds;
    private int[] smallCloudPos;
    private Random random = new Random();

    //Variabile pre identificare il Game Over
    private boolean gameOver = false;


    public Playing(Game game) {
        super(game);
        initClasses();
    }
    
    //Funzione per inizializzare le classi delle entita presenti
    private void initClasses() { 

        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);

        player = new Player(200, 400, (int) (64*Game.SCALE), (int)(64*Game.SCALE), this); 
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        
        loadBackground();

    }

    private void loadBackground(){

        //Vengono caricate le immagini dlle nuvole
        backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMAGE);
        bigClouds = LoadSave.getSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallClouds = LoadSave.getSpriteAtlas(LoadSave.SMALL_CLOUDS);

        smallCloudPos = new int[8];
        for (int i = 0; i < smallCloudPos.length; i++) {
            smallCloudPos[i] = (int)(random.nextInt((int)( 100 * Game.SCALE)) + (90 * Game.SCALE));
        }

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

        if (!gameOver && !paused ) {
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
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
        g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        //Vengono agiunte le nuvole
        drowBigClouds(g);
        drowSmallClouds(g);

        //Durante il draw, vengono aggiunti gli offset per disegnare la parte di mappa corretta
        enemyManager.draw(g, xLevelOffset);
        levelManager.draw(g, xLevelOffset);
        player.render(g, xLevelOffset);

        if (paused) {
            //Se il gioco viene messo in pausa, viene disegnato un rettangolo tra il game ed il menù di pausa
            //Per dare un effetto piacevole alla vista
            g.setColor(new Color(0,0,0, 140));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            //Si disegna il menù di pausa sopra al rettangolo opaco precedente
            pauseOverlay.draw(g);
        } else if (gameOver) {
            gameOverOverlay.draw(g);
        }

    }

    private void drowSmallClouds(Graphics g) {
        for (int i = 0; i < smallCloudPos.length; i++) {
            g.drawImage(smallClouds, SMALL_CLOUD_WIDTH * 4 * i -(int) ( xLevelOffset * 0.7), smallCloudPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
        }

    }

    private void drowBigClouds(Graphics g) {
        for (int i = 0; i < 3; i++) {
            g.drawImage(bigClouds, i*BIG_CLOUD_WIDTH -(int) ( xLevelOffset * 0.3) , (int)(204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
        }

    }

    public void resetAll(){
        //TODO: reset player, enemyes, level things excetera
        gameOver = false;
        paused = false;
        player.resetAll();
        enemyManager.resetAllEnemyes();
    }


    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        
    }



    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOver) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                player.setAttck(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver && paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver && paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver && paused) {
            pauseOverlay.mouseMoved(e);
        }
    }

    public void mouseDragged(MouseEvent e){
        if (!gameOver && paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (gameOver) {
            gameOverOverlay.keyPressed(e);
        } else {

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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if (!gameOver) {

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

   

    
}
