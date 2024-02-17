package DesignPatterns.CommandPattern;

public class DeviceButton {
    
    CommandInterface thecommand;
    
    public DeviceButton(CommandInterface command){
        thecommand = command;
    }

    public void press(){
        thecommand.execute();
    }

}
