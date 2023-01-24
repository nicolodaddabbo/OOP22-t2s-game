package it.unibo.t2sgame.core.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.t2sgame.core.api.GameSystem;
import it.unibo.t2sgame.core.api.GameSystemFactory;
import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physics.api.CollisionComponent;
import it.unibo.t2sgame.physics.api.PhysicsComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;

public class GameSystemFactoryImpl implements GameSystemFactory{

    private <T extends Component> GameSystem sytemWith(Class<T> clazz){
        return new GameSystem() {

            private final Set<Entity> entities = new HashSet<>();
            private final Set<Component> components = new HashSet<>();
            
            @Override
            public void update() {
                this.components.forEach(c -> c.update());
            }

            @Override
            public boolean isMember(Entity e) {
                return e.getComponent(clazz).isPresent();
            }

            @Override
            public <S extends Component> boolean checkType(Class<S> type) {
                return clazz.equals(type);
            }

            @Override
            public Class<? extends Component> getType() {
                return clazz;
            }
            
            @Override
            public GameSystem addEntity(Entity entity){
                assert this.isMember(entity);
                this.entities.add(entity);
                this.addComponent(entity.getComponent(clazz).get());
                return this;
            }

            private void addComponent(Component component) {
                this.components.add(component);
            }
            
        };
    }

    @Override
    public GameSystem craeteInputSystem() {
        return sytemWith(InputComponent.class);
    }

    @Override
    public GameSystem createPhysicsSystem() {
        return sytemWith(PhysicsComponent.class);
    }

    @Override
    public GameSystem createCollisionSystem() {
        return sytemWith(CollisionComponent.class);
    }

    @Override
    public GameSystem createGraphicsSystem() {
        return sytemWith(GraphicComponent.class);
    }
    
}
