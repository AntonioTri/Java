package Progetto_prog_3.entities;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

//Classe madre per tutte le entità che dovranno essere create, come i nemici o gli oggetti raccoglibili, implementa una posizione di partenza, 
//una hitbox, ed un metodo drowhitbox che ci permette di disegnare una linea attorno all'entità in questione
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

    protected void drawHitbox(Graphics g){

        //g.setColor(Color.BLUE);
        //g.drawRect((int)hitbox.x, (int)hitbox.y, (int) hitbox.width, (int) hitbox.height);

    }

    public Rectangle2D.Float getHitbox(){

        return hitbox;

    }

}
