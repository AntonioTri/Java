package DesignPatterns.Adapter;

public class Line implements Shape{

    LegacyLine line = new LegacyLine();

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        line.draw(x1, y1, x2, y2);
    }

}
