package it.unibo.t2sgame.input.impl;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

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

    private static final Map<Integer, Command> WALK_MOVESET = Map.of(
        MOVE_UP_CODE, new Move(Directions.UP),
        MOVE_RIGHT_CODE, new Move(Directions.RIGHT),
        MOVE_DOWN_CODE, new Move(Directions.DOWN),
        MOVE_LEFT_CODE, new Move(Directions.LEFT)
    );

    private static final Map<Integer, Command> SHOOT_MOVESET = Map.of(
        SHOOT_UP_CODE, new Shoot(Directions.UP),
        SHOOT_RIGHT_CODE, new Shoot(Directions.RIGHT),
        SHOOT_DOWN_CODE, new Shoot(Directions.DOWN),
        SHOOT_LEFT_CODE, new Shoot(Directions.LEFT)
    );

    private Optional<Command> moveState = Optional.empty();
    private Optional<Command> shootState = Optional.empty();
    private Queue<Command> commandsQueue = new LinkedList<>();

    public void notifyKeyPressed(final int keyCode) {
        this.moveState = WALK_MOVESET.containsKey(keyCode) ? Optional.of(WALK_MOVESET.get(keyCode)) : this.moveState;
        this.shootState = SHOOT_MOVESET.containsKey(keyCode) ? Optional.of(SHOOT_MOVESET.get(keyCode)) : Optional.empty();
        this.moveState.ifPresent(this.commandsQueue::add);
        this.shootState.ifPresent(this.commandsQueue::add);
    }

    public void notifyKeyReleased(final int keyCode) {
        if (this.moveState.isPresent()) {
            this.moveState = this.moveState.get().equals(WALK_MOVESET.get(keyCode)) ? Optional.of(new Move(Directions.STAY)) : this.moveState;
            this.moveState.ifPresent(this.commandsQueue::add);
        }
    }

    @Override
    public Optional<Command> getCommand() {
        return this.commandsQueue.isEmpty() ? Optional.empty() : Optional.of(this.commandsQueue.poll());
    }
    
}
