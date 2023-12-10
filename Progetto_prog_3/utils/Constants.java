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

    //Gravità
    public static final float GRAVITY = 0.04f * Game.SCALE;


    //Variabili statidche per definire I nemici, per ora soltanto i nightbornes
    public static class EnemtConstants{


        public static final int NIGHT_BORNE = 0;

        public static final int NIGHT_BORNE_IDLE = 0;
        public static final int NIGHT_BORNE_RUN = 1;
        public static final int NIGHT_BORNE_ATTACK = 2;
        public static final int NIGHT_BORNE_HITTED = 3;
        public static final int NIGHT_BORNE_DIE = 4; 


        public static final int NIGHT_BORNE_DEFAULT_WIDHT = 80;
        public static final int NIGHT_BORNE_DEFAULT_HEIGHT = 80;

        public static final int NIGHT_BORNE_WIDHT = (int)(NIGHT_BORNE_DEFAULT_WIDHT * Game.SCALE);
        public static final int NIGHT_BORNE_HEIGHT = (int)(NIGHT_BORNE_DEFAULT_HEIGHT * Game.SCALE);
        
        //Questo è l'offset di posizionamento della hitbox del naightborne
        public static final int NIGHT_BORNE_DROW_OFFSET_X = (int)(20 * Game.SCALE);
        public static final int NIGHT_BORNE_DROW_OFFSET_Y = (int)(33 * Game.SCALE);

        //Metodo per ottenere il numero di sprite giusto per ogni animazione di un nemico
        //con uno switch sul tipo di nemuco fa il return adeguato
        public static int getSpriteAmount(int enemyType, int enemyState){

            switch (enemyType) {
                case NIGHT_BORNE:
                    
                    switch (enemyState) {
                        case NIGHT_BORNE_IDLE: return 9;
                        case NIGHT_BORNE_RUN: return 6;
                        case NIGHT_BORNE_ATTACK: return 12;
                        case NIGHT_BORNE_HITTED: return 5;
                        case NIGHT_BORNE_DIE: return 23;
                    
                        default:
                        return 9;
                            
                    }
            
                default:
                return 0;
            }



        }

        //Metodo che ci permette di ritornare il valore di HP ( Punti vita ) dato uno specifico nemico
        public static int getMaxHealth(int enemyType){
            switch (enemyType) {
                case NIGHT_BORNE:
                    return 20;
                default:
                    return 0;
            }
        }

         //Metodo che ci permette di ritornare il valore di DANNO dato uno specifico nemico
        public static int getEnemyDamage(int enemyType){

            switch (enemyType) {
                case NIGHT_BORNE:
                    return 4;
                default:
                    return 0;
            }

         }


    }

    //Variabili statidche per definire la grandezza dell'ambiente
    public static class Environment {
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

		public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
	}



    public static class UI{
        //Variabili statidche per definire la grandezza dei bottoni
        public static class Buttons{
            public static final int BUTTON_DEFAULT_WIDTH = 140;
            public static final int BUTTON_DEFAULT_HEIGHT = 56;
            public static final int BUTTON_WIDTH = (int)(BUTTON_DEFAULT_WIDTH * Game.SCALE);
            public static final int BUTTON_HEIGHT = (int)(BUTTON_DEFAULT_HEIGHT * Game.SCALE);
        }

        //Variabili statidche per definire la grandezza dei bottoni di pausa
        public static class PauseButtons{

            public static final int SUOND_SIZE_DEFAULT = 42;
            public static final int SUOND_SIZE = (int)(SUOND_SIZE_DEFAULT * Game.SCALE);


        }

        //Variabili statidche per definire la grandezza dei bottoni di reset, home e resume
        public static class PhrButtons{

            public static final int PRH_BUTTONS_DEFAULT_SIZE = 56;
            public static final int PRH_BUTTONS_SIZE = (int) (PRH_BUTTONS_DEFAULT_SIZE * Game.SCALE);

        } 

        //Variabili statidche per definire la grandezza della barra del volume
        public static class VolumeButton{

            public static final int VOLUME_DEFAUT_HEIGHT = 44;
            public static final int VOLUME_DEFAUT_WIDTH = 28;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) ( VOLUME_DEFAUT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) ( VOLUME_DEFAUT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) ( SLIDER_DEFAULT_WIDTH * Game.SCALE);


        }

    }

    //Variabili statidche per definire i movimenti standard globali
    public static class Directions{

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    //Variabili statidche per definire gli stati del player
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

        //Metodo per ottenere il numero di sprite corrispetivo in base all'azione del player
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
