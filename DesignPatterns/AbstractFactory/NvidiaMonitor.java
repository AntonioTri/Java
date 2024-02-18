package DesignPatterns.AbstractFactory;

public class NvidiaMonitor implements Monitor {

    @Override
    public void assemble() {
        System.out.println("Assemblo un monitor Nvidia");
    }

    @Override
    public void use() {
        System.out.println("Stai usando un monitor Nvidia");
    }
    
}
