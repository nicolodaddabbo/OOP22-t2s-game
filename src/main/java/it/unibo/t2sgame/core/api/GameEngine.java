package it.unibo.t2sgame.core.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.GameScene;

public interface GameEngine {
    
    void update(); 
    
    List<Entity> getEntities();

    GameEngine addEntity(Entity e);

    GameEngine removeEntity(Entity e);

    Set<GameSystem> getSystems();

    <T extends Component> Optional<GameSystem> getSystem(Class<T> clazz); 

    void setScene(GameScene scene);    
}
