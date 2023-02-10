package it.unibo.t2sgame.game.logics.api;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.game.ecs.api.Entity;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.World;

/**
 * Rapresent the state and the logics of a Game.
 */
public interface State {
    /**
     * 
     * @param players the list of players which are playing the Game.
     * 
     * @return true if logics determines the end of the game, otherwise false.
     */
    boolean isOver(List<Entity> players);

    /**
     * 
     * @param wave the wave which the status has to check if empty or not.
     *             This wave to be checked should be the wave linked to the current
     *             round,
     *             contained in the World.
     * 
     * @return true if there aren't survived enemies, otherwise false.
     */
    boolean isWaveOver(Optional<Wave> wave);

    /**
     * 
     * @return an integer rappresenting the number of the current round.
     */
    int getRound();

    /**
     * Increment the number of round.
     * The incrementing depends on the State's implementation.
     */
    void incrementRound();

    /**
     * Generate the onPowerUp event if a power up can be issued.
     * 
     * @param world the world where the event "Power up generated" will be notified
     */
    void generatePowerUp(World world);
}
