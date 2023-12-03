package Progetto_prog_3.GameStates;

import Progetto_prog_3.Game;

public class State {
    
    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public Game getGame(){
        return this.game;
    }

}
