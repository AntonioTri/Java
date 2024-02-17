package DesignPatterns.CommandPattern;

import java.util.ArrayList;
import java.util.List;

public class PlayWithRemote {
    
    public static void main(String[] args) {

        DeviceInterface device = TvRemote.getDevice();

        TurnTvOn onCommand = new TurnTvOn(device);
        DeviceButton onPressed = new DeviceButton(onCommand);
        onPressed.press();

        TurnTvOff offCommand = new TurnTvOff(device);
        DeviceButton offPressed = new DeviceButton(offCommand);
        offPressed.press();

        TurnTvVolumeUp volumeUpCommand = new TurnTvVolumeUp(device);
        DeviceButton volumeUpPressed = new DeviceButton(volumeUpCommand);
        volumeUpPressed.press();

        TurnTvVolumeDown volumeDownCommand = new TurnTvVolumeDown(device);
        DeviceButton volumeDownPressed = new DeviceButton(volumeDownCommand);
        volumeDownPressed.press();








    }

}
