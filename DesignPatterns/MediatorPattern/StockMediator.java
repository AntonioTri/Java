package DesignPatterns.MediatorPattern;

import java.util.ArrayList;

public class StockMediator implements Mediator {

    private ArrayList<Colleague> colleagues;
    private ArrayList<StockOffer> stockBuyOffers;
    private ArrayList<StockOffer> stockSelOffers;

    private int colleagueCode = 0;

    public StockMediator(){
        colleagues = new ArrayList<Colleague>();
        stockBuyOffers = new ArrayList<StockOffer>();
        stockSelOffers = new ArrayList<StockOffer>();
    }

    @Override
    public void saleOffer(String stock, int shares, int collCode) {
        
        boolean stockSold = false;

        for(StockOffer offer: stockBuyOffers){
            if ((offer.getStockSymbol() == stock) && (offer.getStockShares() == shares)) {
                System.out.println(shares + " shares of " + stock + " sold to colleague code " + offer.getColleaguecode());
                stockBuyOffers.remove(offer);
                stockSold = true;
            }
            if (stockSold) {
                break;
            }
            if (!stockSold) {
                System.out.println(shares + " shares of " + stock + " added to inventory.");
                StockOffer newOffering = new StockOffer(shares, stock, collCode);
                stockSelOffers.add(newOffering);
            }
            
        }

    }

    @Override
    public void buyOffer(String stock, int shares, int collCode) {
        
        boolean stockBought = false;

        for(StockOffer offer: stockSelOffers){
            if ((offer.getStockSymbol() == stock) && (offer.getStockShares() == shares)) {
                System.out.println(shares + " shares of " + stock + " bought to colleague code " + offer.getColleaguecode());
                stockSelOffers.remove(offer);
                stockBought = true;
            }
            if (stockBought) {
                break;
            }
            if (!stockBought) {
                System.out.println(shares + " shares of " + stock + " added to inventory.");
                StockOffer newOffering = new StockOffer(shares, stock, collCode);
                stockBuyOffers.add(newOffering);
            }
            
        }
    }

    @Override
    public void addColleague(Colleague colleague) {
        colleagueCode++;
        colleague.setColleaguecode(colleagueCode);
        colleagues.add(colleague);
    }

    public void getStockOffering(){
        System.out.println("\nStock for sale.");
        for (StockOffer so : stockSelOffers) {
            System.out.println(so.getStockShares() + " of " + so.getStockSymbol());
        }

        System.out.println("\nStock buy Offers.");
        for (StockOffer so : stockBuyOffers) {
            System.out.println(so.getStockShares() + " of " + so.getStockSymbol());
        }
    }
    
}
