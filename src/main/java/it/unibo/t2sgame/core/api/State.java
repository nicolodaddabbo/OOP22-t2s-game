package it.unibo.t2sgame.core.api;
/**
 * Rapresent the current state of a Game
 */
public interface State {
    /**
     * 
     * @return true if logics determines the end of the game, otherwise false
     */
    boolean isOver();
    /**
     * 
     * @return an integer rappresenting the number of the current round
     */
    int getRound();
    /**
     * Increment the number of round depends on the implementation
     */
    void incrementRound();
}
