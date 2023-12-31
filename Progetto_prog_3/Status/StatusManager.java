package Progetto_prog_3.Status;
import Progetto_prog_3.entities.Entity;

public class StatusManager {

    public void applySlow(Entity entity, int duration, float slowValue){
        
        //Si conserva lo stato attuale della velocità di movimento della entità
        float startingWalkSpeed = entity.getWalkSpeed();
        //Viene creato un nuovo thread che gestisce il debuff
        Thread slowThread = new Thread(() -> {
            //Semplicemente viene settata una debuff speed al valore passato come parametro
            entity.setWalkSpeed(slowValue);
            //Si effettua uno sleep del thread della durata del debuff scelta
            try {
                Thread.sleep(duration * 1000);
            } catch (Exception e) {
                System.out.println("Qualcosa è andato storto nella funzione di slow");
                e.printStackTrace();
            }
            //Quando la sleep è finita viene ripsistinata la velocità di movimento
            entity.setWalkSpeed(startingWalkSpeed);
        });

        //Una volta creato il thread questo viene eseguito lanciando le istruzioni
        slowThread.start();

    }
}
