package Progetto_prog_3.entities.enemies;

import Progetto_prog_3.Game;
import Progetto_prog_3.entities.AbstractEnemy;
import Progetto_prog_3.entities.Player;

import static Progetto_prog_3.utils.Constants.GRAVITY;
import static Progetto_prog_3.utils.Constants.Directions.LEFT;
import static Progetto_prog_3.utils.Constants.Directions.RIGHT;
import static Progetto_prog_3.utils.Constants.EnemtConstants.HellBound.*;
import static Progetto_prog_3.utils.HelpMetods.canMoveHere;
import static Progetto_prog_3.utils.HelpMetods.getEntityXPosNextWall;
import static Progetto_prog_3.utils.HelpMetods.getEntityYPosFloorRoofRelative;
import static Progetto_prog_3.utils.HelpMetods.isEntityOnFloor;
import static Progetto_prog_3.utils.HelpMetods.isFloor;

import java.awt.geom.Rectangle2D;

public class HellBound extends AbstractEnemy{

    private float jumpForce = -1.5f * Game.SCALE;
    private float orizzontalSpeed = 2f * Game.SCALE;
    private float slideDistanceTravelled = 0;


    public HellBound(float x, float y) {
        super(x, y, HELL_BOUND_WIDTH, HELL_BOUND_HEIGHT, HELL_BOUND);
        initHitbox(x, y, (int)(46 * Game.SCALE), (27 * Game.SCALE));
        initattackBox();
        state = HELL_BOUND_WALK;
    }

    private void initattackBox() {
        
        attackBox = new Rectangle2D.Float(x, y, (int)(HELL_BOUND_DEAFULT_WIDTH/3), HELL_BOUND_DEAFULT_HEIGHT + (int)(3 * Game.SCALE));
    
    }
    
	@Override
    public void update(int[][] levelData, Player player) {
        
        if (active) {
            updateMove(levelData, player);
            updateAttackBoxDirection();

            if (attackChecked && aniIndex == 4) {
                
            } else {
                updateAnimationTick();
            }

        }


        if (state == HELL_BOUND_HIT) {
            //gainKnokBack();
            this.invulnerability = true;
        } else {
            this.invulnerability = false;
        }
    }

    
    private void updateAttackBoxDirection() {
        if (wlakDir == LEFT) {
            attackBox.x = hitbox.x;
            attackBox.y = hitbox.y; 
        } else {
            attackBox.x = hitbox.x + (int)(hitbox.width - attackBox.width);
            attackBox.y = hitbox.y;
        }
    }
    
    private void updateMove(int[][] levelData, Player player) {

        if (firstUpdate) {
            firstUpdateCheck(levelData);
        }


        if (inAir) {
            updateInAir(levelData);
        } else {

            switch (state) {
                    
                case HELL_BOUND_WALK:    
                    this.walkSpeed = 0.4f;
                    aniSpeed = 17;

                    if (canSeePlayer(levelData, player)) {
                        turnTowardsPlayer(player);
                        newState(HELL_BOUND_RUN);
                    }
                    
                    move(levelData);
                    break;

                case HELL_BOUND_RUN:
                    this.walkSpeed = 1.5f;
                    aniSpeed = 17;
                    
                    if (isPlayerCloseForAttack(player)) {
                        System.out.println("Player in range di attaco");
                        newState(HELL_BOUND_JUMP);
                    }
                    
                    move(levelData);
                    break;

                case HELL_BOUND_JUMP:
                    aniSpeed = 15;
                    attackChecked = true;    
                    jumpAttack(levelData);

                    break;
                
                case HELL_BOUND_IDLE:
                    System.out.println("Faccio lo slide");
                    slide(levelData);
                    break;

                default:
                    break;
            }


        }
    }

    /*
     * JUMP ATTACK
     * questo attacco permette all'hellbound di saltare per attaccare il player. 
     * Vengono fatti due calcoli in modo parallelo, si agiorna la componente orizzontale e quella verticale
     * Per la direzione destra o sinistra viene fatto un controllo sul possibile movimento, 
     * viene aggiunta una componente alla coordinata se non ci sono muri in traiettoria, 
     * in caso contrario il cagnetto darà una bella testata al muro, ma nel frattemo la componente in Y
     * continuerà ad essere aggiornata fin qando il terreno soto i suoi piedi è aria
     */
    private void jumpAttack(int[][] levelData) {
        
        //Controlli per il movimento orizzontale
        if (wlakDir == RIGHT && canMoveHere(hitbox.x + orizzontalSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += orizzontalSpeed;
        } else if (wlakDir == LEFT && canMoveHere(hitbox.x - orizzontalSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x -= orizzontalSpeed;
        }
        
        //Controlli per il salto in alto, se sta salendo non ci interessa di fare altri controlli, soffitti così bassi non sono presenti in gioco
        if (jumpForce <= 0 ) {
            hitbox.y +=jumpForce;
            jumpForce += GRAVITY;
            return;
        //Controlli per il salto in basso, in questo caso il pavimento può essere presente e viene agiunto un ulteriore controllo
        //Questo può fallire ed in tal caso il RETURN non viene effettuato, andando ad eseguire i controlli aggiuntivi nella parte successiva
        } else if (jumpForce > 0 && canMoveHere(hitbox.x, hitbox.y + jumpForce, hitbox.width, hitbox.height, levelData)) {
            hitbox.y +=jumpForce;
            jumpForce += GRAVITY;
            return;
        }

        //viene resettato il valore del salto
        jumpForce = -1.5f * Game.SCALE;
        //E si riposiziona il cagnetto in relazione al terreno
        updateInAir(levelData);

        //Se il nemico si trova sul pavimento, lo stato di attacco viene spento 
        //e si resetta il loop di movimento reimpostando lo stato su WALK
        if (isEntityOnFloor(hitbox, levelData)) {
            attackChecked = false;
            newState(HELL_BOUND_IDLE);
            System.out.println("New state changedo to " + state);
        }

    }

    private void slide(int[][] levelData){

        int maxDistance = 0;

        
        if (wlakDir == LEFT && canMoveHere(hitbox.x - orizzontalSpeed, hitbox.y, hitbox.width, hitbox.height , levelData)) {
           System.out.println("Ho spostato il tizio a sinistra");
            hitbox.x -= orizzontalSpeed;
            orizzontalSpeed -= 0.01f;
            slideDistanceTravelled += Math.abs(orizzontalSpeed);

            if (slideDistanceTravelled > Game.TILES_SIZE * 3) {
                maxDistance = 1;
            }

        } else if(wlakDir == RIGHT && canMoveHere(hitbox.x + orizzontalSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)){
            System.out.println("Ho spostato il tizio a destra");
            hitbox.x += orizzontalSpeed;
            orizzontalSpeed -= 0.1f;
            slideDistanceTravelled += Math.abs(orizzontalSpeed);
            
            if (slideDistanceTravelled > Game.TILES_SIZE * 3) {
                maxDistance = 1;
            }
        }

        if (maxDistance == 1) {
            newState(HELL_BOUND_WALK);
            orizzontalSpeed = 2f * Game.SCALE;
            slideDistanceTravelled = 0;
            System.out.println("Stato impostato a " + state);
        }

    }

    @Override
    public int flipX() {
        if (wlakDir == LEFT) {
            return 0;
        } else{
            return hitBoxWidth;
        }
    }

    @Override
    public int flipW() {
        if (wlakDir == LEFT) {
            return 1;
        } else { 
            return -1;
        }
    }


}
