package DesignPatterns.PrototypePattern;

public class PrototypeTest {
    
    public static void main(String[] args) {

        CloneFactory animalMaker = new CloneFactory();

        Sheep sally = new Sheep();

        Sheep clonedSheep = (Sheep) animalMaker.getClone(sally);

        System.out.println(sally);
        System.out.println(clonedSheep);
        System.out.println("Sally has code: " + System.identityHashCode(System.identityHashCode(sally)));
        System.out.println("Cloned Sally has code: " + System.identityHashCode(System.identityHashCode(clonedSheep)));
    }
}
