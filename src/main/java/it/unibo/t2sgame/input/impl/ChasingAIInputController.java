package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.input.api.AbstractChasingAIInputController;

/**
 * AI that chase the closest adversary
 */
public class ChasingAIInputController extends AbstractChasingAIInputController {
    private final Type adversaryType;

    public ChasingAIInputController(final Type adversaryType) {
        this.adversaryType = adversaryType;
    }
    @Override
    protected void computeNextCommand() {
        super.addToCommandsQueue(entity -> {
            var closestAdeversary = super.findClosestAdeversary(entity, this.adversaryType);
            closestAdeversary.ifPresent(adversary -> {
                final var dX = adversary.getPosition().getX() - entity.getPosition().getX();
                final var dY = adversary.getPosition().getY() - entity.getPosition().getY();
                final var angle = Math.toDegrees(Math.atan2(dY, dX)); // get the angle between the player and the enemy in degrees
                entity.notifyComponent(PhysicsComponent.class, () -> super.findDirectionGivenAngle(angle));
            });
        });
    }

}
