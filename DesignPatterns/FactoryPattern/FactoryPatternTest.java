package DesignPatterns.FactoryPattern;
import java.util.Scanner;

public class FactoryPatternTest {

    public static void main(String[] args){

        AbstractEnemyShip ufoShip = null;
        EnemyShipFactory enemyFactory = new EnemyShipFactory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("What ship do u want? ( U / F / B )");

        if (scanner.hasNextLine()) {
            
            String userInput = scanner.nextLine();
            ufoShip = enemyFactory.makeEnemyship(userInput);

        }

        scanner.close();
        if(ufoShip != null) doStuffEnemy(ufoShip);

    }

    private static void doStuffEnemy(AbstractEnemyShip anEnemyship) {

        anEnemyship.displayEnemyShip();
        anEnemyship.followingHero();

    }
    
}
