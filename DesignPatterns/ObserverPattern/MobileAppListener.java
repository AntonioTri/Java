package DesignPatterns.ObserverPattern;

public class MobileAppListener implements EventListener{

    private final String message;

    public MobileAppListener(String message){
        this.message = message;
    }


    @Override
    public void update() {
        System.out.println("Mobile listener Updatato");
    }
    
    


}
