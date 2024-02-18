package DesignPatterns.Proxy.Socket;

interface SocketInterface {
  String readLine();
  void  writeLine( String str );
  void  dispose();
}