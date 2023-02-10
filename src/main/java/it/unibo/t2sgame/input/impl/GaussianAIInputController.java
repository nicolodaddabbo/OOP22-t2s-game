package it.unibo.t2sgame.input.impl;

import java.util.Random;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.input.api.AbstractChasingAIInputController;
import it.unibo.t2sgame.input.api.Directions;

/**
 * AI that uses Gaussian distribution to generate random movements for an
 * entity, with the aim of gradually approaching the adversary's position.
 */
public class GaussianAIInputController extends AbstractChasingAIInputController {
    private final Random random = new Random();
    private static final long TIME_TO_NEXT_DECISION = 300;
    private static final long STANDARD_DEVIATION = 500;
    private static final double TWO_PI = 2 * Math.PI;
    // Factor used to adjust the scale of the normally distributed random variable
    private static final double SCALE_FACTOR = -2;
    private long lastChoiceTime;
    private final Type adversaryType;

    /**
     * Gaussian AI InputController constructor.
     * 
     * @param adversaryType type of the adversary to chase
     */
    public GaussianAIInputController(final Type adversaryType) {
        this.adversaryType = adversaryType;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    protected void computeNextCommand() {
        super.addToCommandsQueue(entity -> {
            if (isTimeToChoose()) {
                final var closestAdeversary = super.findClosestAdeversary(entity, this.adversaryType);
                closestAdeversary.ifPresent(a -> entity.notifyComponent(PhysicsComponent.class,
                        () -> generateGaussianRandomDirection(entity.getPosition(),
                                closestAdeversary.get().getPosition(), STANDARD_DEVIATION)));
                this.lastChoiceTime = System.currentTimeMillis();
            }
        });
    }

    /**
     * This method uses Box-Muller transform to generate a pair of indepented normally distributed 
     * random numbers then scales them using the adversary's position.
     * @param currentEntityPosition
     * @param adversaryPosition
     * @param standardDeviation
     * @return a Gaussian distributed random direction
     */
    private Directions generateGaussianRandomDirection(final Vector2D currentEntityPosition, final Vector2D adversaryPosition,
            final double standardDeviation) {
        // Generate 2 random numbers, u1 and u2, from a uniform distribution between 0 and 1
        final var u1 = this.random.nextDouble();
        final var u2 = this.random.nextDouble();
        // This equation generate a STANDARD normally distributed random variable for the x-coordinate
        final var standardX = Math.sqrt(SCALE_FACTOR * Math.log(u1)) * Math.cos(TWO_PI * u2);
        // and this generate it for the y-coordinate
        final var standardY = Math.sqrt(SCALE_FACTOR * Math.log(u1)) * Math.sin(TWO_PI * u2);
        /*
         * This equation scale the standard normal random variable, using 
         * the player x-coordinate as the mean, creating a normal random variable 
         * centered on the adversary's x-coordinate.
         */
        final var randomX = adversaryPosition.getX() + standardDeviation * standardX;
        // And this is centered on the adversary's y-coordinate
        final var randomY = adversaryPosition.getY() + standardDeviation * standardY;
        final var dX = randomX - currentEntityPosition.getX();
        final var dY = randomY - currentEntityPosition.getY();
        final var angle = Math.toDegrees(Math.atan2(dY, dX));
        return super.findDirectionGivenAngle(angle);
    }

    private boolean isTimeToChoose() {
        return System.currentTimeMillis() - lastChoiceTime > TIME_TO_NEXT_DECISION;
    }

}
