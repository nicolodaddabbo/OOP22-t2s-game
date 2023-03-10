package it.unibo.t2sgame.input.api;

import java.util.Optional;

/**
 * Interface that model the state (i.e. Concurrent State Machine) of an entity.
 * 
 * @param <I> input type
 */
public interface EntityState<I> {
    /**
     * Change the currentCommand of the state based on the input received.
     * 
     * @param input
     */
    void notifyInput(I input);

    /**
     * Change the currentCommand of the state based on the release of the input received.
     * 
     * @param input               released
     * @param releaseCommandValue optional of the command to execute when the input is released
     */
    void notifyInputRelease(I input, Optional<Command> releaseCommandValue);

    /**
     * @return the currentCommand of the entity state
     */
    Optional<Command> getCurrentCommand();
}
