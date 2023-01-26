package it.unibo.t2sgame.input.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;

public class KeyboardInputController implements InputController {
    private static final int MOVE_UP_CODE = 87;
    private static final int MOVE_RIGHT_CODE = 68;
    private static final int MOVE_DOWN_CODE = 83;
    private static final int MOVE_LEFT_CODE = 65;
    private static final int SHOOT_UP_CODE = 38;
    private static final int SHOOT_RIGHT_CODE = 39;
    private static final int SHOOT_DOWN_CODE = 40;
    private static final int SHOOT_LEFT_CODE = 37;

    private static final Map<Integer, Command> MOVESET = Map.of(
        MOVE_UP_CODE, new Move(Directions.UP),
        MOVE_RIGHT_CODE, new Move(Directions.RIGHT),
        MOVE_DOWN_CODE, new Move(Directions.DOWN),
        MOVE_LEFT_CODE, new Move(Directions.LEFT),
        SHOOT_UP_CODE, new Shoot(Directions.UP),
        SHOOT_RIGHT_CODE, new Shoot(Directions.RIGHT),
        SHOOT_DOWN_CODE, new Shoot(Directions.DOWN),
        SHOOT_LEFT_CODE, new Shoot(Directions.LEFT)
    );

    private Optional<Command> command = Optional.empty();

    public void notifyKeyPressed(final int keyCode) {
        this.command = MOVESET.containsKey(keyCode) ? Optional.of(MOVESET.get(keyCode)) : Optional.empty();
    }

    public void notifyKeyReleased(final int keyCode) {
        if (this.command.isPresent()) {
            this.command = this.command.get().equals(MOVESET.get(keyCode)) ? Optional.of(new Move(Directions.STAY)) : this.command;
        }
    }

    @Override
    public Optional<Command> getCommand() {
        return this.command;
    }
    
}
