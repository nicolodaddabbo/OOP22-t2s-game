package it.unibo.t2sgame.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physic.api.PhysicComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;

public class EntityImpl implements Entity {

    private List<Component> components = new ArrayList<>();
    private Optional<GraphicComponent> gc = Optional.empty();
    private Optional<InputComponent> ic = Optional.empty();
    private Optional<PhysicComponent> pc = Optional.empty();

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public <T extends Component> Optional<T> getComponent(Class<T> componentClass){
        return this.components.stream()
                .filter(c -> componentClass.isAssignableFrom(c.getClass()))
                .map(componentClass::cast)
                .findFirst();
    }

    @Override
    public Optional<GraphicComponent> getGraphicComponent() {
        return this.gc;
    }

    @Override
    public Optional<InputComponent> getInputComponent() {
        return this.ic;
    }

    @Override
    public Optional<PhysicComponent> getPhysicComponent() {
        return this.pc;
    }

    @Override
    public void notifyComponent() {
        
    }
    
}
