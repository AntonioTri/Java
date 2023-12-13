package Progetto_prog_3.objects;

import static Progetto_prog_3.utils.Constants.ObjectConstants.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.entities.Player;
import Progetto_prog_3.levels.Level;
import Progetto_prog_3.utils.LoadSave;

public class ObjectManager {
    
    private Playing playing;
    private BufferedImage[][] potionImages, boxesImages;
    private BufferedImage spikeImage;
    private ArrayList<Potion> potions;
    private ArrayList<LootBox> lootBoxes;
    private ArrayList<Spike> spikes;


    public ObjectManager(Playing playing){
        this.playing = playing;
        loadImages();
    }

    public void loadObjects(Level level) {
    
        potions = new ArrayList<>(level.getPotions());
        lootBoxes =  new ArrayList<>(level.getLootBoxes());
        spikes = new ArrayList<>(level.getSpike());

    }

    public void checkSpikesTouched(Player p){
        for (Spike s : spikes) {
            if (s.getHitbox().intersects(p.getHitbox())) {
                p.die();
            }
        }
    }

    //Questa funzione ci permette di controllare se il player hoverlaps, cammina sopra, una pozione, in tal caso, la pozione viene 
    //settata in stato di falso e quindi non sarà più raccoglibile e scomparirà dalla scena, ed al Player viene associato l'effetto
    //della pozione che ha appena raccolto
    public void checkPlayerTouched(Rectangle2D.Float hitbox){
        
        for (Potion potion : potions) {
            if (potion.isActive()) {
                if (hitbox.intersects(potion.hitbox)) {
                    applyEffectToPlayer(potion);
                    potion.setActive(false);

                }
            }
        }
    }

    //La funzione che applica al player l'effetto della pozione raccolta
    //!!QUA CI STA UN DESIGN PATERN AL 100%
    public void applyEffectToPlayer(Potion potion){

        if (potion.getObjType() == RED_POTION) {
            playing.getPlayer().changeHealth(RED_POTION_VALUE);
        } else {
            playing.getPlayer().changePower(BLUE_POTION_VALUE);
        }

    }

    public void checkObjectHit(Rectangle2D.Float playerAttackBox){

        for (LootBox box : lootBoxes) {
            if (box.isActive() && box.getHitbox().intersects(playerAttackBox)) {
        
                box.setAnimation(true);
                    
                int type = 0;
                int offset = 9;

                if (box.getObjType() == BARREL) {
                    type = 1;
                    offset = 0;
                }

                //Momento di spawn di una pozione al posto di una loot box a momento della distruzione di questa
                if (box.getCanSpawnPotion()) {
                    potions.add(new Potion( (int)(box.getHitbox().x - 5 + box.getHitbox().width / 2),
                                            (int)(box.getHitbox().y - offset),
                                            type));
                    box.setCanSpawnPotion(false);
                    System.out.println("Spawned a Potion");
                    }

                return;
                
            }
        }
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

        //Si carica l'immagine delle spine
        spikeImage = LoadSave.getSpriteAtlas(LoadSave.SPIKE_ATLAS);
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
        drawTraps(g,xLevelOffset);
    }


    

    private void drawBoxes(Graphics g, int xLevelOffset) {
        
        for (LootBox box : lootBoxes) {
            
            int type = 0;
            
            if (box.isActive() && box.getObjType() == BARREL) {
                type = 1;
            } 

            if (box.isActive()) {
                g.drawImage(boxesImages[type][box.getAniIndex()],
                    (int)(box.getHitbox().x - box.getxDrawOffset() - xLevelOffset),
                    (int)(box.getHitbox().y - box.getyDrawOffset()),
                    CONTAINER_WIDTH,
                    CONTAINER_HEIGHT, 
                    null);
    
                box.drawHitbox(g, xLevelOffset);

            }
        }
    }


    private void drawPotions(Graphics g, int xLevelOffset) {

        for (Potion potion : potions) {

            int type = 0;
            
            if (potion.isActive() && potion.getObjType() == RED_POTION) {
                type = 1;
            } 

            if (potion.isActive()) {
                g.drawImage(potionImages[type][potion.getAniIndex()],
                    (int)(potion.getHitbox().x - potion.getxDrawOffset() - xLevelOffset),
                    (int)(potion.getHitbox().y - potion.getyDrawOffset()),
                    POTION_WIDTH,
                    POTION_HEIGHT, 
                    null);
                
                potion.drawHitbox(g, xLevelOffset);

            }
        }
    }

    private void drawTraps(Graphics g, int xLevelOffset) {

        for (Spike spike : spikes) {
            g.drawImage(spikeImage, (int)(spike.getHitbox().x - xLevelOffset), (int) spike.getHitbox().y - spike.getyDrawOffset(), SPIKE_WIDTH, SPIKE_HEIGHT, null);
        }

    }

    public void resetAllObjects() {

        loadObjects(playing.getLevelManager().getCurrentLevel());

        for (Potion potion : potions)
            potion.reset();
        for (LootBox box : lootBoxes)
            box.reset();
    }


    

}
