package DesignPatterns.ObserverPattern.Observer2;

public abstract class Observer {  
    // 2. Root of the "dependent" hierarchy
    protected Subject subj;
    public abstract void update(); 

}
