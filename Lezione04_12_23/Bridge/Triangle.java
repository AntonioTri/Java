package Lezione04_12_23.Bridge;
import Lezione04_12_23.Bridge.*;

public class Triangle extends Shape{
    
    public Triangle(Colorz c){
        super(c);
    }

    @Override
    public void applyColor() {
        color.applyColor();
    }




}
