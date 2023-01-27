package it.unibo.t2sgame.input.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.EntityState;

public class EntityStateImpl<I> implements EntityState<I> {
    private final Map<I, Command> moveset;
    private Optional<Command> currentCommand;

    public EntityStateImpl(final Map<I, Command> moveset) {
        this.moveset = moveset;
        this.currentCommand = Optional.empty();
    }

    @Override
    public void notifyInput(final I input) {
        this.currentCommand = this.moveset.containsKey(input) ? Optional.of(this.moveset.get(input)) : this.currentCommand;
    }

    @Override
    public void notifyInputRelease(final I input, final Optional<Command> releaseCommandValue) {
        if (this.currentCommand.isEmpty()) {
            return;
        }
        this.currentCommand = this.moveset.get(input) == this.currentCommand.get() ? releaseCommandValue : this.currentCommand;
    }

    @Override
    public Optional<Command> getCurrentCommand() {
        return this.currentCommand;
    }
    
}
