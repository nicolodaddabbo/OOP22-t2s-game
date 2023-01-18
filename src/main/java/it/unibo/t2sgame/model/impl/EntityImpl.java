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
        this.components.forEach(entity::addComponent);
        return entity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntityImpl other = (EntityImpl) obj;
        if (components == null) {
            if (other.components != null)
                return false;
        } else if (!components.equals(other.components))
            return false;
        return true;
    }
    
}
