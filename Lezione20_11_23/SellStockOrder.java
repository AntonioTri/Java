package Lezione20_11_23;

public class SellStockOrder implements Order{
    
    StockTrade stock = new StockTrade();

    @Override
    public void execute() {

        stock.sell();

    }

    public SellStockOrder(StockTrade stk){
        stock = stk;
    }

}
