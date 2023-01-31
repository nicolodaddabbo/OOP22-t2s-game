package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.view.api.Graphic;

public abstract class GraphicComponent extends AbstractComponent{
    protected Graphic graphic;
    protected final double width, height;
    
    protected GraphicComponent(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {
        
    }

    public void setGraphics(Graphic graphic) {
        this.graphic = graphic;   
    }
    @Override
    public <T> void receive(Message<T> messge) {
        
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
