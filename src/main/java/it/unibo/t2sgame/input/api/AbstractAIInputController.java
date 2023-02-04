package it.unibo.t2sgame.input.api;

import java.util.Queue;

/**
 * This abstract class represent an InutController for an AI with a default implementation of
 * the method getCommandsQueue
 */
public abstract class AbstractAIInputController extends AbstractInputController {
    private static final int RIGHT_MAX_ABSOLUTE_ANGLE = 45;
    private static final int LEFT_MIN_ABSOLUTE_ANGLE = 135;

    /**
     * This method adds the next command to be executed by the AI controlled entity
     */
    protected abstract void computeNextCommand();

    /**
     * This template method return the commands queue (in the way that is implemented 
     * in the parent class) but first it execute the computeNextCommand method
     */
    @Override
    public Queue<Command> getCommandsQueue() {
        computeNextCommand();
        return super.getCommandsQueue();
    }

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
