package it.unibo.t2sgame.input.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.InputController;

public class KeyboardInputController implements InputController {
    private static final Map<Integer, Command> MOVESET = Map.of(
        87, new MoveUp(),
        68, new MoveRight(),
        83, new MoveDown(),
        65, new MoveLeft()
    );

    private Optional<Command> command = Optional.empty();

    public void notifyKeyPressed(final int keyCode) {
        this.command = MOVESET.containsKey(keyCode) ? Optional.of(MOVESET.get(keyCode)) : Optional.empty();
    }

    public void notifyKeyReleased(final int keyCode) {
        if (this.command.isPresent()) {
            this.command = this.command.get().equals(MOVESET.get(keyCode)) ? Optional.of(new Stop()) : this.command;
        }
    }

    @Override
    public Optional<Command> getCommand() {
        return this.command;
    }
    
}
