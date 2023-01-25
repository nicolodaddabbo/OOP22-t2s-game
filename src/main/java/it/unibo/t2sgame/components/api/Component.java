package it.unibo.t2sgame.model.api;

/**
 * This interface represents the components of the entities.
 */
public interface Component {
    
    /**
     * This method updates the domain of the component of specified entity.
     */
    void update();
    /**
     * 
     * @param <T> the type of message
     * @param message the message to receive
     */
    <T> void receive(Message<T> message);
    /**
     * 
     * @param entity
     * @return
     */
    Entity getEntity();
    /**
     * Adding the related entity to the component
     * @param entity
     */
    void setEntity(Entity entity);

}
