package it.unibo.t2sgame.core.systems.api;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.entity.api.Entity;

public abstract class AbstractGameSystem implements GameSystem{

    private  Set<Component> components = new HashSet<>(); 

    @Override
    public GameSystem addComponent(Optional<? extends Component> component) {
        component.ifPresent(this.components::add);
        return this;
    }

    @Override
    public <T extends Component> boolean checkType(Class<T> clazz) {
        return this.getType().equals(clazz);
    }

    @Override
    abstract public Class<? extends Component> getType();

    @Override
    public boolean isMember(Entity e) {
        return e.getComponent(this.getType()).isPresent();
    }


    @Override
    public GameSystem removeComponent(Optional<? extends Component> component) {
        component.ifPresent(this.components::remove);
        return this;
    }

    @Override
    public void update() {
        this.components.forEach(Component::update);        
    }
    
}
