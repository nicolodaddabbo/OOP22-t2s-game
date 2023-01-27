package it.unibo.t2sgame.input.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.EntityState;

public class EntityStateImpl<I> implements EntityState<I> {
    /**
     * Every state is a Command, and this Map is useful for 
     * associate to every Input a state (i.e. command)
     */
    private final Map<I, Command> states;
    private Optional<Command> currentCommand;

    public EntityStateImpl(final Map<I, Command> states) {
        this.states = states;
        this.currentCommand = Optional.empty();
    }

    @Override
    public void notifyInput(final I input) {
        this.currentCommand = this.states.containsKey(input) ? Optional.of(this.states.get(input)) : this.currentCommand;
    }

    @Override
    public void notifyInputRelease(final I input, final Optional<Command> releaseCommandValue) {
        if (this.currentCommand.isEmpty()) {
            return;
        }
        this.currentCommand = this.states.get(input) == this.currentCommand.get() ? releaseCommandValue : this.currentCommand;
    }

    @Override
    public Optional<Command> getCurrentCommand() {
        return this.currentCommand;
    }
    
}
