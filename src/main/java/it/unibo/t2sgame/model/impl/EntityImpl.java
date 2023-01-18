package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physic.api.PhysicComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityImpl implements Entity {

    private List<Component> components = new ArrayList<>();
    private Optional<GraphicComponent> graphicComponent = Optional.empty();
    private Optional<InputComponent> inputComponent = Optional.empty();
    private Optional<PhysicComponent> physicComponent = Optional.empty();

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
        return this.graphicComponent;
    }

    @Override
    public Optional<InputComponent> getInputComponent() {
        return this.inputComponent;
    }

    @Override
    public Optional<PhysicComponent> getPhysicComponent() {
        return this.physicComponent;
    }

    @Override
    public void setGraphicComponent(GraphicComponent graphicComponent) {
        this.graphicComponent = Optional.of(graphicComponent);
    }

    @Override
    public void setInputComponent(InputComponent inputComponent) {
        this.inputComponent = Optional.of(inputComponent);
    }

    @Override
    public void setPhysicComponent(PhysicComponent physicComponent) {
        this.physicComponent = Optional.of(physicComponent);
    }

    @Override
    public void notifyComponent() {
        
    }
    
}
