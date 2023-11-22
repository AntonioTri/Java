package Progetto_prog_3;
import Progetto_prog_3.Inputs.*;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    
    public GamePanel(){

        addKeyListener(new KeyboardInputs());

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        //g.fillRect(100, 100, 200, 50);
        //g.setColor(Color.GREEN);
        g.fillOval(100, 100, 100, 100);

    }

}
