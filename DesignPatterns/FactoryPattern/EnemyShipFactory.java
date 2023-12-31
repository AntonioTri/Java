package DesignPatterns.FactoryPattern;

public class EnemyShipFactory {
    
    public AbstractEnemyShip makeEnemyship(String newShiptype){

        switch (newShiptype) {

            case "U": return new UFOEnemyShip();
            case "B": return new BigUfoenemyShip();
            case "F": return new FastUfoEnemyShip();
            
            default: return null;
        }

    }

}
