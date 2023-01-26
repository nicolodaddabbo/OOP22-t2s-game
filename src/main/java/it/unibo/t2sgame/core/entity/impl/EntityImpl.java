package it.unibo.t2sgame.core.entity.impl;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.model.api.World;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EntityImpl implements Entity {

    private Set<Component> components = new HashSet<>();
    private Optional<World> world = Optional.empty();
    private Vector2D position;

    public EntityImpl(final Vector2D position) {
        this.position = position;
    }

    @Override
    public Set<Component> getComponents() {
        return this.components;
    }

    @Override
    public <T extends Component> Optional<T> getComponent(Class<T> componentClass){
        return this.components.stream()
            .filter(componentClass::isInstance)
            .map(componentClass::cast)
            .findFirst();
    }

    @Override
    public Entity addComponent(Component component) {
        this.components.add(component);
        component.setEntity(this);
        return this;
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Vector2D position) {
        this.position = position;
    }

    @Override
    public Optional<World> getWorld() {
        return this.world;
    }

    @Override
    public void setWorld(World world) {
        this.world = Optional.of(world);        
    }

    @Override
    public <T extends Component, S> void notifyComponent(Class<T> receiver, Message<S> message) {
        this.getComponent(receiver).ifPresent(c -> c.receive(message));
    }
    
    @Override
    public Entity clone(){
        var entity = new EntityImpl(this.position);
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
