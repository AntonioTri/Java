package Progetto_prog_3.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static Progetto_prog_3.utils.Constants.UI.PauseButtons.*;
import Progetto_prog_3.Game;
import Progetto_prog_3.utils.LoadSave;

//Classe che definisce la schermata di ausa, nella quale coesistono diversi bottoni per diverse
//Funzionalità legate al gameplay, come il volume, reset del livello, ritorno alla schermata iniziale
public class PauseOverlay {
    
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgWidth, bgHeight;
    private SoundButton musicButon, sfxButton;

    public PauseOverlay(){

        createSoundButtons();
        loadBackground();

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
    }

    public void draw(Graphics g){
        //Background
        g.drawImage(backgroundImg, bgX, bgY, bgWidth, bgHeight, null);
        
        //Sound buttons
        sfxButton.draw(g);
        musicButon.draw(g);
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
        }
        
    }

    //Questo metodo ci permette di osservare quando il tasto viene lasciato, dopo che è stato premuto
    //Setta lo stato opposto in base a quello corrente tramite una sorta di chiamata ricorsiva al contrario
    public void mouseReleased(MouseEvent e){

        if (mouseHovering(e, musicButon) ) {
            if (musicButon.getMousePressed()) {
                musicButon.setMuted(!musicButon.getMuted());
            }    
        } else if(mouseHovering(e, sfxButton) ){
            if (sfxButton.getMousePressed()) {
                sfxButton.setMuted(!sfxButton.getMuted());
            }
        }

        musicButon.resetBools();
        sfxButton.resetBools();
    }

    //Questa funzione osserva se il mouse sta passando sopra ad un bottone, in tal caso
    //Setta il suo stato su Hover per mostrare lo sprite corretto
    public void mouseMoved(MouseEvent e){
        musicButon.setMouseOver(false);
        sfxButton.setMouseOver(false);

        if (mouseHovering(e, musicButon)) {
            musicButon.setMouseOver(true);
        } 
        if(mouseHovering(e, sfxButton)){
            sfxButton.setMouseOver(true);
        }
    }

    //Si usa il polimorfismo per la classe PauseButton
    private boolean mouseHovering(MouseEvent e , PauseButtons pb){

        return pb.getVolumeHitbox().contains(e.getX(), e.getY());
        

    }



}
