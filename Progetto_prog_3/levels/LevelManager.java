package Progetto_prog_3.levels;

import static Progetto_prog_3.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import Progetto_prog_3.levels.Level;
import Progetto_prog_3.Game;
import Progetto_prog_3.GameStates.GameState;
import Progetto_prog_3.utils.LoadSave;

public class LevelManager {
 
    Game game;
    BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private int levelIndex = 0;

    public LevelManager(Game game){
        this.game = game;
        importSprite();
        levels = new ArrayList<>();
        buildAllLevels();

    }

    //!!!!!!! QUESTO MI PARE IL BRIDGE !!!!!!
    private void buildAllLevels() {
        BufferedImage[] alllevels = LoadSave.getAllLevels();
        for (BufferedImage img : alllevels) {
            //!!!!!!! QUESTO MI PARE IL BRIDGE !!!!!!
            levels.add(new Level(img));
        }
    }

    private void importSprite() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for(int j = 0; j<4; j++){
            for (int i = 0; i < 12; i++) {
                int index = j*12 + i;
                levelSprite[index] = img.getSubimage(i*32, j * 32, 32, 32);
            }

        }

    }

    public void draw(Graphics g, int xLevelOffset){

        for(int j = 0; j<Game.TILES_IN_HEIGHT; j++){
            //Questo è un array list, possiamo accedere ad un elemento della lista tramite indice e con il metodo get()
            for (int i = 0; i < levels.get(levelIndex).getLD()[0].length; i++){
                int index = levels.get(levelIndex).getSpriteIndex(i, j);
                //Quì viene aggiunto l'offset in x per spostare il rendering dlla mappa a dare l'illusione del movimento
                g.drawImage(levelSprite[index], TILES_SIZE*i - xLevelOffset, TILES_SIZE*j, TILES_SIZE, TILES_SIZE, null);
                
            }
        }



    }

    public Level getCurrentLevel(){

        return levels.get(levelIndex);

    }


    public void update(){



    }

    public int getAmountOfLevels(){
        return levelIndex;
    }

    public void loadNextLevel() {
        levelIndex++;
        if (levelIndex >= levels.size()) {
            levelIndex = 0;
            System.out.println("You completed all the levels!");
            GameState.state = GameState.MENU;
        }
        
        Level newLevel = levels.get(levelIndex);
        game.getPlaying().getEnemyManager().addEnemies(newLevel);
        game.getPlaying().getPlayer().loadLevelData(newLevel.getLD());
        game.getPlaying().setMaxLevelOffset(newLevel.getLevelOffset());
    }

}
