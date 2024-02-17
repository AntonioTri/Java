package DesignPatterns.IteratorPattern;

import java.util.Arrays;
import java.util.Iterator;

public class SongsOfThe80 implements SongIterator{

    private SongInfo[] bestsSong;
    private int position = 0;
    public SongsOfThe80(){

        bestsSong = new SongInfo[3];
        addSong("Roam", "B 52s", 1989);
        addSong("Cruel Summer", "Bananarama", 1984);
        addSong("Head Over Heels", "Tears For Fears", 1985);


    }

    private void addSong(String name, String autor, int year){
        
        bestsSong[position] = new SongInfo(name, autor, year);
        position++;

    }

    /* 
    public SongInfo[] getBestSongs(){
        return bestsSong;
    }
    */

    @Override
    public Iterator createIterator() {
        return Arrays.asList(bestsSong).iterator();
    };

}
