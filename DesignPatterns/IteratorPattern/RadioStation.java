package DesignPatterns.IteratorPattern;

public class RadioStation {
    
    public static void main(String[] args) {
        
        SongsOfThe70 songs70 = new SongsOfThe70();
        SongsOfThe80 songs80 = new SongsOfThe80();
        SongsOfThe90 songs90 = new SongsOfThe90();

        DiscJokey madMyke = new DiscJokey(songs70, songs80, songs90);

        madMyke.showTheSongs();


    }
}
