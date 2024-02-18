package DesignPatterns.Bridge;

public class Pentagon extends Shape {

    public Pentagon(Color c) {
        super(c);
    }

    @Override
    public void applyColor() {
        System.out.println("Printing the Triangle with color:");
        color.applyColor();
    }
    
}
