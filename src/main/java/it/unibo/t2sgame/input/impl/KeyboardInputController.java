package it.unibo.t2sgame.input.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.t2sgame.input.api.AbstractInputController;
import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.EntityState;

public class KeyboardInputController extends AbstractInputController {
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

    private final EntityState<Integer> moveState = new EntityStateImpl<>(WALK_MOVESET);
    private final EntityState<Integer> shootState = new EntityStateImpl<>(SHOOT_MOVESET);
    private final List<EntityState<Integer>> states = List.of(this.moveState, this.shootState);

    public void notifyKeyPressed(final int keyCode) {
        this.states.forEach(s -> s.notifyInput(keyCode));
        addCommandsToQueue();
    }

    public void notifyKeyReleased(final int keyCode) {
        this.moveState.notifyInputRelease(keyCode, Optional.of(new Move(Directions.STAY)));
        this.shootState.notifyInputRelease(keyCode, Optional.empty());
        addCommandsToQueue();
    }

    private void addCommandsToQueue() {
        this.commandsQueue.addAll(this.states.stream().filter(s -> s.getCurrentCommand().isPresent()).map(s -> s.getCurrentCommand().get()).toList());
    }

}
