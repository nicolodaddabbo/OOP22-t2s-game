package it.unibo.t2sgame.core.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.t2sgame.components.api.CollisionComponent;
import it.unibo.t2sgame.components.api.Component;
import it.unibo.t2sgame.components.api.GraphicComponent;
import it.unibo.t2sgame.components.api.InputComponent;
import it.unibo.t2sgame.components.api.PhysicsComponent;
import it.unibo.t2sgame.core.api.GameSystem;
import it.unibo.t2sgame.core.api.GameSystemFactory;
import it.unibo.t2sgame.model.api.Entity;

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
                this.entities.add(entity);
                /**
                 * Automatically add the component to the GameSystem
                 */
                this.addComponent(entity.getComponent(this.getType()).orElseThrow());
                return this;
            }

            @Override
            public GameSystem removeEntity(Entity entity) {
                this.entities.remove(entity);
                /**
                 * Automatically remove the component from the GameSystem
                 */
                this.removeComponent(entity.getComponent(this.getType()).orElseThrow());
                return this;
            }

            private void addComponent(Component component) {
                this.components.add(component);
            }
            
            private void removeComponent(Component component){
                this.components.remove(component);
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
