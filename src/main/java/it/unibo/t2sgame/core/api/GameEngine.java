package it.unibo.t2sgame.core.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.t2sgame.components.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.GameScene;

/**
 * The core part of Entity-Component-Systems architecture
 * The GameEngine allows to handle and update all the systems
 * which it contains.
 */
public interface GameEngine {
    /**
     * Update all the systems contained in the engine.
     * This method has to be called once per game loop's 
     * cycle
     */
    void update(); 
    /**
     * Get the entities contained in the engine
     * 
     * @return the entities contained in the engine
     */
    List<Entity> getEntities();
    /**
     * Add an entity to the engine.
     * Once added, this entity is automatically associated with its
     * related GameSystems, based on the component that it has.
     * 
     * @param e the entity to be added to the engine
     * 
     * @return this
     */
    GameEngine addEntity(Entity e);
    /**
     * Remove the entity from the engine.
     * Once removed, this entity is automatically removed from its
     * related GameSystems.
     * @param e
     * @return
     */
    GameEngine removeEntity(Entity e);
    /**
     * Get the GameSystems added to the engine
     * 
     * @return the GameSystems added to the engine
     */
    Set<GameSystem> getSystems();
    /**
     * Get the GameSystem based on the type of component which it 
     * contains.
     * 
     * @param <T> The type of the component 
     * @param clazz the class instance of the component
     * 
     * @return an Optional containing the GameSystem if present, 
     * otherwise an empty Optional
     */
    <T extends Component> Optional<GameSystem> getSystem(Class<T> clazz); 
    /**
     * Set the scene to be rendered by the Graphics System
     * @param scene
     */
    void setScene(GameScene scene);    
}
