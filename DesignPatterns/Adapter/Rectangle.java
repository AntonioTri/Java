package DesignPatterns.Adapter;

public class Rectangle implements Shape{
    
    LegacyRectangle rectangle = new LegacyRectangle();

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        rectangle.draw(x1, y1, x2, y2);
    }
    
}
