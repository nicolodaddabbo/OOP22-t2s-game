package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physics.api.PhysicsComponent;

public class Move implements Command {
    private final Directions direction;

    public Move(final Directions direction) {
        this.direction = direction;
    }

    @Override
    public void execute(final Entity entity) {
        entity.notifyComponent(PhysicsComponent.class, () -> this.direction);
    }
}
