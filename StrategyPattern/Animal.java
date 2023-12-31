package StrategyPattern;

public abstract class Animal {
    
    //Si crea un oggetto interfaccia che deve implementare il metodo FLY
    protected FlyInterface flyTipe;

    //Il metodo ci permette di usare la funzioneimplementata dalle classi figlie
    public String tryToFly(){
        return flyTipe.fly();
    }

    //Questo metodo ci permette di cambiare il fly type durante il runtime a condizione di qualche evento
    public void setFlyType(FlyInterface newFlyType){
        this.flyTipe = newFlyType;
    }

}
