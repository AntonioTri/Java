package Progetto_prog_3.entities.enemies;

import Progetto_prog_3.Game;
import Progetto_prog_3.entities.AbstractEnemy;
import Progetto_prog_3.entities.Player;

import static Progetto_prog_3.utils.Constants.GRAVITY;
import static Progetto_prog_3.utils.Constants.Directions.LEFT;
import static Progetto_prog_3.utils.Constants.EnemtConstants.HellBound.*;
import static Progetto_prog_3.utils.HelpMetods.canMoveHere;
import static Progetto_prog_3.utils.HelpMetods.getEntityXPosNextWall;
import static Progetto_prog_3.utils.HelpMetods.getEntityYPosFloorRoofRelative;

import java.awt.geom.Rectangle2D;

public class HellBound extends AbstractEnemy{


    private boolean jumping;
    private float jumpSpeed = -1.5f * Game.SCALE;;


    public HellBound(float x, float y) {
        super(x, y, HELL_BOUND_WIDTH, HELL_BOUND_HEIGHT, HELL_BOUND);
        initHitbox(x, y, (int)(46 * Game.SCALE), (27 * Game.SCALE));
        initattackBox();
        state = HELL_BOUND_WALK;
    }

    private void initattackBox() {
        
        attackBox = new Rectangle2D.Float(x, y, (int)(HELL_BOUND_DEAFULT_WIDTH/3), HELL_BOUND_DEAFULT_HEIGHT);
    
    }
    
	@Override
    public void update(int[][] levelData, Player player) {
        if (active) {
            updateMove(levelData, player);
            updateAttackBoxDirection();
        }

        updateAnimationTick();

        if (state == HELL_BOUND_HIT) {
            gainKnokBack();
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
            attackBox.x = hitbox.x + (int)((HELL_BOUND_DEAFULT_WIDTH/3 )* 3);
            attackBox.y = hitbox.y;
        }
    }
    
    private void updateMove(int[][] levelData, Player player) {

        // if (jumping && canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
        //     hitbox.y += airSpeed;
        //     airSpeed += GRAVITY;
            
        //     if (canMoveHere(hitbox.x + walkSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
        //         move(levelData);
                
        //     } else {
        //         hitbox.x = getEntityXPosNextWall(hitbox, walkSpeed);
        //     }

        // } else {
        //     System.out.println("Lo sto mandando nell'ipersfera");
        //     System.out.println(jumping);
        //     System.out.println(canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData));
        //     hitbox.y = getEntityYPosFloorRoofRelative(hitbox, airSpeed);
        //     jumping = false;
        // }

        if (firstUpdate) {
            firstUpdateCheck(levelData);
        }

        if (inAir) {
            updateInAir(levelData);
        } else {

            switch (state) {
                    
                case HELL_BOUND_WALK:    
                    this.walkSpeed = 1f;
                    aniSpeed = 20;

                    if (canSeePlayer(levelData, player)) {
                        System.out.println(enemyTileY);
                        System.out.println((int)(player.y / Game.TILES_SIZE));
                        System.out.println("Can see player");

                    }
                    
                    move(levelData);

                default:
                    break;
            }


        }
    }
    
    private void jumpAttack() {

        // if (jumping) return;
        
        // jumping = true;
        // airSpeed = jumpSpeed;
        newState(HELL_BOUND_WALK);
   
    }

    private void gainKnokBack() {
    }


}
