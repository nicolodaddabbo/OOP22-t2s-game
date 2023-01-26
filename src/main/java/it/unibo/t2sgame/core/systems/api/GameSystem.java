package it.unibo.t2sgame.core.systems.api;

import java.util.Optional;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.entity.api.Entity;
/**
 * A GameSystem represent the "System" in a Entity-Component-System 
 * architecture.
 * A GameSystem is a collector of the same domain field components.
 * For example, the PhysicsSystem contains and update only PhysicsComponents.
 */
public interface GameSystem {
    /**
     * Updating the current system.
     * The number of update's calls depends by 
     * the GameStates type.
     */
    void update();
    /**
     * Check if the entity has a component related to the current system
     * 
     * @param e entity to be checked
     * 
     * @return true if the entity has a component related to the current system,
     * otherwise false
     */
    boolean isMember(Entity e);
    /**
     * Check if the type passed match with the GameSystem's domain.
     *  
     * @param <T> the type of component contained in the system
     * @param clazz 
     * 
     * @return true if the type match with the components contained in the systems,
     * otherwise false
     */
    <T extends Component> boolean checkType(Class<T> clazz);
    /**
     * 
     *@return the Class type of components contained in the GameSystem.
     */
     Class<? extends Component> getType();
    /**
     * Add the entity to the current system.
     * The domain component related to the GameSystem is
     * automatically added to the GameSystem component's list
     * 
     * @param entity the entity to addto the GameSystem
     * 
     * @return this
     */
     GameSystem addComponent(Optional<? extends Component> component);
    /**
     * Remove the entity to the current system.
     * The domain componnet related to the GameSystem is 
     * automatically removed to the GameSystem component's list
     * 
     * @param entity the entity to remove from the GameSystem
     * @return
     */
    GameSystem removeComponent(Optional<? extends Component> component);
}
