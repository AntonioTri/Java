package DesignPatterns.Decorator;

public class SportCar extends CarDecorator {
    
    public SportCar(Car c) {
        super(c);
    }

    @Override
    public void assemble(){
        car.assemble();
        System.out.print(" Adding features of Sports Car.");
    }

}
