package Lezione20_11_23;

public class HandlerMain {
    
    public static void main(String[] args){

        Request request0 = new Request("Richiesta 0", 0);
        Request requestPositive = new Request("Richiesta 0", 21);
        Request requestNegative = new Request("Richiesta 0", -31);

        HandlerForMajor0 h3 = new HandlerForMajor0();
        HandlerForMinus0 h1 = new HandlerForMinus0();
        HndlerFor0 h2 = new HndlerFor0();

        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        h1.handleRequest(request0);
        h1.handleRequest(requestPositive);
        h1.handleRequest(requestNegative);


    }

}
