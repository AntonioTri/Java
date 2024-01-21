package DesignPatterns.ChainOOfResponsability;

public class SubtractNumbers implements ChainInterface {

    private ChainInterface nextChainInterface;

    @Override
    public void setNextChain(ChainInterface nextChain) {
        this.nextChainInterface = nextChain;
    }

    @Override
    public void calculate(Numbers request) {
        if (request.getCalculationWanted() == "sub") {
            System.out.println(request.getNumber1() + " - " + request.getNumber2() + " = " + (request.getNumber1() - request.getNumber2()));
        } else {
            nextChainInterface.calculate(request);
        }
    }
}

