package DesignPatterns.StrategyPattern;

public class Bird extends Animal{
    
    public Bird(){
        //Usando il polimorfismo si permette a questa interfaccia
        // di assumere la forma di un animale che vola
        setFlyType(new CanFly());
    }

}
