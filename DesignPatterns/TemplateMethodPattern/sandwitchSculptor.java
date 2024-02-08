package DesignPatterns.TemplateMethodPattern;

public class sandwitchSculptor {
    
    public static void main(String[] args) {
        
        Hoagies custHoagies = new ItalianHoagies();
        Hoagies custVegetariab = new VeggyeHoagies();

        custHoagies.makeSandwitch();
        custVegetariab.makeSandwitch();

    }

}
