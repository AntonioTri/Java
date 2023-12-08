package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.*;
import static Progetto_prog_3.utils.HelpMetods.*;

import java.awt.geom.Rectangle2D.Float;

import static Progetto_prog_3.utils.Constants.Directions.*;

import Progetto_prog_3.Game;

public abstract class AbstractEnemy extends Entity{

    private int aniIndex, enemyState, enemyType;
    private int aniTick, aniSpeed = 25;
    private boolean firstUpdate = true, inAir = false;
    private float fallSpeed, gravity = (float) 0.04 * Game.SCALE;
    private float wlakSpeed = 0.5f * Game.SCALE;
    private int wlakDir = LEFT;

    public AbstractEnemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
    
    }
    

    private void updateAnimationTick(){

        aniTick++;
        if (aniTick >= aniSpeed) {

            aniTick = 0;
            aniIndex++;

            if (aniIndex >= getSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }
    }

    public void update(int[][] levelData){

        updateMove(levelData);
        updateAnimationTick();

    }

    private void updateMove(int[][] levelData){

        if (firstUpdate) {

            if (!isEntityOnFloor(hitbox, levelData)) {
                inAir = true;
            }

            firstUpdate = false;

        }

        if (inAir) {
            
            if (canMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                hitbox.y = getEntityYPosFloorRoofRelative(hitbox, fallSpeed);
            }

        } else{
            switch (enemyState) {
                case NIGHT_BORNE_IDLE:
                    enemyState = NIGHT_BORNE_RUN;
                    break;
                case NIGHT_BORNE_RUN:
                    float xSpeed = 0;

                    if (wlakDir == LEFT) {
                        xSpeed = -wlakSpeed;
                    } else {
                        xSpeed = wlakSpeed;
                    }

                    if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
                        if (isFloor(hitbox, xSpeed, levelData)) {
                            hitbox.x += xSpeed;
                            return;
                        }
                    }

                changeWalkDir();


                break;
            }
        }

    }
    
    private void changeWalkDir() {
        if (wlakDir == LEFT) {
            wlakDir = RIGHT;
        } else {
            wlakDir = LEFT;
        }
    }


    public int getAniIndex(){
        return aniIndex;
    }

    public int getEnemyState(){
        return enemyState;
    }


}
