package Progetto_prog_3.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    
    public int x, y;
    protected int hitBoxWidth, hitBoxHeight;
    protected Rectangle2D.Float hitbox;



    public Entity(int x, int y, int hitBoxWidth,int hitBoxHeight){

        this.x = x;
        this.y = y;
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;

    }

    protected void initHitbox(float x, float y, float width, float height){

        hitbox = new Rectangle2D.Float(x, y, width, height);

    }

    // protected void updateHitbox(){

    //     hitbox.x = (int) x;
    //     hitbox.y = (int) y;

    // }

    protected void drawHitbox(Graphics g){

        g.setColor(Color.BLUE);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int) hitbox.width, (int) hitbox.height);

    }

    public Rectangle2D.Float getHitbox(){

        return hitbox;

    }

}
