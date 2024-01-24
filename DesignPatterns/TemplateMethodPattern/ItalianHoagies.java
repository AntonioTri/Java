package DesignPatterns.TemplateMethodPattern;

public class ItalianHoagies extends Hoagies {

    @Override
    void addMeat() {
        System.out.println("Adding the meat");
    }

    @Override
    void addCheese() {
        System.out.println("Adding the cheese");
    }

    @Override
    void addVegetables() {
        System.out.println("Adding veggies");
    }
    
}
