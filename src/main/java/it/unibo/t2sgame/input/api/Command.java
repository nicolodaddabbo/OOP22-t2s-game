package it.unibo.t2sgame.input.api;

import it.unibo.t2sgame.core.entity.api.Entity;

/**
 * This functional interface represent a game Command.
 */
@FunctionalInterface
public interface Command {
    /**
     * Execute the command.
     * 
     * @param entity to execute the command on
     */
    void execute(Entity entity);
}
