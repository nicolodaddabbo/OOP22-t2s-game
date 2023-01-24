package it.unibo.t2sgame.view.impl;

import java.util.function.BiConsumer;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Message;
import it.unibo.t2sgame.view.api.Graphic;
import it.unibo.t2sgame.view.api.GraphicComponent;
import it.unibo.t2sgame.view.api.GraphicComponentFactory;

public class GraphicComponentFactoryImpl implements GraphicComponentFactory{

    private GraphicComponent fromGraphicFunction(BiConsumer<Graphic, Entity> graphConsumer){
        return new GraphicComponent() {
            private Graphic graphic;
            private Entity entity;
            @Override
            public void update() {
                graphConsumer.accept(this.graphic, entity);
            }
            @Override
            public void setGraphics(Graphic graphic) {
                this.graphic = graphic;   
            }
            @Override
            public <T> void receive(Message<T> messge) {
                
            }
            @Override
            public Entity getEntity() {
                return this.entity;
            }
            @Override
            public void setEntity(Entity entity) {
                this.entity = entity;     
            }
        };
    }

    @Override
    public GraphicComponent getPlayerGraphicComponent() {    
        return fromGraphicFunction((graphic, entity) -> graphic.drawPlayer(entity));
    }

    @Override
    public GraphicComponent getProjectileGraphicComponent() {
        return fromGraphicFunction((graphic, entity) -> graphic.drawProjectile(entity));
    }

    @Override
    public GraphicComponent getBaseEnemyGraphicComponent() {
        return fromGraphicFunction((graphic, entity) -> graphic.drawBaseEnemy(entity));
    }
    
}
