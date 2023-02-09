package it.unibo.t2sgame.core.components.api;

import it.unibo.t2sgame.core.entity.api.Entity;

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
    @Override
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }

}
