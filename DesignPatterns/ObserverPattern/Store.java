package DesignPatterns.ObserverPattern;

public class Store {

    private final NotificationService notificationService;

    public Store(){
        notificationService = new NotificationService();
    }

    public void newItemPromotion(){
        notificationService.notifai();

    };


    public NotificationService getService(){
        return this.notificationService;
    }


}
