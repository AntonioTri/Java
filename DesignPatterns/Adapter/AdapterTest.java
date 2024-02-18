package DesignPatterns.Adapter;

public class AdapterTest {

    public static void main(String[] args) {

        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;

        Shape line = new Line();
        Shape rectangle = new Rectangle();

        line.draw(x1, y1, x2, y2);
        rectangle.draw(x1, y1, x2, y2);

    }

}
