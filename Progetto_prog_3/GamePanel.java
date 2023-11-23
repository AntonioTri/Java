package Progetto_prog_3;
import Progetto_prog_3.Inputs.*;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    
    private MouseInputs mouseInputs;
    private int deltaX = 100;
    private int deltaY = 100;

    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void changeDeltaX(int value){
        this.deltaX += value;
        repaint();
    }

    public void changeDeltaY(int value){
        this.deltaY += value;
        repaint();
    }

    public void updatePosition(int x, int y){
        this.deltaX = x;
        this.deltaY = y;
        repaint();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        //g.fillRect(100, 100, 200, 50);
        //g.setColor(Color.GREEN);
        g.fillOval(deltaX, deltaY, 100, 100);

    }

}
