package Progetto_prog_3.utils;

import Progetto_prog_3.Game;

public class Constants {

    //Questa classe contiene dei valori costanti e statici che rappresentano 
    //il numero della riga della matrice contenente i frame della azione specificata
    //Ed un metodo che in base alla azione, o alla riga scelta, restituisce il numero
    //di frame ad esso associata

    //Alla chiamata in una clase, creiamo una variabile che contiene uno di questi valori
    //costanti ed utilizziamo la funzione getSpriteAmount per otenere il numero di
    //frame associati alla azione selezionata, così da poter dare una animazione fluida
    //in base alla azione scelta, senza andare a mostrare frame vuoti che comunque verranno inizializzati

    public static class UI{

        public static class Buttons{
            public static final int BUTTON_DEFAULT_WIDTH = 140;
            public static final int BUTTON_DEFAULT_HEIGHT = 56;
            public static final int BUTTON_WIDTH = (int)(BUTTON_DEFAULT_WIDTH * Game.SCALE);
            public static final int BUTTON_HEIGHT = (int)(BUTTON_DEFAULT_HEIGHT * Game.SCALE);
        }

        public static class PauseButtons{

            public static final int SUOND_SIZE_DEFAULT = 42;
            public static final int SUOND_SIZE = (int)(SUOND_SIZE_DEFAULT * Game.SCALE);


        }

        public static class PhrButtons{

            public static final int PRH_BUTTONS_DEFAULT_SIZE = 56;
            public static final int PRH_BUTTONS_SIZE = (int) (PRH_BUTTONS_DEFAULT_SIZE * Game.SCALE);

        } 

    }

    public static class PlayerConstants{

        public static final int IDLE = 0;
        public static final int WALKING = 1;
        public static final int RUNNING = 2;
        public static final int JUMPING_UP = 3;
        public static final int TROW_SWORD = 4;
        public static final int ATTACK1 = 5;
        public static final int ATTACK2 = 6;
        public static final int JUMPING_DOWN = 7;
        public static final int HURT = 8;
        public static final int DIE = 9;

        public class Directions{
    
            public static final int LEFT = 0;
            public static final int UP = 1;
            public static final int RIGHT = 2;
            public static final int DOWN = 3;
    
        }
        
        public static int getSpriteAmount(int PLAYER_ACTION){

            switch (PLAYER_ACTION) {
                case RUNNING:
                    return 8;

                case IDLE:
                    return 8; 

                case WALKING:
                    return 8; 

                case JUMPING_UP:
                    return 4; 

                case TROW_SWORD:
                    return 6; 

                case ATTACK1:
                    return 3; 

                case ATTACK2:
                    return 4; 

                case JUMPING_DOWN:
                    return 4; 

                case HURT:
                    return 3; 

                case DIE:
                    return 3;
            
                default:
                    return 1;
            }

        }

    }



}
