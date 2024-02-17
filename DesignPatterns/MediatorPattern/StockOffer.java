package DesignPatterns.MediatorPattern;

public class StockOffer {
    
    private int stockShares = 0;
    private String stockSymbol = "";
    private int colleaguecode = 0;

    public StockOffer(int numOfShares, String stock, int collCode){
        stockShares = numOfShares;
        stockSymbol = stock;
        colleaguecode = collCode;
    }

    public int getStockShares() {
        return stockShares;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getColleaguecode() {
        return colleaguecode;
    }



}
