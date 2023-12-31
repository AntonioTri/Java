package StrategyPattern;

public class Dog extends Animal{
    
    public Dog(){
        //Usando il polimorfismo si permette a questa interfaccia
        // di assumere la forma di un animale che NON vola
        setFlyType(new CanTfly());
    }
}
