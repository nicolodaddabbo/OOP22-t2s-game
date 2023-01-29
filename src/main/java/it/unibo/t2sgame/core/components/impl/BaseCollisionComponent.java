package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.entity.api.Entity;

public class BaseCollisionComponent extends CollisionComponent {

    protected BaseCollisionComponent(Shape shape, boolean isRigid) {
        super(shape, isRigid);
    }
    
}
