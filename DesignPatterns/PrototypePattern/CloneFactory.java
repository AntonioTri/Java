package DesignPatterns.PrototypePattern;

public class CloneFactory {
    
    public AnimalInterface getClone(AnimalInterface animalSample){

        return animalSample.makeCopy();

    };
}
