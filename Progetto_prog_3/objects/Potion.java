package Progetto_prog_3.objects;

import Progetto_prog_3.Game;

public class Potion extends AbstractObject{

    public Potion(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        initHitbox(7, 14);
        xDrawOffset = (int)(3 * Game.SCALE);
        yDrawOffset = (int)(2 * Game.SCALE);
    }

    public void update(){
        updateAnimationTick();
    }
    
}
