package Lezione13_11_23;

public class boh {

    public static void main (String[] args ){

        ShapeFactory circleFactory = new CircleFactory();
        Shape circle = circleFactory.createShape();
        circle.draw(); // Questo disegnerà un cerchio

        ShapeFactory squareFactory = new SquareFactory();
        Shape square = squareFactory.createShape();
        square.draw(); // Questo disegnerà un quadrato



    }
    
}
