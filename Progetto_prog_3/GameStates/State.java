package Progetto_prog_3.GameStates;

import java.awt.event.MouseEvent;

import Progetto_prog_3.Game;
import Progetto_prog_3.UI.MenuButton;

public class State {
    
    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public boolean buttonHovered(MouseEvent e, MenuButton mb){
        return mb.getHitbox().contains(e.getX(), e.getY());
    }

    public Game getGame(){
        return this.game;
    }

}
