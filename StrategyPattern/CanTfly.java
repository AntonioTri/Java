package StrategyPattern;

//La classe can't Fly implementa l'interfaccia in modo che non possa volare
public class CanTfly implements FlyInterface {

    @Override
    public String fly() {
        return "This animal can't fly";
    }
    
}
