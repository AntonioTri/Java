package ProgrammiACaso;
import javax.swing.*;
import java.awt.*;

public class BernoulliLemniscateMotion extends JFrame {
    private double t;

    public BernoulliLemniscateMotion() {
        t = 0;

        setTitle("Bernoulli Lemniscate Motion");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BernoulliPanel bernoulliPanel = new BernoulliPanel();
        add(bernoulliPanel);

        // Imposta un timer per aggiornare la posizione del punto
        Timer timer = new Timer(10, e -> {
            updatePosition();
            bernoulliPanel.repaint();
        });
        timer.start();
    }

    private void updatePosition() {
        // Aggiorna la posizione del punto lungo la lemniscata di Bernoulli
        t += 0.03;
        if (t > 360) {
            System.out.println("T ha raggiunto i 360 gradi");
        }
    }

    private class BernoulliPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawPoint(g);
        }

        private void drawPoint(Graphics g) {
            // Equazione esplicita della lemniscata di Bernoulli
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int scale = 50;  // Scala per regolare le dimensioni della lemniscata

            int x = (int) (centerX + scale * Math.sqrt(2) * Math.cos(t) / (1 + Math.pow(Math.sin(t), 2)));
            int y = (int) (centerY + scale * Math.sqrt(2) * Math.cos(t) * Math.sin(t) / (1 + Math.pow(Math.sin(t), 2)));

            g.setColor(Color.RED);
            g.fillOval(x, y, 10, 10); // Disegna un punto rosso di dimensione 10x10
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BernoulliLemniscateMotion motion = new BernoulliLemniscateMotion();
            motion.setVisible(true);
        });
    }
}
