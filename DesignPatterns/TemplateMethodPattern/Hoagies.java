package DesignPatterns.TemplateMethodPattern;

public abstract class Hoagies {
    
    protected final void makeSandwitch(){

        cutBun();

        if (customerWantsMeat()) {
            addMeat();
        }

        if (customerWantsCheese()) {
            addCheese();
        }

        if (customerWantsVegetables()) {
            addVegetables();
        }

    };

    public void cutBun() {
        System.out.println("Cutting bun");
    }

    abstract void addMeat();
    abstract void addCheese();
    abstract void addVegetables();

    boolean customerWantsMeat(){return true;}
    boolean customerWantsCheese(){return true;}
    boolean customerWantsVegetables(){return true;}

    public void wrapTheHoagie(){
        System.out.println("Wrapping the hoagie");
    }


}
