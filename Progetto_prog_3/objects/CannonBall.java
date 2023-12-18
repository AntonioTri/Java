package Progetto_prog_3.objects;

import static Progetto_prog_3.utils.Constants.Projectiles.CANNON_BALL;
import static Progetto_prog_3.utils.Constants.Projectiles.CannonBall.*;
import java.awt.geom.Rectangle2D;
import Progetto_prog_3.Game;

public class CannonBall extends AbstractProjectile {

    public CannonBall(int x, int y, int direction) {
        super(x, y, direction, CANNON_BALL);
        int yOffset = (int)( 5 * Game.SCALE);
        int xOffset = (int)(-3 * Game.SCALE);

        if (direction == 1) {
            xOffset = (int)(29 * Game.SCALE);
        }

        hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT);

    }
    
}
