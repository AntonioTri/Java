package Lezione20_11_23;

public class BuyStockOrder implements Order {

    StockTrade stock = new StockTrade();

    @Override
    public void execute() {

        stock.buy();

    }

    public BuyStockOrder(StockTrade stk) {
        stock = stk;
    }

}
