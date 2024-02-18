package DesignPatterns.ObserverPattern.Observer2;

public class HexObserver extends Observer {

    public HexObserver( Subject s ) {
        this.subj = s;
        subj.attach( this ); 
    }
    
    public void update() {
       System.out.print( "  " + Integer.toHexString( subj.getState() ) );
    }  
 } 
