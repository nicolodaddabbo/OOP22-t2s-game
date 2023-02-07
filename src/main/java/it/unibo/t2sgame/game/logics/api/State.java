package it.unibo.t2sgame.game.logics.api;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.World;

/**
 * Rapresent the state and the logics of a Game
 */
public interface State {
    /**
     * 
     * @return true if logics determines the end of the game, otherwise false
     */
    boolean isOver(List<Entity> players);
    /**
     * 
     * @return true if there aren't survived enemies, otherwise false
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
    /**
     * Generate the onPowerUp event if a power up can be issued
     * @param world
     */
    void generatePowerUp(World world);
}
