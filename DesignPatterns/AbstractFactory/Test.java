package DesignPatterns.AbstractFactory;

public class Test {

    public static void main(String[] args) {

        AbstractCompany MSICompany = new MSIFactoory();
        AbstractCompany NvidiaCompany = new NvidiaFactoory();

        Gpu MSIGpu = MSICompany.assembleGpu();
        Monitor MSIMonitoor = MSICompany.assembleMonitor();

        Gpu NvidiaGpu = NvidiaCompany.assembleGpu();
        Monitor NvidiaMonitor = NvidiaCompany.assembleMonitor();

        MSIGpu.use();
        MSIMonitoor.use();

        NvidiaGpu.use();
        NvidiaMonitor.use();

    }


}
