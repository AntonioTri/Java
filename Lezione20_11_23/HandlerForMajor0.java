package Lezione20_11_23;

public class HandlerForMajor0 extends Handler {

    @Override
    public void handleRequest(Request request){

        if(request.getValue() < 0){
            System.out.println("Sono l'Handelr per i maggiori di 0 e rispondo alla richiesta: " + request.getValue());
        } else {
            
            succ.handleRequest(request);

        }

    }


}
