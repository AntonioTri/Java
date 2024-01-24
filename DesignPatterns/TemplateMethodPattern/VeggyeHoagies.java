package DesignPatterns.TemplateMethodPattern;

public class VeggyeHoagies extends Hoagies{

    @Override
    void addMeat() {
        System.out.println("Im not adding meat");
    }

    @Override
    void addCheese() {
        System.out.println("Im noot adding cheese");
    }

    @Override
    void addVegetables() {
        System.out.println("Adding veggies");
    }
    
}
