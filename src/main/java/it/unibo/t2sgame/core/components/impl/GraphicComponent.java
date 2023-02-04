package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.view.api.Graphic;
/**
 * this abstract class represents the GraphicComponent of the entity
 */
public abstract class GraphicComponent extends AbstractComponent{
    protected Graphic graphic;
    protected final double width;
    protected final double height;
    /**
     * @param width the width of the GraphicComponent
     * @param height the height of the GraphicComponent
     */
    protected GraphicComponent(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {
        
    }
    /**
     * setter for the Graphic architecture used
     * @param graphic the graphic architecture used
     */
    public void setGraphics(Graphic graphic) {
        this.graphic = graphic;   
    }

    @Override
    public <T> void receive(Message<T> messge) {
        
    }
    /**
     * @return the width of the GraphicComponent
     */
    public double getWidth() {
        return width;
    }
    /**
     * @return the height of the GraphicComponent
     */
    public double getHeight() {
        return height;
    }
}
