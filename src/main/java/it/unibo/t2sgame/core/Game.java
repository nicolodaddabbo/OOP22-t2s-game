package it.unibo.t2sgame.core;
/**
 * This interface models a generic game.
 * Every time you want to create a new game, the new game has to
 * implements this interface in order to be executed.
 */
public interface Game {
    /**
     * Starting a new sessions of the game
     */
    void start();
}
