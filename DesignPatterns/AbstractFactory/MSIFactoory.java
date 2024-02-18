package DesignPatterns.AbstractFactory;

public class MSIFactoory extends AbstractCompany {

    @Override
    public Gpu assembleGpu() {
        Gpu MSIGpu = new MSIGpu();
        MSIGpu.assemble();
        return MSIGpu;
    }

    @Override
    public Monitor assembleMonitor() {
        Monitor MSIMonitor = new MSIMonitor();
        MSIMonitor.assemble();
        return MSIMonitor;
    }
    
}
