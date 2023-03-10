package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.game.ecs.api.Type;
import it.unibo.t2sgame.game.ecs.impl.PhysicsComponent;
import it.unibo.t2sgame.input.api.AbstractChasingAIInputController;

/**
 * AI that chase the closest adversary.
 */
public class ChasingAIInputController extends AbstractChasingAIInputController {
    private final Type adversaryType;

    /**
     * @param adversaryType type of the adversary to chase
     */
    public ChasingAIInputController(final Type adversaryType) {
        this.adversaryType = adversaryType;
    }

    /**
     * Notify to the PhysicsComponent a movement in the calculated direction of the player.
     */
    @Override
    protected void computeNextCommand() {
        super.addToCommandsQueue(entity -> {
            final var closestAdeversary = super.findClosestAdeversary(entity, this.adversaryType);
            closestAdeversary.ifPresent(adversary -> {
                final var dX = adversary.getPosition().getX() - entity.getPosition().getX();
                final var dY = adversary.getPosition().getY() - entity.getPosition().getY();
                final var angle = Math.toDegrees(Math.atan2(dY, dX)); // get the angle between the player and the enemy in degrees
                entity.notifyComponent(PhysicsComponent.class, () -> super.findDirectionGivenAngle(angle));
            });
        });
    }

}
