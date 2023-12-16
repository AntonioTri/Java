package Progetto_prog_3.objects;

import Progetto_prog_3.Game;

//La classe cannone agiunge i cannoni all'interno della mappa, questi sparano al player se è visibile e sono indistruttibili.
//Sono fissi in un posto e sono rivolti solo a destra o a sinistra
public class Cannon extends AbstractObject{

    private int cannonTileY;

    public Cannon(int x, int y, int objType) {
        super(x, y, objType);

        cannonTileY = y / Game.TILES_SIZE;
        initHitbox(40, 26);
        //Queste picole modificee servono a far scendere l'immagine dal centro, lo sprite ha dello spazio vuoto sotto
        hitbox.x -= (int)(4 * Game.SCALE);
        hitbox.y += (int)(6 * Game.SCALE);
    }
    

    public void update(){
        if (doAnimation) {
            updateAnimationTick();
        }
    }

    public int getCannonTyleY(){
        return cannonTileY;
    }



}
