package DesignPatterns.Visitor;

public class VisitorTest {

    public static void main(String[] args) {

        TaxVisitor taxCalculator = new TaxVisitor();
        TaxHolydaysVisitoor taxHolydaysVisitor = new TaxHolydaysVisitoor();

        Necessity milk = new Necessity(3.47);
        Tobacco malboro = new Tobacco(5.50);
        Liquor vodka = new Liquor(11.99);

        milk.accept(taxCalculator);
        malboro.accept(taxCalculator);
        vodka.accept(taxCalculator);

        milk.accept(taxHolydaysVisitor);
        malboro.accept(taxHolydaysVisitor);
        vodka.accept(taxHolydaysVisitor);

    }

}
