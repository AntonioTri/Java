package Progetto_prog_3.entities;

import static Progetto_prog_3.utils.Constants.EnemtConstants.*;

import java.awt.Color;
import java.awt.Graphics;

import Progetto_prog_3.Game;

public class NightBorne extends AbstractEnemy{

    //I nemici non hanno bisogno di particolari attributi una volta definita la loro classe, dobbiamo solo definire il luogo di spawn
    //Dichiarata la classe, alla classe supre nel costruttore mandiamo le variabili costanti create nella clase Constants per definire
    //Hitbox e tipo di nemico
    public NightBorne(float x, float y) {
        super(x, y, NIGHT_BORNE_WIDHT, NIGHT_BORNE_HEIGHT, NIGHT_BORNE);
        initHitbox(x, y, (int)(35 * Game.SCALE), (int)(35 * Game.SCALE));
    }

    
}
