package it.unibo.t2sgame.input.api;

import java.util.Optional;
import java.util.Queue;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;

/**
 * This abstract class represent an InutController for an AI that chase an entity
 */
public abstract class AbstractChasingAIInputController extends AbstractInputController {
    private static final int RIGHT_MAX_ABSOLUTE_ANGLE = 45;
    private static final int LEFT_MIN_ABSOLUTE_ANGLE = 135;

    /**
     * This method adds the next command to be executed by the AI controlled entity
     */
    protected abstract void computeNextCommand();

    /**
     * This template method return the commands queue (in the way that is
     * implemented
     * in the parent class) but first it execute the computeNextCommand method
     */
    @Override
    public Queue<Command> getCommandsQueue() {
        computeNextCommand();
        return super.getCommandsQueue();
    }

    /**
     * This method return the closest adversary to the current entity
     * 
     * @param currentEntity
     * @param adversaryType the Type of the adversary to chase
     * @return the closest adversary
     */
    protected Optional<Entity> findClosestAdeversary(final Entity currentEntity, final Type adversaryType) {
        if (currentEntity.getWorld().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return currentEntity.getWorld().get().getEntities().stream()
                .filter(e -> e.getType().equals(adversaryType))
                .min((adv1, adv2) -> Double.compare(distanceBetweenEntities(currentEntity, adv1),
                        distanceBetweenEntities(currentEntity, adv2)));
    }

    /**
     * @param e1
     * @param e2
     * @return the distance between e1 and e2
     */
    private double distanceBetweenEntities(final Entity e1, final Entity e2) {
        return e1.getPosition().distance(e2.getPosition());
    }

    /**
     * This method convert an angle in degrees into one of the 4 cardinal directions
     * 
     * @param angle in degree
     * @return direction corresponding to the given angle
     */
    protected Directions findDirectionGivenAngle(final Double angle) {
        final var absAngle = Math.abs(angle);
        if (absAngle <= RIGHT_MAX_ABSOLUTE_ANGLE) {
            // the angle is between -45 and 45 degrees
            return Directions.RIGHT;
        } else if (absAngle >= LEFT_MIN_ABSOLUTE_ANGLE) {
            // the angle is between -135 and 135 degrees
            return Directions.LEFT;
        } else if (angle < 0) {
            // the angle is between -45 and -135 degrees
            return Directions.UP;
        } else {
            // the angle is between 45 and 135 degrees
            return Directions.DOWN;
        }
    }

}
