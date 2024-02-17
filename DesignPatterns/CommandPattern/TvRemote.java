package DesignPatterns.CommandPattern;

public class TvRemote {
    public static DeviceInterface getDevice(){
        return new Television();
    }
}
