package DesignPatterns.CommandPattern;

public class TurnTvVolumeDown implements CommandInterface{

    DeviceInterface theDevice;

    public TurnTvVolumeDown(DeviceInterface device){
        theDevice = device;
    }

    @Override
    public void execute() {
        theDevice.volumeDown();
    }
    
}
