package it.unibo.t2sgame.core.components.api;

import it.unibo.t2sgame.core.entity.api.Entity;

public abstract class AbstractComponent implements Component{
    /* The entity linked to the component */
    protected Entity entity;

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;        
    }
    
}
