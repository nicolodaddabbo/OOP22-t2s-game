package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;

/**
 * This class is used to handle all kind of move related commands
 * which differs only in the direction of the movement.
 */
public class Move implements Command {
    private final Directions direction;

    /**
     * @param direction of the movement
     */
    public Move(final Directions direction) {
        this.direction = direction;
    }

    /**
     * Notify the entity's Physic component comunicating the direction of the movement.
     */
    @Override
    public void execute(final Entity entity) {
        entity.notifyComponent(PhysicsComponent.class, () -> this.direction);
    }
}
