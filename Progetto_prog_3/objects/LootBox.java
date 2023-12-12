package Progetto_prog_3.objects;

import static Progetto_prog_3.utils.Constants.ObjectConstants.*;
import Progetto_prog_3.Game;

public class LootBox extends AbstractObject {

    public LootBox(int x, int y, int objType) {
        super(x, y, objType);
        createHitBox();
    }

    private void createHitBox(){

        if (objType == BOX) {
            initHitbox(25, 18);
            xDrawOffset = (int)(7 * Game.SCALE);
            yDrawOffset = (int)(12 * Game.SCALE);
        } else {
            initHitbox(23, 25);
            xDrawOffset = (int)(8 * Game.SCALE);
            yDrawOffset = (int)(5 * Game.SCALE);

        }

    }

    public void update(){
        if (doAnimation) {
            updateAnimationTick();
        }
    }

}