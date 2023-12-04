package Lezione04_12_23.Decorator;

public class SportsCar extends CarDecorator {
    public SportsCar(Car c) {
        super(c);
    }
    
    @Override
    public void assemble(){
        car.assemble();;
        System.out.println("Adding new Sports feature");
    }
}
