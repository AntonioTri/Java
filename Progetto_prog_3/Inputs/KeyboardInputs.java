package Progetto_prog_3.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener{

    @Override
    public void keyPressed(KeyEvent e) {

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
