package Progetto_prog_3.GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Progetto_prog_3.Game;
import Progetto_prog_3.UI.PauseOverlay;
import Progetto_prog_3.entities.Player;
import Progetto_prog_3.levels.LevelManager;

public class Playing extends State implements StateMethods{
    
    private Player player;
    private LevelManager levelManager;

    private boolean paused = true;
    private PauseOverlay pauseOverlay;
    
    public Playing(Game game) {
        super(game);
        initClasses();
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

    @Override
    public void update() {

        if (!paused) {
            levelManager.update();
            player.update();
        } else {
            pauseOverlay.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);

        if (paused) {
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
