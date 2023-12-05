package Lezione04_12_23.Decorator;

public class LuxuryCar extends CarDecorator {

    public LuxuryCar(Car c) {
        super(c);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public void assemble(){
        car.assemble();;
        System.out.println("Adding new luxury feature");
    }

}
