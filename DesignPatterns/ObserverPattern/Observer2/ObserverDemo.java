package DesignPatterns.ObserverPattern.Observer2;

import java.util.Scanner;

public class ObserverDemo {
    public static void main( String[] args ) {
      
        Subject sub = new Subject();
  
        // 7. Client configures the number and type of Observers
        new HexObserver( sub );  
        new OctObserver( sub );  
        new BinObserver( sub );

        Scanner scanner = new Scanner(System.in);

        while (true) {
           System.out.print( "\nEnter a number: " );
           sub.setState( scanner.nextInt() );
        }
        
    } 
}
