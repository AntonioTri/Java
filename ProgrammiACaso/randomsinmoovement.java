package ProgrammiACaso;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class randomsinmoovement extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int NUM_POINTS = 5; // Numero di punti casuali per l'interpolazione

    private final JPanel panel;
    private final ArrayList<Point2D> points;

    public randomsinmoovement() {
        setTitle("Moving Circle");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            double time = 0;
            double yPrev = 0;
            Random rand = new Random();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Genera e interpola i punti casuali lungo l'asse y
                points.clear();
                for (int i = 0; i < NUM_POINTS; i++) {
                    double x = (double) getWidth() / (NUM_POINTS - 1) * i;
                    double y = rand.nextDouble() * getHeight();
                    points.add(new Point2D.Double(x, y));
                }

                // Disegna il cerchio rosso sulla finestra
                g.setColor(Color.RED);
                int circleX = getWidth() / 2;
                double circleY = interpolateY(getWidth() / 2, points); // Interpola l'altezza del cerchio
                int circleDiameter = 20;
                g.fillOval(circleX - circleDiameter / 2, (int) circleY - circleDiameter / 2, circleDiameter, circleDiameter);
            }
        };

        points = new ArrayList<>();
        add(panel);
        setVisible(true);

        // Aggiorna l'interfaccia grafica ogni 10 millisecondi
        new Timer(500, e -> panel.repaint()).start();
    }

    // Interpolazione di Hermite
    private double interpolateY(double x, ArrayList<Point2D> points) {
        int numPoints = points.size();
        for (int i = 0; i < numPoints - 1; i++) {
            double x0 = points.get(i).getX();
            double x1 = points.get(i + 1).getX();
            if (x >= x0 && x <= x1) {
                double y0 = points.get(i).getY();
                double y1 = points.get(i + 1).getY();
                double t = (x - x0) / (x1 - x0);
                double m0 = (i == 0) ? 0 : (y1 - points.get(i - 1).getY()) / (x1 - points.get(i - 1).getX());
                double m1 = (i == numPoints - 2) ? 0 : (points.get(i + 2).getY() - y0) / (points.get(i + 2).getX() - x0);
                double h00 = (1 + 2 * t) * Math.pow(1 - t, 2);
                double h10 = t * Math.pow(1 - t, 2);
                double h01 = t * t * (3 - 2 * t);
                double h11 = t * t * (t - 1);
                return h00 * y0 + h10 * (x1 - x0) * m0 + h01 * y1 + h11 * (x1 - x0) * m1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(randomsinmoovement::new);
    }
}
