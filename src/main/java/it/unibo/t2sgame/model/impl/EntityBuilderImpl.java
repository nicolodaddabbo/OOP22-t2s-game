package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.EntityBuilder;
import it.unibo.t2sgame.physic.api.PhysicComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;

import java.util.Optional;

public class EntityBuilderImpl implements EntityBuilder {

    private Optional<GraphicComponent> graphicComponent = Optional.empty();
    private Optional<InputComponent> inputComponent = Optional.empty();
    private Optional<PhysicComponent> physicComponent = Optional.empty();

    @Override
    public EntityBuilder physic(PhysicComponent physicComponent) {
        this.physicComponent = Optional.of(physicComponent);
        return this;
    }

    @Override
    public EntityBuilder input(InputComponent inputComponent) {
        this.inputComponent = Optional.of(inputComponent);
        return this;
    }

    @Override
    public EntityBuilder graphic(GraphicComponent graphicComponent) {
        this.graphicComponent = Optional.of(graphicComponent);
        return this;
    }

    @Override
    public Entity build() {
        var entity = new EntityImpl();
        if(this.graphicComponent.isPresent()){
            entity.setGraphicComponent(this.graphicComponent.get());
        }
        if(this.inputComponent.isPresent()){
            entity.setInputComponent(this.inputComponent.get());
        }
        if(this.physicComponent.isPresent()){
            entity.setPhysicComponent(this.physicComponent.get());
        }
        return entity;
    }
    
}
