package Progetto_prog_3.UI;

import java.awt.Rectangle;

//Questa superclasse permette la crazione di qualsivoglia bottone
//Contiene diversi metodi ed attributi utili alla creazione di un bottne come
//la hitbox rettangolare, la posizione, la larghezza e l'altezza
public class PauseButtons {
    
    protected int x, y, width, height;
    private Rectangle volumeHitbox;

    public PauseButtons(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    
        createHitbox();
    
    
    }


    //Getters e Setters
    private void createHitbox() {
        volumeHitbox = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getVolumeHitbox() {
        return volumeHitbox;
    }

    public void setVolumeHitbox(Rectangle volumeHitbox) {
        this.volumeHitbox = volumeHitbox;
    }


}
