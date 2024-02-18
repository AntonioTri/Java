package DesignPatterns.Proxy.Socket;

import java.io.*;  
import java.net.*;

public class ProxyDemo {
  public static void main( String[] args ) {

    // 3. The client deals with the wrapper
    SocketInterface socket = new SocketProxy( "127.0.0.1", 8189,
      args[0].equals("first") ? true : false );

    String  str = null;
    boolean skip = true;
    while (true) {
      if (args[0].equals("second")  &&  skip) {
        skip = ! skip;
      }
      else {
        str = socket.readLine();
        System.out.println( "Receive - " + str );  // java ProxyDemo first
        if (str.equals("quit")) break;             // Receive - 123 456
      }                                            // Send ---- 234 567
      System.out.print( "Send ---- " );            // Receive - 345 678
     
      // Implementare lettura stringa
      str = "ciao";
	      
     
      socket.writeLine( str );                     // java ProxyDemo second
      
      
      if (str.equals("quit")) break;               // Send ---- 123 456
    }                                              // Receive - 234 567
    socket.dispose();                              // Send ---- 345 678
  }
}