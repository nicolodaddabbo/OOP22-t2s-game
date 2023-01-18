package it.unibo.t2sgame.input.impl;

import java.util.Optional;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.InputController;

public class KeyboardInputController implements InputController {
    private static final int MOVEUP_KEYCODE = 38;
    private Optional<Command> command = Optional.empty();

    public void notifyKeyPressed(final int keyCode) {
        if (keyCode == MOVEUP_KEYCODE) {
            this.command = Optional.of(new MoveUp());
        } else {
            this.command = Optional.empty();
        }
    }

    @Override
    public Optional<Command> getCommand() {
        return this.command;
    }
    
}
