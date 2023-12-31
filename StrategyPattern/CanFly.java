package StrategyPattern;

//La classe can Fly implementa l'interfaccia in modo che possa volare
public class CanFly implements FlyInterface {

    @Override
    public String fly() {
        return "This animal is flying";
    }
    
}
