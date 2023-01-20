package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physics.api.PhysicsComponent;

public class MoveLeft implements Command {

    @Override
    public void execute(Entity entity) {
        entity.notifyComponent(PhysicsComponent.class, () -> this);
    }
    
}
