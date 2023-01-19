package it.unibo.t2sgame.core.api;

import it.unibo.t2sgame.model.api.World;
/**
 * This interface abstracts the concepts of "Game" ("Partita" in italian)
 */
public interface Game {
    /**
     * 
     * @return the state of the current game
     */
    State getState();
    /**
     * 
     * @return the world of the current game
     */
    World getWorld();
    /**
     * Generating a new wave
     */
    void nextWave();
}
