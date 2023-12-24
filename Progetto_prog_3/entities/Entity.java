package Progetto_prog_3.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import Progetto_prog_3.Game;

//Classe madre per tutte le entità che dovranno essere create, come i nemici o gli oggetti raccoglibili, 
//implementa una posizione di partenza, 
//una hitbox, ed un metodo drowhitbox che ci permette di disegnare una linea attorno all'entità in questione
public abstract class Entity {
    
    public float x, y;
    protected int hitBoxWidth, hitBoxHeight;
    protected int aniTick, aniIndex;

    //Variabile per la vita
    protected int maxHealth ;
	protected int currentHealth = maxHealth;

    //Variabile per la velocità di movimento
    protected float walkSpeed = Game.SCALE;

    //Variabile per il salto
    protected float airSpeed = 0f;
    protected boolean inAir = true;

    //Variabile per lo stato
    protected int state;

    //Hitbox
    protected Rectangle2D.Float hitbox;

    //AttackBox
	protected Rectangle2D.Float attackBox;
    protected Ellipse2D.Float circularAttackbox;


    public Entity(float x, float y, int hitBoxWidth, int hitBoxHeight){

        this.x = x;
        this.y = y;
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;

    }

    protected void initHitbox(float x, float y, float width, float height){
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void drawHitbox(Graphics g, int xLevelOffset, int yLevelOffset){
        g.setColor(Color.BLUE);
        g.drawRect((int)hitbox.x - xLevelOffset, (int)hitbox.y - yLevelOffset, (int) hitbox.width, (int) hitbox.height);
    }

    public void drowAttackBox(Graphics g, int levelOffsetX, int yLevelOffset) {
        g.setColor(Color.RED);
        g.drawRect((int)attackBox.x - levelOffsetX, (int)attackBox.y - yLevelOffset, (int)attackBox.width, (int)attackBox.height);

    }

    public void drowCircularAttackBox(Graphics g, int levelOffsetX, int yLevelOffset) {
        g.setColor(Color.RED);
        g.drawOval((int)circularAttackbox.x - levelOffsetX, (int)circularAttackbox.y - yLevelOffset, (int)circularAttackbox.width, (int)circularAttackbox.height);

    }

    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }

    public int getState(){
        return state;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getCurrentHealth(){
        return currentHealth;
    }

}
