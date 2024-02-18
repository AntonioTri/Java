package DesignPatterns.FlyheightPattern.Example2;

import java.awt.Color;
import java.awt.Graphics;

public class Myrect {
    
    private Color color;
    private int x, y, x1, y1;


    public Myrect(Color color){
        this.color = color;
    }

    public void draw(Graphics g, int upperX, int upperY, int lowerX, int lowerY){
        g.setColor(color);
        g.fillRect(upperX, upperY, lowerX, lowerY);
    }

    /*
    public Myrect(Color color, int upperX, int upperY, int lowerX, int lowerY){
        this.color = color;
        this.x = upperX;
        this.y = upperY;
        this.x1 = lowerX;
        this.y1 = lowerY;

    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, x1, y1);
    }
    */


}
