package DesignPatterns.FlyheightPattern;

public class SoldierImpl implements Soldier {

    @Override
    public void moveSoldier(int previousLocationX, int previousLocationY, int newLocationX, int newLocationY) {
        System.out.println("Muovo il soldato nella nuova posizione X: " + newLocationX +", Y:" + newLocationY);
    }
    
}
