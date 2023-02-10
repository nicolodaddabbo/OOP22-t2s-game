package it.unibo.t2sgame.game.ecs.impl;

import it.unibo.t2sgame.game.ecs.api.Component;
import it.unibo.t2sgame.game.ecs.api.Entity;

/**
 * This abstract class factorized the common code between all components
 * subtypes.
 * In particular, get/set entity has been factorized.
 */
public abstract class AbstractComponent implements Component {
    private Entity entity;

    /**
     * {@inheritDoc}
     */
    protected Entity getEntity() {
        return this.entity;
    }

    /**
     * {@inheritDoc}
     */
    protected void setEntity(final Entity entity) {
        this.entity = entity;
    }

}
