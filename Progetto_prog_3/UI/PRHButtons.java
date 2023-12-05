package Progetto_prog_3.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Progetto_prog_3.utils.LoadSave;
import static Progetto_prog_3.utils.Constants.UI.PhrButtons.*;


public class PRHButtons extends PauseButtons {

    private BufferedImage[] imgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;

    public PRHButtons(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages(){
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.PRH_BUTTONS);

        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * PRH_BUTTONS_DEFAULT_SIZE, rowIndex * PRH_BUTTONS_DEFAULT_SIZE, PRH_BUTTONS_DEFAULT_SIZE, PRH_BUTTONS_DEFAULT_SIZE);
        }
    }

    public void update(){

        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }

    }

    public void draw(Graphics g){
        g.drawImage(imgs[index], x, y, PRH_BUTTONS_SIZE, PRH_BUTTONS_SIZE, null);
    }

    public void resetBools(){

        mouseOver = false;
        mousePressed = false;

    }

    public boolean getMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean getMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    };

    
}
