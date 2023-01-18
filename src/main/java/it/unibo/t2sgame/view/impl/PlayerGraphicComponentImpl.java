package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.Graphic;
import it.unibo.t2sgame.view.api.GraphicComponent;

public class PlayerGraphicComponentImpl implements GraphicComponent{

    private Graphic gr;
    @Override
    public void update(Entity entity) {
        this.gr.drawPlayer(entity);
    }

    @Override
    public void setGraphics(Graphic graphic) {
        this.gr = graphic;
    }
    
    @Override
    public void receive() {
        
    }

}
