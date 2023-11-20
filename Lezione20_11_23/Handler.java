package Lezione20_11_23;

public abstract class Handler {
    
    protected Handler succ;
    public void setSuccessor(Handler successor){
        succ = successor;
    }

    public abstract void handleRequest(Request request);
}
