package Progetto_prog_3.objects;

import static Progetto_prog_3.utils.Constants.ObjectConstants.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.utils.LoadSave;

public class ObjectManager {
    
    private Playing playing;
    private BufferedImage[][] potionImages, boxesImages;
    private ArrayList<Potion> potions;
    private ArrayList<LootBox> lootBoxes;


    public ObjectManager(Playing playing){
        this.playing = playing;
        loadImages();

        potions = new ArrayList<>();
        lootBoxes = new ArrayList<>();

        potions.add(new Potion(300, 300, RED_POTION));
        potions.add(new Potion(400, 300, BLUE_POTION));
        lootBoxes.add(new LootBox(500, 300, BARREL));
        lootBoxes.add(new LootBox(600, 300, BOX));

    }


    private void loadImages() {
        //Si caricanole immagini delle pozioni
        BufferedImage potionSprites = LoadSave.getSpriteAtlas(LoadSave.POTIONS);
        potionImages = new BufferedImage[2][7];

        for (int j = 0; j < potionImages.length; j++) {
            for (int i = 0; i < potionImages[j].length; i++) {
                potionImages[j][i] = potionSprites.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }

        //Si caricanole immagini dei contenitori
        BufferedImage boxesSprites = LoadSave.getSpriteAtlas(LoadSave.BOXES_SPRITE);
        boxesImages = new BufferedImage[2][8];

        for (int j = 0; j < boxesImages.length; j++) {
            for (int i = 0; i < boxesImages[j].length; i++) {
                boxesImages[j][i] = boxesSprites.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }
    }

    public void update(){
        for (Potion potion : potions) {
            if (potion.isActive()) {
                potion.update();
            }
        }

        for (LootBox box : lootBoxes) {
            if (box.isActive()) {
                box.update();
            }
        }
    }


    public void draw(Graphics g, int xLevelOffset){
        drawPotions(g, xLevelOffset);
        drawBoxes(g, xLevelOffset);
    }


    private void drawBoxes(Graphics g, int xLevelOffset) {
        
        for (LootBox box : lootBoxes) {
            
            int type = 0;
            
            if (box.isActive() && box.getObjType() == BARREL) {
                type = 1;
            } 

            g.drawImage(boxesImages[type][box.getAniIndex()],
                (int)(box.getHitbox().x - box.getxDrawOffset() - xLevelOffset),
                (int)(box.getHitbox().y - box.getyDrawOffset()),
                CONTAINER_WIDTH,
                CONTAINER_HEIGHT, 
                null);

        }
    }


    private void drawPotions(Graphics g, int xLevelOffset) {

        for (Potion potion : potions) {

            int type = 0;
            
            if (potion.isActive() && potion.getObjType() == RED_POTION) {
                type = 1;
            } 

            g.drawImage(potionImages[type][potion.getAniIndex()],
                (int)(potion.getHitbox().x - potion.getxDrawOffset() - xLevelOffset),
                (int)(potion.getHitbox().y - potion.getyDrawOffset()),
                POTION_WIDTH,
                POTION_HEIGHT, 
                null);

        }

    }

}
