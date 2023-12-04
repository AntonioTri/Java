package Lezione04_12_23.Decorator;

public class Decorator {

    public static void main(String[] args){

        Car sportCar = new SportsCar(new BasicCar());

        sportCar.assemble();

        Car luxuricar = new SportsCar(new LuxuryCar(new BasicCar()));

        luxuricar.assemble();
    }

}

/*
 * Tecnicamente queso già lo stiamo usando nel menùù di pausa, questo design pattern parte da una interfaccia e la decora
 * aggiungendo pezzi e funzionalità, come ad esempio il menù di pausa
 * 
 */
