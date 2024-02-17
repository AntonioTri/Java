package DesignPatterns.CommandPattern;

public class TurnTvVolumeUp implements CommandInterface{

    DeviceInterface theDevice;

    public TurnTvVolumeUp(DeviceInterface device){
        theDevice = device;
    }

    @Override
    public void execute() {
        theDevice.volumeUp();
    }
    
}
