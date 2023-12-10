package Progetto_prog_3.levels;

import static Progetto_prog_3.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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

    //Il seguente metodo importa i blochetti del terreno da posizionare poi nella mappa
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

    //Qui disegnamo la mapa di gioco
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

    /*
     * Il seguente metodo quando chiamato carica il prossimo livello
     * Esegue diversi passaggi, aumenta l'indice del livello, così da poter scorrere al successivo
     * Se il numero dell'indice è maggiore o uguale al numero di livello disponibili, viene resettato a 0
     * e si viene riportati al menù di partenza.
     * 
     * Se non fosse così verrebbe caricato il livello successivo andando a prendere dall'array list,
     * l'elemento di indice 'levelIndex' tramite il metodo get(), dal game scendiamo allo stato di Playing e
     * aggiungiamo i nemici in base ai nuovi dati del livello ottenuti, diamo al player le informazioni del livello
     * per permettergli di interagire con l'ambiente, e viene settato il massimo livello di spostamento della telecamerta
     * 
    */
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


    //Getters e Setters
    public Level getCurrentLevel(){
        return levels.get(levelIndex);
    }

    public void update(){

    }

    public int getAmountOfLevels(){
        return levelIndex;
    }

}
