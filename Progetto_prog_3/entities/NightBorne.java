package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.*;
import Progetto_prog_3.Game;

public class NightBorne extends AbstractEnemy{

    //I nemici non hanno bisogno di particolari attributi una volta definita la loro classe, dobbiamo solo definire il luogo di spawn
    //Dichiarata la classe, alla classe supre nel costruttore mandiamo le variabili costanti create nella clase Constants per definire
    //Hitbox e tipo di nemico
    public NightBorne(float x, float y) {
        super(x, y, NIGHT_BORNE_WIDHT, NIGHT_BORNE_HEIGHT, NIGHT_BORNE);
        initHitbox(x, y, (int)(35 * Game.SCALE), (int)(30 * Game.SCALE));
    }

    public void update(int[][] levelData, Player player){

        updateMove(levelData, player);
        updateAnimationTick();

    }


    private void updateMove(int[][] levelData, Player player){

        if (firstUpdate)
            firstUpdateCheck(levelData);

        if (inAir) {
            updateInAir(levelData);
            

        } else{
            switch (enemyState) {

                case NIGHT_BORNE_IDLE:
                    enemyState = NIGHT_BORNE_RUN;
                    break;

                case NIGHT_BORNE_RUN:
                if (canSeePlayer(levelData, player)) {
                    turnTowardsPlayer(player);
                }
                if (isPlayerCloseForAttack(player)) {
                    System.out.println("Attacking the player");
                    newState(NIGHT_BORNE_ATTACK);
                }
                    move(levelData);
                    break;
            }
        }

    }

    
}
