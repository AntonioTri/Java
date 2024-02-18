package DesignPatterns.Visitor;

public interface Visitor {
    
    public double visit(Tobacco tobacco);
    public double visit(Liquor tobaccoItem);
    public double visit(Necessity necessityItem);

}
