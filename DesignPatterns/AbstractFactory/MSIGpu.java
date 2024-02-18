package DesignPatterns.AbstractFactory;

public class MSIGpu implements Gpu {

    @Override
    public void assemble() {
        System.out.println("Assemblo una GPU della MSI.");
    }

    @Override
    public void use() {
        System.out.println("Stai usando una GPU MSI");
    }
    
}
