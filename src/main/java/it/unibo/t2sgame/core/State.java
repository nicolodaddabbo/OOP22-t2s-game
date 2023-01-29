package it.unibo.t2sgame.core;
/**
 * Represents the logics/state of the current game.
 */
public interface State {
    /**
     * 
     * @return true if the game is over, which means the possibility to exit from the game loop
     */
    boolean isOver();
}
