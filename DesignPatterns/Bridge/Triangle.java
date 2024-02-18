package DesignPatterns.Bridge;

public class Triangle extends Shape {

    public Triangle(Color c) {
        super(c);
    }

    @Override
    public void applyColor() {
        System.out.println("Printing the Triangle with color:");
        color.applyColor();
    }
    
}
