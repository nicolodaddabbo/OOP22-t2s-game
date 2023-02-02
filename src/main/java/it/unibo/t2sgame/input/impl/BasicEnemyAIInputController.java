package it.unibo.t2sgame.input.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;

public class BasicEnemyAIInputController implements InputController {
    private final List<Command> availableCommands;
    private final Queue<Command> commandsQueue;

    public BasicEnemyAIInputController() {
        this.commandsQueue = new ConcurrentLinkedQueue<>();
        this.availableCommands = new LinkedList<>();
        for (var direction : Directions.values()) {
            this.availableCommands.add(new Move(direction));
        }
    }

    @Override
    public Queue<Command> getCommandsQueue() {
        computeNextCommand();
        var defensiveQueue = new LinkedList<>(this.commandsQueue);
        this.commandsQueue.clear();
        return defensiveQueue;
    }

    private void computeNextCommand() {
        Command cmd = entity -> {
            if (entity.getWorld().isEmpty()) {
                throw new IllegalArgumentException();
            }
            final var world = entity.getWorld().get();
            var closestPlayer = world.getPlayers().stream()
                    .min((p1, p2) -> Double.compare(distanceBetweenEntities(entity, p1),
                            distanceBetweenEntities(entity, p2)));
            closestPlayer.ifPresent(p -> {
                final var dX = p.getPosition().getX() - entity.getPosition().getX();
                final var dY = p.getPosition().getY() - entity.getPosition().getY();
                final var angle = Math.toDegrees(Math.atan2(dY, dX)); // get the angle between the player an the enemy
                                                                      // in degree
                entity.notifyComponent(PhysicsComponent.class, () -> findDirectionGivenAngle(angle));
            });
        };
        this.commandsQueue.add(cmd);
    }

    private double distanceBetweenEntities(final Entity e1, final Entity e2) {
        return e1.getPosition().distance(e2.getPosition());
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
