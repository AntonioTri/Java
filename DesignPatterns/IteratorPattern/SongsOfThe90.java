package DesignPatterns.IteratorPattern;

import java.util.Hashtable;
import java.util.Iterator;

public class SongsOfThe90 implements SongIterator {

    private Hashtable<Integer, SongInfo> bestSongs = new Hashtable<Integer, SongInfo>();
    private int hashKey = 0;

    public SongsOfThe90(){

        addSong("Losing My Religion", "REM", 1991);
        addSong("Creep", "Radiohead", 1993);
        addSong("Walk on the Ocean", "Toad The Wet Sprocket", 1991);


    }

    private void addSong(String name, String autor, int year){
        
        bestSongs.put(hashKey, new SongInfo(name, autor, year));
        hashKey++;

    }
    /*
    public Hashtable<Integer, SongInfo> getBestSongs(){
        return bestSongs;
    }
    */

    @Override
    public Iterator createIterator() {
        return bestSongs.values().iterator();
    };
}
