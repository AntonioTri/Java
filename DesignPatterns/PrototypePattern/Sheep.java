package DesignPatterns.PrototypePattern;

public class Sheep implements AnimalInterface {

    public Sheep(){
        System.out.println("Sheep is made");
    }


    @Override
    public AnimalInterface makeCopy() {
        System.out.println("Ship is beeing clonated");

        Sheep newSheep = null;

        try {
            newSheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clonig not supported");
        }

        return newSheep;

    }

    public String toString(){
        return "Dolly is my Hero, Beeeee";
    }
    
}
