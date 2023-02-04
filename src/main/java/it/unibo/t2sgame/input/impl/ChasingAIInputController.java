package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.input.api.AbstractAIInputController;

public class ChasingAIInputController extends AbstractAIInputController {


    @Override
    protected void computeNextCommand() {
        this.commandsQueue.add(entity -> {
            if (entity.getWorld().isEmpty()) {
                throw new IllegalArgumentException();
            }
            final var world = entity.getWorld().get();
            var closestPlayer = world.getPlayers().stream()
                    .min((p1, p2) -> Double.compare(distanceBetweenEntities(entity, p1),
                            distanceBetweenEntities(entity, p2)));
            closestPlayer.ifPresent(player -> {
                final var dX = player.getPosition().getX() - entity.getPosition().getX();
                final var dY = player.getPosition().getY() - entity.getPosition().getY();
                final var angle = Math.toDegrees(Math.atan2(dY, dX)); // get the angle between the player and the enemy in degrees
                entity.notifyComponent(PhysicsComponent.class, () -> super.findDirectionGivenAngle(angle));
            });
        });
    }

    private double distanceBetweenEntities(final Entity e1, final Entity e2) {
        return e1.getPosition().distance(e2.getPosition());
    }

}
