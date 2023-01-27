package it.unibo.t2sgame.input.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;

public class BasicEnemyAIInputController implements InputController {
    private final List<Command> availableCommands;

    public BasicEnemyAIInputController() {
        this.availableCommands = new LinkedList<>();
        for (var direction : Directions.values()) {
            this.availableCommands.add(new Move(direction));
        }
    }

    @Override
    public Optional<Command> getCommand() {
        final Command nextMove = entity -> {
            if (entity.getWorld().isEmpty()) {
                throw new IllegalArgumentException();
            }
            final var world = entity.getWorld().get();
            var closestPlayer = world.getPlayers().get(0);
            for (var nextPlayer : world.getPlayers()) { // get the closest player
                if (entity.getPosition().distance(nextPlayer.getPosition()) < entity.getPosition()
                        .distance(closestPlayer.getPosition())) {
                    closestPlayer = nextPlayer;
                }
            }
            final var dX = closestPlayer.getPosition().getX() - entity.getPosition().getX();
            final var dY = closestPlayer.getPosition().getY() - entity.getPosition().getY();
            final var angle = Math.toDegrees(Math.atan2(dY, dX)); // get the angle between the player an the enemy in degree
            entity.notifyComponent(PhysicsComponent.class, () -> findDirectionGivenAngle(angle));
        };
        return Optional.of(nextMove);
    }

    private Directions findDirectionGivenAngle(final Double angle) {
        final var absAngle = Math.abs(angle);
        if (absAngle <= 45) {
            return Directions.RIGHT;
        } else if (absAngle >= 135) {
            return Directions.LEFT;
        } else if (absAngle > 45 && absAngle < 135 && angle < 0) {
            return Directions.UP;
        } else {
            return Directions.DOWN;
        }
    }

}
