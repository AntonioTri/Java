package DesignPatterns.IteratorPattern;

import java.util.ArrayList;
import java.util.Iterator;

public class SongsOfThe70 implements SongIterator {

    private ArrayList<SongInfo> bestsSong;

    public SongsOfThe70(){

        bestsSong = new ArrayList<SongInfo>();
        addSong("Imagine", "Jhon Lennon", 1971);
        addSong("American Pie", "Don McLean", 1971);
        addSong("I Will Survive", "Gloria Gaynor", 1971);

    }

    private void addSong(String name, String autor, int year){
        bestsSong.add(new SongInfo(name, autor, year));
    }

    /* 
    private ArrayList<SongInfo> getBestSongs(){
        return bestsSong;
    }
    */

    @Override
    public Iterator createIterator() {
        return bestsSong.iterator();
    };

}
