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
import Progetto_prog_3.GameStates.GameState;
import Progetto_prog_3.utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){

        this.gamePanel = gamePanel;

    };


    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state){
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state){
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
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
