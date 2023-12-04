package Lezione04_12_23.Bridge;

import java.awt.Color;

public abstract class Shape {
    
    protected Color color;

    public Shape(Color color){
        this.color = color;
    }

    abstract public void applyColor();

}

/*
 * 
 */
