package it.unibo.t2sgame.game;

import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.World;

/**
 * This interface abstracts the concepts of "Game" ("Partita" in italian).
 */
public interface Game {
    /**
     * 
     * @return the state of the current game.
     */
    State getState();

    /**
     * 
     * @return the world of the current game.
     */
    World getWorld();

    /**
     * 
     * @return true if the logics of the game determs the end of the game,
     *         otherwise false.
     */
    boolean isOver();

    /**
     * Update the game based on the events and checks occoured.
     */
    void update();
}
