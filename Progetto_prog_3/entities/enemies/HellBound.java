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


    private boolean attacking;
    private float jumpForce = -1.5f * Game.SCALE;;


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
            updateAnimationTick();
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
                    aniSpeed = 22;
                    jumpAttack(levelData);
                    attacking = true;    

                    break;


                default:
                    break;
            }


        }
    }

    private void jumpAttack(int[][] levelData) {

        float orizzontalSpeed = 2f * Game.SCALE;

        if (canMoveHere(hitbox.x + orizzontalSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x -= orizzontalSpeed * flipW();
            
        }
        
        if (jumpForce <= 0 ) {
            hitbox.y +=jumpForce;
            jumpForce += GRAVITY;
        } else if (jumpForce > 0 && canMoveHere(hitbox.x, hitbox.y + jumpForce, hitbox.width, hitbox.height, levelData)) {
            hitbox.y +=jumpForce;
            jumpForce += GRAVITY;
        } else {
            jumpForce = -1.5f * Game.SCALE;
            getEntityXPosNextWall(hitbox, orizzontalSpeed);
            getEntityYPosFloorRoofRelative(hitbox, jumpForce);
            inAir = true;
            newState(HELL_BOUND_WALK);
        }

        System.out.println("jump Attack!");
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
