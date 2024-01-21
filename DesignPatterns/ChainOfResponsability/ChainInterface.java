package DesignPatterns.ChainOfResponsability;

public interface ChainInterface {
    
    public void setNextChain(ChainInterface nextChain);
    public void calculate(Numbers request);

}
