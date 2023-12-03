package Progetto_prog_3.Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Progetto_prog_3.GamePanel;
import Progetto_prog_3.GameStates.GameState;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    //    gamePanel.updatePosition(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state){
            case MENU:
            gamePanel.getGame().getMenu().mouseClicked(e);
                break;
            case PLAYING:
            gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
   }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
