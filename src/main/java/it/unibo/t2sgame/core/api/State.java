package it.unibo.t2sgame.core.api;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Wave;

/**
 * Rapresent the current state of a Game
 */
public interface State {
    /**
     * 
     * @return true if logics determines the end of the game, otherwise false
     */
    boolean isOver(List<Entity> players);
    /**
     * 
     * @return true if there aren't survived elements, otherwise false
     */
    boolean isWaveOver(Optional<Wave> w);
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
