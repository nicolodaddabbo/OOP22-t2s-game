package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physics.api.PhysicsComponent;

public class Stop implements Command {

    @Override
    public void execute(final Entity entity) {
        entity.notifyComponent(PhysicsComponent.class, () -> this);
    }
    
}
