package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.view.api.Graphic;

public class GraphicComponent extends AbstractComponent{
    protected Graphic graphic;
    protected Entity entity;

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    public void setGraphics(Graphic graphic) {
        this.graphic = graphic;   
    }
    @Override
    public <T> void receive(Message<T> messge) {
        
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;     
    }
}
