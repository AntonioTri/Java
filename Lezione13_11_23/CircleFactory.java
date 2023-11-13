package Lezione13_11_23;

// Implementazione Factory per creare cerchi
public class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Circle();
    }
}