package DesignPatterns.CommandPattern;

public class Television implements DeviceInterface {

    private int volume = 0;

    @Override
    public void on() {
        System.out.println("Tv is ON");
    }

    @Override
    public void off() {
        System.out.println("Tv is OFF");
    }

    @Override
    public void volumeUp() {
        volume++;
        System.out.println("Tv volume is: " + volume);
    }

    @Override
    public void volumeDown() {
        volume--;
        if (volume <= 0) volume = 0;
        System.out.println("Tv volume is: " + volume);
    }




}
