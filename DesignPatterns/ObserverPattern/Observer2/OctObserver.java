package DesignPatterns.ObserverPattern.Observer2;

public class OctObserver extends Observer {

    public OctObserver( Subject s ) {
        this.subj = s;
        subj.attach( this ); 
    }
    
    public void update() {
       System.out.print( "  " + Integer.toHexString( subj.getState() ) );
    }  
 } 
