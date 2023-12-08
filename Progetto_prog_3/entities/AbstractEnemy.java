package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.*;

public abstract class AbstractEnemy extends Entity{

    private int aniIndex, enemyState, enemyType;
    private int aniTick, aniSpeed = 25;

    public AbstractEnemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        initHitbox(x, y, width, height);
        this.enemyType = enemyType;
    
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

    public void update(){

        updateAnimationTick();

    }

    public int getAniIndex(){
        return aniIndex;
    }

    public int getEnemyState(){
        return enemyState;
    }


}
