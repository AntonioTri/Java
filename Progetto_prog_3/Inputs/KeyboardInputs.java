package Progetto_prog_3.Inputs;

import static Progetto_prog_3.utils.Constants.Directions.DOWN;
import static Progetto_prog_3.utils.Constants.Directions.LEFT;
import static Progetto_prog_3.utils.Constants.Directions.RIGHT;
import static Progetto_prog_3.utils.Constants.Directions.UP;
import static Progetto_prog_3.utils.Constants.PlayerConstants.getSpriteAmount;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.xml.namespace.QName;

import Progetto_prog_3.GamePanel;
import Progetto_prog_3.utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){

        this.gamePanel = gamePanel;

    };


    @Override
    public void keyPressed(KeyEvent e) {

         switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().setJump(true);
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().setJump(false);
                break;
            default:
                break;
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
    
        switch (e.getKeyCode()) {
            
            case KeyEvent.VK_W:
                System.out.println("Pressing W");
                break;
            case KeyEvent.VK_A:
                System.out.println("Pressing A");
                break;
            case KeyEvent.VK_S:
                System.out.println("Pressing S");
                break;
            case KeyEvent.VK_D:
                System.out.println("Pressing D");
                break;
                

        }

    }
    
}
