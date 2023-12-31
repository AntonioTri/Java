package DesignPatterns.StrategyPattern;

public class StrategyPatternTest {
    
    /*
     * In sintesi: se c'è un metodo che diverse classi devono usare, classi che estendono una stessa classe madre ...
     * 
     * 1) Si può dare a questa classe madre una istanza di una interfaccia che contiene quel metodo da implementare
     *     ed un metodo set che permette di cambiare l'implementazione di questa sua interfaccia a piacimento
     * 2) Si da alla stessa un medodo che esegue il metodo ancora da implementare
     * 3) Si creano classi a quante ce ne sono bisogno che implementino i metodi dell'interfaccia e non solo
     * 4) Successivamente alle classi che estendono la classe madre si può assegnare tramite il metodo set, all'istanza dell'interfaccia
     *     della classe madre, una sua classe che la implementa sfruttando il polimorfismo et voilà
     * 5) Ora queste classi hanno una implementazione del metodo unica e che può cambiare anche in modo dinamico
     * 
     * Di sotto un esempio concreto
     * 
     */


    public static void main(String [] args){

        //Creazione dele istanze polimorfe
        //Entrmbe hanno nel costruttore il metodo set che implementa in modo dinamico l'istanza della interfaccia
        Animal dog = new Dog();
        Animal bird = new Bird();

        //Esecuzione dell'esempio
        System.out.println("Dog flyType: " + dog.tryToFly());
        System.out.println("Bird flyType: " + bird.tryToFly());

        System.out.println("Changing dog flytype in runTime");
        dog.setFlyType(new CanFly());

        System.out.println("Dog flyType: " + dog.tryToFly());

    }


}
