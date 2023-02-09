package it.unibo.t2sgame.core.components.api;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;

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
        return new EntityImpl(this.entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntity(final Entity entity) {
        this.entity = new EntityImpl(entity);
    }

}
