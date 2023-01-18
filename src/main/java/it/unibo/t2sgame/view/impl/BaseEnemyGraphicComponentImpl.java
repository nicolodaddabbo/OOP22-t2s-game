package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.Graphic;
import it.unibo.t2sgame.view.api.GraphicComponent;

public class BaseEnemyGraphicComponentImpl implements GraphicComponent{

    private Graphic graphic;
    @Override
    public void update(Entity entity) {
        graphic.drawBaseEnemy(entity);        
    }

    @Override
    public void setGraphics(Graphic graphic) {
        this.graphic = graphic;
    }

    @Override
    public void receive() {
        
    }
}
