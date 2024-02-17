package DesignPatterns.CommandPattern;

public class TurnTvOn implements CommandInterface{

    DeviceInterface theDevice;

    public TurnTvOn(DeviceInterface device){
        theDevice = device;
    }

    @Override
    public void execute() {
        theDevice.on();
    }
    
}
