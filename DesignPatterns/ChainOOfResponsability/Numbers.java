package DesignPatterns.ChainOOfResponsability;

public class Numbers {
    
    private int nro1;
    private int nro2;

    private String calculateWanted;

    public Numbers(int number1, int number2, String calculateWanted){

        nro1 = number1;
        nro2 = number2;
        this.calculateWanted = calculateWanted;

    }

    public int getNumber1(){ return nro1;};
    public int getNumber2(){ return nro2;};
    public String getCalculationWanted(){ return calculateWanted;};

}
