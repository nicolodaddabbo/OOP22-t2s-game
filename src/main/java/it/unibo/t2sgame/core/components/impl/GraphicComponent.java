package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.view.api.Graphic;

public abstract class GraphicComponent extends AbstractComponent{
    protected Graphic graphic;

    @Override
    public void update() {
        
    }

    public void setGraphics(Graphic graphic) {
        this.graphic = graphic;   
    }
    @Override
    public <T> void receive(Message<T> messge) {
        
    }
}
