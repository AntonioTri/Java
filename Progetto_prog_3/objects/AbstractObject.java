package Progetto_prog_3.objects;

import static Progetto_prog_3.utils.Constants.ObjectConstants.*;
import static Progetto_prog_3.utils.Constants.ANI_SPEED;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Progetto_prog_3.Game;

public abstract class AbstractObject {
    
    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;


    public AbstractObject(int x, int y, int objType){
    
        this.x = x;
        this.y = y;
        this.objType = objType;
        
    }

    protected void initHitbox(float width, float height){
        hitbox = new Rectangle2D.Float(x, y, (int)(width * Game.SCALE), (int)(height * Game.SCALE));
    }

    public void drawHitbox(Graphics g, int xLevelOffset){
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x - xLevelOffset, (int)hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void updateAnimationTick(){

        aniTick++;
        if (aniTick >= ANI_SPEED - 6) {

            aniTick = 0;
            aniIndex++;

            if (aniIndex >= getSpriteAmount(objType)) {
                aniIndex = 0;
                if (objType == BARREL || objType == BOX) {
                    doAnimation = false;
                    active = false;
                }

            }
        }
    }


    public void reset(){

        aniIndex = 0;
        aniTick = 0;
        active = true;

        if (objType == BARREL || objType == BOX) {
            doAnimation = false;
        } else {
            doAnimation = true;
        }

    }

    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }
    
    public int getAniIndex(){
        return aniIndex;
    }


}
