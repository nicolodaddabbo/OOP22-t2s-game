package it.unibo.t2sgame.input.impl;

import java.util.Random;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.input.api.AbstractAIInputController;
import it.unibo.t2sgame.input.api.Directions;

public class GaussianAIInputController extends AbstractAIInputController {
    final Random random = new Random();
    private static final long TIME_TO_NEXT_DECISION = 300;
    private static final long STANDARD_DEVIATION = 500;
    private long lastChoiceTime = 0;

    @Override
    protected void computeNextCommand() {
        this.commandsQueue.add(entity -> {
            if (isTimeToChoose()) {
                final var world = entity.getWorld().get();
                var closestPlayer = world.getPlayers().stream()
                        .min((p1, p2) -> Double.compare(distanceBetweenEntities(entity, p1), distanceBetweenEntities(entity, p2)));
                entity.notifyComponent(PhysicsComponent.class,
                        () -> generateGaussianRandomDirection(entity.getPosition(), closestPlayer.get().getPosition(), STANDARD_DEVIATION));
                this.lastChoiceTime = System.currentTimeMillis();
            }
        });
    }

    private double distanceBetweenEntities(final Entity e1, final Entity e2) {
        return e1.getPosition().distance(e2.getPosition());
    }

    private Directions generateGaussianRandomDirection(final Vector2D currentEntityPos, final Vector2D playerPos, final double standardDeviation) {
        // generate 2 random numbers, u1 and u2, from a uniform distribution between 0
        // and 1
        var u1 = this.random.nextDouble();
        var u2 = this.random.nextDouble();
        // this equation generate a STANDARD normally distributed random variable for
        // the x-coordinate
        var standardX = Math.sqrt(-2 * Math.log(u1)) * Math.cos(2 * Math.PI * u2);
        // and this generate it for the y-coordinate
        var standardY = Math.sqrt(-2 * Math.log(u1)) * Math.sin(2 * Math.PI * u2);
        // this equation scale the standard normal random variable, using the player
        // x-coordinate as the mean,
        // creating a normal random variable centered on the player x-coordinate
        var randomX = playerPos.getX() + standardDeviation * standardX;
        // and this is centered on the entity y-coordinate
        var randomY = playerPos.getY() + standardDeviation * standardY;
        var dX = randomX - currentEntityPos.getX();
        var dY = randomY - currentEntityPos.getY();
        var angle = Math.toDegrees(Math.atan2(dY, dX));
        return super.findDirectionGivenAngle(angle);
    }

    private boolean isTimeToChoose() {
        return System.currentTimeMillis() - lastChoiceTime > TIME_TO_NEXT_DECISION;
    }

}
