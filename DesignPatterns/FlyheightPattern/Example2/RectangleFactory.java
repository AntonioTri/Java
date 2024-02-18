package DesignPatterns.FlyheightPattern.Example2;

import java.awt.Color;
import java.util.HashMap;

public class RectangleFactory {
    
    private static final HashMap<Color, Myrect> rectsByColor = new HashMap<Color, Myrect>();

    public static Myrect gMyrect(Color color){
        Myrect rect = (Myrect)rectsByColor.get(color);
        
        if (rect == null) {
            rect = new Myrect(color);
            rectsByColor.put(color, rect);
        }

        return rect;
    }

}
