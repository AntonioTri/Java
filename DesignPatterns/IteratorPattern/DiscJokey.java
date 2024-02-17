package DesignPatterns.IteratorPattern;

import java.util.Iterator;

public class DiscJokey {

    SongIterator iterator70;
    SongIterator iterator80;
    SongIterator iterator90;

    public DiscJokey(SongIterator songs70, SongIterator songs80, SongIterator songs90){

        this.iterator70 = songs70;
        this.iterator80 = songs80;
        this.iterator90 = songs90;

    }

    public void showTheSongs(){

        Iterator songs70 = iterator70.createIterator();
        Iterator songs80 = iterator80.createIterator();
        Iterator songs90 = iterator90.createIterator();

        System.out.println("Songs of the 70s:");
        printTheSongs(songs70);
        System.out.println("Songs of the 80s:");
        printTheSongs(songs80);
        System.out.println("Songs of the 90s:");
        printTheSongs(songs90);

    }

    private void printTheSongs(Iterator songs) {
        
        while (songs.hasNext()) {

            SongInfo currentSong = (SongInfo) songs.next();

            System.out.println(currentSong.getSongName());
            System.out.println(currentSong.getBandName());
            System.out.println(currentSong.getYearReleased());

        }
    }


}
