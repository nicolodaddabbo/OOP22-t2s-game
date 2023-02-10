package it.unibo.t2sgame.input.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.EntityState;

/**
 * Generic finite state of an entity.
 * 
 * @param <I> input type
 */
public class EntityStateImpl<I> implements EntityState<I> {
    private final Map<I, Command> moveset;
    private Optional<Command> currentCommand;

    /**
     * @param moveset Map that associate to every input a command
     */
    public EntityStateImpl(final Map<I, Command> moveset) {
        this.moveset = moveset;
        this.currentCommand = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyInput(final I input) {
        this.currentCommand = this.moveset.containsKey(input) ? Optional.of(this.moveset.get(input))
                : this.currentCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyInputRelease(final I input, final Optional<Command> releaseCommandValue) {
        if (this.currentCommand.isEmpty()) {
            return;
        }
        this.currentCommand = this.moveset.containsKey(input)
                && this.moveset.get(input).equals(this.currentCommand.get()) ? releaseCommandValue
                        : this.currentCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Command> getCurrentCommand() {
        return this.currentCommand;
    }

}
