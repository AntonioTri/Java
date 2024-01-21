package DesignPatterns.ChainOOfResponsability;

public class DivideNumbers implements ChainInterface {
    
    private ChainInterface nextChainInterface;

    @Override
    public void setNextChain(ChainInterface nextChain) {
        this.nextChainInterface = nextChain;
    }

    @Override
    public void calculate(Numbers request) {
        if (request.getCalculationWanted() == "div") {
            System.out.println(request.getNumber1() + " / " + request.getNumber2() + " = " + (request.getNumber1() / request.getNumber2()));
        } else {
            System.out.println("La richiesta non può essere Portata a termine");
        }
    }
}
