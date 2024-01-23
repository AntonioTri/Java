package DesignPatterns.ChainOfResponsability;

public class ChainOfResponsabilityTest {

    public static void main(String[] args) {

        ChainInterface chainCalc1 = new AaddNumbers();
        ChainInterface chainCalc2 = new SubtractNumber();
        ChainInterface chainCalc3 = new MultiplyNumbers();
        ChainInterface chainCalc4 = new DivideNumbers();
        
        chainCalc1.setNextChain(chainCalc2);
        chainCalc2.setNextChain(chainCalc3);
        chainCalc3.setNextChain(chainCalc4);
    
        Numbers request = new Numbers(4, 2, "mult");

        chainCalc1.calculate(request);
    
    }
}
