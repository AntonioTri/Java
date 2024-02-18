package DesignPatterns.Visitor;

import java.text.DecimalFormat;

public class TaxHolydaysVisitoor implements Visitor {
    
    DecimalFormat df = new DecimalFormat("#.##");

    public TaxHolydaysVisitoor(){}

    @Override
    public double visit(Liquor liquorItem) {
        System.out.println("This is a liquor item on Holydays.");
        return Double.parseDouble(df.format((liquorItem.getPrice() * .10) + liquorItem.getPrice()));
    }

    @Override
    public double visit(Tobacco tobaccoItem) {
        System.out.println("This is a tobacco item on Holydays.");
        return Double.parseDouble(df.format((tobaccoItem.getPrice() * .28) + tobaccoItem.getPrice()));
    }

    @Override
    public double visit(Necessity necessityItem) {
        System.out.println("This is a necessity item on Holydays.");
        return Double.parseDouble(df.format((necessityItem.getPrice() * 0) + necessityItem.getPrice()));
    }

}
