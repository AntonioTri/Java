package DesignPatterns.ObserverPattern;

public class EmailMsgListener implements EventListener{
    
    private final String email;

    public EmailMsgListener(String email){
        this.email = email;
    }

    @Override
    public void update(){
        System.out.println("Email listener updatato");
    };

}
