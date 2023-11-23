package Progetto_prog_3.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Progetto_prog_3.GamePanel;

public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){

        this.gamePanel = gamePanel;

    };


    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
                System.out.println("Pressing W");
                gamePanel.changeDeltaY(-5);
                break;
            case KeyEvent.VK_A:
                System.out.println("Pressing A");
                gamePanel.changeDeltaX(-5);
                break;
            case KeyEvent.VK_S:
                System.out.println("Pressing S");
                gamePanel.changeDeltaY(5);
                break;
            case KeyEvent.VK_D:
                System.out.println("Pressing D");
                gamePanel.changeDeltaX(5);
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
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
