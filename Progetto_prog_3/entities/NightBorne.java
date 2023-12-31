package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.NightBorne.*;
import static Progetto_prog_3.utils.Constants.Directions.*;
import java.awt.geom.Rectangle2D;
import Progetto_prog_3.Game;

public class NightBorne extends AbstractEnemy{

    private int attackBoxOffsetX;

    //I nemici non hanno bisogno di particolari attributi una volta definita la loro classe, dobbiamo solo definire il luogo di spawn
    //Dichiarata la classe, alla classe supre nel costruttore mandiamo le variabili costanti create nella clase Constants per definire
    //Hitbox e tipo di nemico
    public NightBorne(float x, float y) {
        super(x, y, NIGHT_BORNE_WIDHT, NIGHT_BORNE_HEIGHT, NIGHT_BORNE);
        initHitbox(x, y, (int)(45 * Game.SCALE), (int)(40 * Game.SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int)(65 * Game.SCALE), (int)(65 * Game.SCALE));
        attackBoxOffsetX = (int)(Game.SCALE * 20);
    }

    @Override
    public void update(int[][] levelData, Player player){
            
        if(active){
            updateMove(levelData, player);
            updateAttackBoxDirection();
        }
        updateAnimationTick();

        if (state == NIGHT_BORNE_HITTED) {
            this.invulnerability = true;
        } else {
            this.invulnerability = false;
        }

     

    }

    //In base al movimento se sia destro o sinistro l'attackbox viene posizionata a destra o a sinistra della hitbox
    private void updateAttackBoxDirection() {
        if (wlakDir == LEFT) {
            attackBox.x = hitbox.x - attackBoxOffsetX - 5;
            attackBox.y = hitbox.y - 45;
        } else {
            attackBox.x = hitbox.x + 5;
            attackBox.y = hitbox.y - 45;
        }

    }

    private void updateMove(int[][] levelData, Player player){

        if (firstUpdate)
            firstUpdateCheck(levelData);

        if (inAir) {
            updateInAir(levelData);
            

        } else{
            //!!!!! QUESTA PROBABILMENTE DEVE ESSERE TRASFORMATA IN UNA FUNZIONE A MOMENTO IN UI CI SARANNO TANTI NEMICI !!!!!!!
            switch (state) {
                //Lo stato del Nightborne viene impostato a running da subito
                case NIGHT_BORNE_IDLE:
                    state = NIGHT_BORNE_RUN;
                    break;
                //Se lo stato è quello del running vengono fatti dei controlli
                case NIGHT_BORNE_RUN:
                    aniSpeed = 20;
                    //Se il nightboren può vedere il player si gira verso di esso
                    if (canSeePlayer(levelData, player)) {
                        turnTowardsPlayer(player);

                        //Se il player è abbastanza vicino al nemico, questo farà un attacco
                        //Dopo aver fatto tutto, il nemico torna a muversi nel livello
                        if (isPlayerCloseForAttack(player)) {
                            //Viene sewttato lo stato ad attacco
                            newState(NIGHT_BORNE_ATTACK);
                        }

                    }
                    
                    move(levelData);
                    break;
                    
                case NIGHT_BORNE_ATTACK:

                    aniSpeed = 13;

                    //La variabile attackChecked identifica se l'attacco è stato eseguito
                    //Nel primo momento in cui la attackbox del nemico collide con la hitbox del player
                    //A questo viene applicato il danno e viene sambiato lo stato della flag di attavvo a true
                    //Segnalango che l'attacco è stato eseguito, non ne verranno fatti altri ad ogni tick di agiornamento 
                    if (aniIndex == 0) {
                        attackChecked = false;
                    }
                    if (aniIndex == 10 && !attackChecked) {
                        checkEnemyHit(attackBox, player);
                    }
                    break;
                    
            
            }
        }
    }

    //I successivi due metodi ci permettono di modificare la direzione del movimento o per 
    //meglio dire, il modo in cui viene disegnato uno sprite, per dare l'illusione che il nemico stia
    //facendo una zione oppure l'altra
    public int flipX(){
        if (wlakDir == LEFT) {
            return hitBoxWidth + 10;
        } else{
            return 0;
        }
    }
    public int flipW(){
        if (wlakDir == LEFT) {
            return -1;
        } else{
            return 1;
        }
    }

}
