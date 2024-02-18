package DesignPatterns.AbstractFactory;

public class MSIMonitor implements Monitor {

    @Override
    public void assemble() {
        System.out.println("Assembloo un monitor MSI.");
    }

    @Override
    public void use() {
        System.out.println("Stai usando un monitor MSI");
    }
    
}
