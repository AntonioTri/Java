package Progetto_prog_3.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import Progetto_prog_3.Game;
import Progetto_prog_3.GameStates.GameState;
import Progetto_prog_3.GameStates.Playing;
import Progetto_prog_3.utils.LoadSave;
import static Progetto_prog_3.utils.Constants.UI.PauseButtons.*;
import static Progetto_prog_3.utils.Constants.UI.PhrButtons.*;

//Classe che definisce la schermata di ausa, nella quale coesistono diversi bottoni per diverse
//Funzionalità legate al gameplay, come il volume, reset del livello, ritorno alla schermata iniziale
public class PauseOverlay {
    
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgWidth, bgHeight;

    //Bottoni presenti nell schermata
    private SoundButton musicButon, sfxButton;
    private PRHButtons homeB, replayB, unpauseB;

    //Variabile per accedere al game state di "Playing"
    Playing playing;

    public PauseOverlay(Playing playing){

        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createPRHButtons();

    }

    private void createPRHButtons() {

        //Differenti posizioni in x
        int homeX = (int)(313 * Game.SCALE);
        int replayX = (int)(387 * Game.SCALE);
        int unpauseX = (int)( 461 * Game.SCALE);
        //Posizione uguale in y
        int buttonY = (int)(325 * Game.SCALE);

        homeB = new PRHButtons(homeX, buttonY, PRH_BUTTONS_SIZE, PRH_BUTTONS_SIZE, 2);
        replayB = new PRHButtons(replayX, buttonY, PRH_BUTTONS_SIZE, PRH_BUTTONS_SIZE, 1);
        unpauseB = new PRHButtons(unpauseX, buttonY, PRH_BUTTONS_SIZE, PRH_BUTTONS_SIZE, 0);


    }


    private void createSoundButtons() {
        int buttonX = (int) (450 * Game.SCALE);
        int musicX = (int) ( 140 * Game.SCALE);
        int sfxY = (int) ( 186* Game.SCALE);
        
        musicButon = new SoundButton(buttonX, musicX, SUOND_SIZE, SUOND_SIZE);
        sfxButton = new SoundButton(buttonX, sfxY, SUOND_SIZE, SUOND_SIZE);

    }

    //Questo metodo come gli altri gia incontrati durante il progetto, carica un png per l'immagine del nostro Pause Menu
    private void loadBackground() {
        
        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgWidth = (int) (backgroundImg.getWidth() * Game.SCALE); 
        bgHeight = (int) (backgroundImg.getHeight() * Game.SCALE);

        bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
        bgY = (int) (25 * Game.SCALE);
    
    }

    public void update(){
        musicButon.update();
        sfxButton.update();
        homeB.update();
        replayB.update();
        unpauseB.update();
    }

    public void draw(Graphics g){
        //Background
        g.drawImage(backgroundImg, bgX, bgY, bgWidth, bgHeight, null);
        
        //Sound buttons
        sfxButton.draw(g);
        musicButon.draw(g);

        //Home Resume unpause button
        homeB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

    }

    public void mouseDragged(MouseEvent e){

    }

    //Questo metodo si attiva quando il tasto del mouse viene premuto, 
    //Modifica lo stato del bottone a premuto permettendo di mostrare lo sprite corretto
    public void mousePressed(MouseEvent e){

        if (mouseHovering(e, musicButon) ) {
            musicButon.setMousePressed(true);  
        } else if(mouseHovering(e, sfxButton) ){
            sfxButton.setMousePressed(true);
        } else if (mouseHovering(e, homeB)) {
            homeB.setMousePressed(true);
        } else if (mouseHovering(e, replayB)) {
            replayB.setMousePressed(true);
        } else if (mouseHovering(e, unpauseB)) {
            unpauseB.setMousePressed(true);
        }
        
    }

    //Questo metodo ci permette di osservare quando il tasto viene lasciato, dopo che è stato premuto
    public void mouseReleased(MouseEvent e){

        //Questa serie di if else statements, serve a definire un evento specifico nella 
        //Schermata di pausa, se iol mouse si trova sopra ad un bottone e se questo viene premuto
        //Avvia il suo stato e modifica le componenti di gioco come il suono o il Game state

        //Music button
        if (mouseHovering(e, musicButon) ) {
            if (musicButon.getMousePressed()) {
                musicButon.setMuted(!musicButon.getMuted());
            }
        //Sound Effects button
        } else if(mouseHovering(e, sfxButton) ){
            if (sfxButton.getMousePressed()) {
                sfxButton.setMuted(!sfxButton.getMuted());
            }
        //Home Button
        } else if(mouseHovering(e, homeB) ){
            if (homeB.getMousePressed()) {
                GameState.state = GameState.MENU;
                playing.unpauseGame();
            }
        //Replay Button
        } else if(mouseHovering(e, replayB) ){
            if (replayB.getMousePressed()) {
                System.out.println("Replay level! WIP");
            }
        //Unpause Button
        } else if(mouseHovering(e, unpauseB) ){
            if (unpauseB.getMousePressed()) {
                playing.unpauseGame();
            }
        }

        //Si resetano i valori booleani per resettare gli sprite
        musicButon.resetBools();
        sfxButton.resetBools();
        homeB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
    }

    //Questa funzione osserva se il mouse sta passando sopra ad un bottone, in tal caso
    //Setta il suo stato su Hover per mostrare lo sprite corretto
    public void mouseMoved(MouseEvent e){

        musicButon.setMouseOver(false);
        sfxButton.setMouseOver(false);
        homeB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        if (mouseHovering(e, musicButon)) {
            musicButon.setMouseOver(true);
        } else if(mouseHovering(e, sfxButton)){
            sfxButton.setMouseOver(true);
        } else if(mouseHovering(e, homeB)){
            homeB.setMouseOver(true);
        } else if(mouseHovering(e, replayB)){
            replayB.setMouseOver(true);
        } else if(mouseHovering(e, unpauseB)){
            unpauseB.setMouseOver(true);
        }
    }

    //Si usa il polimorfismo per la classe PauseButton
    private boolean mouseHovering(MouseEvent e , PauseButtons pb){

        return pb.getVolumeHitbox().contains(e.getX(), e.getY());
        

    }



}
