package it.unibo.t2sgame.game.logics.api;

import it.unibo.t2sgame.game.model.api.World;

/**
 * This functional interface abstract the concept of a game 'event'
 */
@FunctionalInterface
public interface Event {
    /**
     * Execute the event
     * @param world where the event has to be executed
     */
    void execute(World world);
}
