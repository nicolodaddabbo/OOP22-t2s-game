package it.unibo.t2sgame.model.api;
import it.unibo.t2sgame.common.Vector2D;

import java.util.Optional;
import java.util.Set;

/**
 * This interface represents the entities of the game.
 */
public interface Entity {
    /**
     * 
     * @return list of components of the entity
     */
    Set<Component> getComponents();
    /**
     * 
     * @return Optional containing the specified Component if present,
     * otherwise returns an empty Optional
     */
    <T extends Component> Optional<Component> getComponent(Class<T> componentClass);
    /**
     * This method is used to add components to the entity.
     * @param component adds specified component to the set of components of the entity
     */
    Entity addComponent(Component component);
    /**
     * 
     * @return the entity position
     */
    Vector2D getPosition();
    /**
     * Sets the entity position to the given position.
     * @param position the position to set
     */
    void setPosition(Vector2D position);
    /**
     * 
     * @param world the world where the entity is placed
     */
    Optional<World> getWorld();
    /**
     * 
     * @param world the world where the entity is placed
     */
    void setWorld(World world);
    /**
     * This method is used to notify the components of the entity.
     * @param <T> T is the type of the receiver 
     * @param <S> S is the type of the message 
     * @param receiver the component class that will receive the message
     * @param message the message to send
     */
    <T extends Component, S> void  notifyComponent(Class<T> receiver, Message<S> message);
    /**
     * Method used to apply the Prototype pattern
     * @return an exact copy of the Entity
     */
    Entity clone();
}
