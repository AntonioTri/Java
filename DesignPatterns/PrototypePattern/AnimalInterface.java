package DesignPatterns.PrototypePattern;

//Viene estesa a clonable così jaavaa sa che questa classe può essere clonata
public interface AnimalInterface extends Cloneable{

    public AnimalInterface makeCopy();

}