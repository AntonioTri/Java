package DesignPatterns.ObserverPattern;

public class ObserverTest {

    public static void main(String[] args) {
        
        Store store = new Store();
        store.getService().subscribe(new EmailMsgListener("ciao@gmail.com"));
        store.getService().subscribe(new MobileAppListener("gbuweriaanidfoa"));

        store.newItemPromotion();
    }


}
