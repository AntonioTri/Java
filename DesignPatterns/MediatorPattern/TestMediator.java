package DesignPatterns.MediatorPattern;

public class TestMediator {
    
    public static void main(String[] args) {
        
        StockMediator nyse = new StockMediator();
        
        GormanSlacks broker = new GormanSlacks(nyse);
        JTPoorMan broker2 = new JTPoorMan(nyse);

        broker.saleOffer("MFTS", 100);
        broker.saleOffer("GOOG", 50);

        broker2.buyOffer("MFTS", 100);
        broker2.saleOffer("NRG", 10);

        broker.buyOffer("NRG", 5);

        nyse.getStockOffering();

    }
}
