package it.unibo.t2sgame.input.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.InputController;

public class KeyboardInputController implements InputController {
    private final static int MOVE_UP_CODE = 87;
    private final static int MOVE_RIGHT_CODE = 68;
    private final static int MOVE_DOWN_CODE = 83;
    private final static int MOVE_LEFT_CODE = 65;

    private static final Map<Integer, Command> MOVESET = Map.of(
        MOVE_UP_CODE, new MoveUp(),
        MOVE_RIGHT_CODE, new MoveRight(),
        MOVE_DOWN_CODE, new MoveDown(),
        MOVE_LEFT_CODE, new MoveLeft()
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
