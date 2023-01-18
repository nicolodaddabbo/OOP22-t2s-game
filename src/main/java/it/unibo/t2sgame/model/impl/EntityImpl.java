package it.unibo.t2sgame.model.impl;
import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physic.api.PhysicComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EntityImpl implements Entity {

    private Set<Component> components = new HashSet<>();
    private Optional<GraphicComponent> graphicComponent = Optional.empty();
    private Optional<InputComponent> inputComponent = Optional.empty();
    private Optional<PhysicComponent> physicComponent = Optional.empty();

    @Override
    public Set<Component> getComponents() {
        return this.components;
    }

    @Override
    public <T extends Component> Optional<Component> getComponent(Class<T> componentClass){
        return this.components.stream()
                .filter(c -> componentClass.isAssignableFrom(c.getClass()))
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
    public Entity addComponent(Component component) {
        this.components.add(component);
        return this;
    }

    @Override
    public void notifyComponent() {
        
    }
    
    @Override
    public Entity clone(){
        var entity = new EntityImpl();
        this.components.forEach(component -> entity.addComponent(component));
        return entity;
    }
    
}
