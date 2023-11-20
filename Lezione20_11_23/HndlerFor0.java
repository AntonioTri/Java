package Lezione20_11_23;

public class HndlerFor0 extends Handler{
    
    public void handleRequest(Request request){

        if(request.getValue() < 0){
            System.out.println("Sono l'Handelr per 0 e rispondo alla richiesta: " + request.getValue());
        } else {
            
            succ.handleRequest(request);

        }

    }

}
