package DesignPatterns.AbstractFactory;

public class NvidiaFactoory extends AbstractCompany {

    @Override
    public Gpu assembleGpu() {
        Gpu NvidiaGpu = new NvidiaGpu();
        NvidiaGpu.assemble();
        return NvidiaGpu;
    }

    @Override
    public Monitor assembleMonitor() {
        Monitor NvidiaMonitor = new NvidiaMonitor();
        NvidiaMonitor.assemble();
        return NvidiaMonitor;
    }
    
}
