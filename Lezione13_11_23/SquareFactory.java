package Lezione13_11_23;



// Implementazione Factory per creare quadrati
public class SquareFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Square();
    }
}

