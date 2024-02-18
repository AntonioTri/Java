package DesignPatterns.Decorator;

public class DecoratorPatternTest {
    public static void main(String[] args) {

        Car sportsCar = new SportCar(new BasicCar());
        sportsCar.assemble();

        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();

    }

}
