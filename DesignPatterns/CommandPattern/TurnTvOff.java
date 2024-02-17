package DesignPatterns.CommandPattern;

public class TurnTvOff implements CommandInterface{

    DeviceInterface theDevice;

    public TurnTvOff(DeviceInterface device){
        theDevice = device;
    }

    @Override
    public void execute() {
        theDevice.off();
    }
    
}
