package DesignPatterns.AbstractFactory;

public class NvidiaGpu implements Gpu {

    @Override
    public void assemble() {
        System.out.println("Assemblo una GPU Nvidia.");
    }

    @Override
    public void use() {
        System.out.println("Stai usando una GPU Nvidia");
    }
    
}
