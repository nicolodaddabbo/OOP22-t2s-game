package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;

public class Shoot implements Command {
    private final Directions direction;

    public Shoot(final Directions direction) {
        this.direction = direction;
    }

    @Override
    public void execute(final Entity entity) {
        entity.notifyComponent(ShootComponent.class, () -> this.direction);
    }
}
