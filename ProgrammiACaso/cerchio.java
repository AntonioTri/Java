package ProgrammiACaso;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class cerchio extends JFrame {

    public cerchio() {
        setTitle("Disegno di un cerchio");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // Chiamato automaticamente quando la finestra viene disegnata o ridisegnata
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // Definisci le coordinate del rettangolo in cui il cerchio sarà inscritto
        double x = 50;
        double y = 50;
        double larghezza = 100;
        double altezza = 100;

        // Crea un oggetto Ellipse2D che rappresenta un cerchio all'interno del rettangolo
        Ellipse2D cerchio = new Ellipse2D.Double(x, y, larghezza, altezza);

        // Disegna il cerchio
        g2d.draw(cerchio);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new cerchio();
        });
    }
}
