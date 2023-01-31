package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;

/**
 * This class is used to handle all kind of shooting related commands
 * which differs only in the direction of the projectile
 */
public class Shoot implements Command {
    private final Directions direction;

    public Shoot(final Directions direction) {
        this.direction = direction;
    }

    /**
     * Notify the entity's Shoot component comunicating the direction of the projectile
     */
    @Override
    public void execute(final Entity entity) {
        entity.notifyComponent(ShootComponent.class, () -> this.direction);
    }
}
