package DesignPatterns.ObserverPattern.Observer2;

public class BinObserver extends Observer {
   
    public BinObserver( Subject s ) {
        this.subj = s;
        subj.attach( this ); 
    }
 
    public void update() {
       System.out.print( "  " + Integer.toBinaryString( subj.getState() ) );
    }    
 
 }  
