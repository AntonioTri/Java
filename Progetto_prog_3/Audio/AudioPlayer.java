package Progetto_prog_3.Audio;

import static Progetto_prog_3.utils.Constants.EnemtConstants.NIGHT_BORNE;
import static Progetto_prog_3.utils.Constants.EnemtConstants.NIGHT_BORNE_ATTACK;
import static Progetto_prog_3.utils.Constants.PlayerConstants.JUMPING_UP;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    
    //Lista delle soundtrack numerate
    public static int MENU_MUSIC = 0;
    public static int LEVEL_1 = 1;
    public static int LEVEL_2 = 2;

    //Lista di suoni numerati
    public static final int PLAYER_HURT = 0;
    public static int PLAYER_HURT_1 = 0;
    public static int PLAYER_HURT_2 = 1;
    public static int PLAYER_HURT_3 = 2;

    public static final int PLAYER_ATTACK = 12;
    public static int PLAYER_ATTACK_1 = 12;
    public static int PLAYER_ATTACK_2 = 13;
    public static int PLAYER_ATTACK_3 = 14;

    public static final int PLAYER_JUMPING = 3;
    public static int PLAYER_JUMPING_1 = 3;
    public static int PLAYER_JUMPING_2 = 4;

    public static final int PLAYER_LANDING = 5;
    public static int PLAYER_LANDING_1 = 5;
    public static int PLAYER_LANDIN_2 = 6;


    public static int WALKING_ON_GRASS = 7;
    public static int NIGHTBORRNE_DIE = 8;

    public static final int NIGHTBORNE_HURT = 9;
    public static int NIGHTBORNE_HURT_1 = 9; 
    public static int NIGHTBORNE_HURT_2 = 10;
    public static int NIGHTBORNE_HURT_3 = 11;

    public static int LEVEL_COMPLITED = 15;
    public static int GAME_OVER = 16;

    //La clip è il modo di java di eseguirre suoni in un programma, è un contenitore capace di storare file .WAV
    private Clip[] songs, effects;

    //Variabili di ambiente
    private int currentSongId;
    private float volume = 1f;
    private boolean songMute, effectMute;
    private Random rand = new Random();

    public AudioPlayer(){

        loadSong();
        loadSoundEffects();
        playSong(MENU_MUSIC);
        
    }

    private void loadSong(){

        String[] names = {"menuMusic", "level1", "level2"};
        songs = new Clip[names.length];

        for (int i = 0; i < songs.length; i++) {
            songs[i] = getClip(names[i]);
        }

    }

    private void loadSoundEffects(){

        String[] effectsName = {"hurt1", "hurt2", "hurt3", "jumping1", "jumping2", "landing", "landing2", "walking_on_grass", "nightBorneDie", "nightBorneHurt1","nightBorneHurt2","nightBorneHurt3", "attack1", "attack2", "attack3", "lvlcompleted", "gameover"};
        effects = new Clip[effectsName.length];

        for (int i = 0; i < effects.length; i++) {
            effects[i] = getClip(effectsName[i]);
        }

        updateEffectsVolume();
    }

    private Clip getClip(String name){

        URL url = getClass().getResource("/Progetto_prog_3/res/audio/" + name + ".wav");
        AudioInputStream audio;

        try {

            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        
        return null;

    }


    private void updateSongVolume(){

        FloatControl gaincoControl = (FloatControl)songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gaincoControl.getMaximum() - gaincoControl.getMinimum();
        float gane = (range * volume) + gaincoControl.getMinimum();
        gaincoControl.setValue(gane);

    }


    private void updateEffectsVolume(){

        for (Clip c : effects) {
			FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * volume) + gainControl.getMinimum();
			gainControl.setValue(gain);
		}
    }



    public void togleSongMute(){
        this.songMute = !songMute;
        for (Clip c : songs) {
            BooleanControl booleanControl = (BooleanControl)(c.getControl(BooleanControl.Type.MUTE));
            booleanControl.setValue(songMute);
        }
        
    }


    public void togleEffectsMute(){
        this.effectMute = !effectMute;
        for (Clip sfx : effects) {
            BooleanControl booleanControl = (BooleanControl)(sfx.getControl(BooleanControl.Type.MUTE));
            booleanControl.setValue(songMute);
        }

        if (!effectMute) {
            playEffect(JUMPING_UP);
        }

    }

    public void setVolume(float volume){
        this.volume = volume;
        updateSongVolume();
        updateEffectsVolume();
    }

    public void stopSong(){
        if (songs[currentSongId].isActive()) {
            songs[currentSongId].stop();
        }
    }

    public void setLevelSong(int lvlIndex){
        if (lvlIndex % 2 == 0) {
            playSong(LEVEL_1);
        } else {
            playSong(LEVEL_2);
        }
    }

    public void levelComplited(){
        stopSong();
        playEffect(LEVEL_COMPLITED);
    }

    public void playSetOfEffect(int EFFECT){

        int choosen = EFFECT + rand.nextInt(getNumberOfSounds(EFFECT));
        playEffect(choosen);

    }


    public void playEffect(int EFFECT) {
        
        if (EFFECT == WALKING_ON_GRASS) {
            effects[EFFECT].start();
            return;
        }

            effects[EFFECT].setMicrosecondPosition(0);
            effects[EFFECT].start();



    }

    public void playSong(int SONG){

        stopSong();

        currentSongId = SONG;
        updateSongVolume();
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);

    }


    private int getNumberOfSounds(int SOUND){

        switch (SOUND) {
            case PLAYER_ATTACK: return 3;
            case PLAYER_JUMPING: return 2;
            case PLAYER_LANDING: return 2;
            case PLAYER_HURT: return 3;
            case NIGHTBORNE_HURT: return 3; 
            default: return SOUND;
        }
    }


}
