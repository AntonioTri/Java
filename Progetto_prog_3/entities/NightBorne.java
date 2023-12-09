package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.*;
import static Progetto_prog_3.utils.Constants.Directions.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import Progetto_prog_3.Game;

public class NightBorne extends AbstractEnemy{

    private int attackBoxOffsetX;

    //I nemici non hanno bisogno di particolari attributi una volta definita la loro classe, dobbiamo solo definire il luogo di spawn
    //Dichiarata la classe, alla classe supre nel costruttore mandiamo le variabili costanti create nella clase Constants per definire
    //Hitbox e tipo di nemico
    public NightBorne(float x, float y) {
        super(x, y, NIGHT_BORNE_WIDHT, NIGHT_BORNE_HEIGHT, NIGHT_BORNE);
        initHitbox(x, y, (int)(35 * Game.SCALE), (int)(30 * Game.SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int)(55 * Game.SCALE), (int)(55 * Game.SCALE));
        attackBoxOffsetX = (int)(Game.SCALE * 20);
    }

    public void update(int[][] levelData, Player player){

        updateMove(levelData, player);
        updateAnimationTick();
        updateAttackBox();

    }

    public void drawAttackbox(Graphics g, int xLevelOffset){
        g.setColor(Color.RED);
        g.drawRect((int)(attackBox.x -xLevelOffset), (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
    }

    //In base al movimento se sia destro o sinistro l'attackbox viene posizionata a destra o a sinistra della hitbox
    private void updateAttackBox() {
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
            switch (enemyState) {
                //Lo stato del Nightborne viene impostato a running da subito
                case NIGHT_BORNE_IDLE:
                    enemyState = NIGHT_BORNE_RUN;
                    break;
                //Se lo stato è quello del running vengono fatti dei controlli
                case NIGHT_BORNE_RUN:
                    //Se il nightboren può vedere il player si gira verso di esso
                    if (canSeePlayer(levelData, player)) {
                        turnTowardsPlayer(player);
                    }
                    //Se il player è abbastanza vicino al nemico, questo farà un attacco
                    if (isPlayerCloseForAttack(player)) {
                        //Viene sewttato lo stato ad attacco
                        newState(NIGHT_BORNE_ATTACK);
                    }
                    //Dopo aver fatto tutto, il nemico torna a muversi nel livello
                    move(levelData);
                    
                    break;
                    
                case NIGHT_BORNE_ATTACK:
                    if (aniIndex == 0) {
                        attackChecked = false;
                    }
                    if (aniIndex == 10 /*oppure 9, bisogna vedere */ && !attackChecked) {
                        checkEnemyHit(attackBox, player);
                    }
                case NIGHT_BORNE_HITTED:
            
            
            
            
            
            
            
            }
        }
    }


    

    public int flipX(){
        if (wlakDir == LEFT) {
            return hitBoxWidth - 8;
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
