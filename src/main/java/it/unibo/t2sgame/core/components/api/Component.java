package it.unibo.t2sgame.core.components.api;

import it.unibo.t2sgame.core.entity.api.Entity;

/**
 * This interface represents the component's contract of a entity.
 * A Component hold a particular aspect, or domain, of the entity which it
 * belongs.
 * This class helps to decouple all the multiple domains of a Entity from the
 * Entity it self, delegating
 * to the respective component to update its domain.
 */
public interface Component {

    /**
     * This method updates the domain of the component of specified entity.
     */
    void update();

    /**
     * Receive a message packet.
     * @param <T>     the type of message
     * @param message the message to receive
     */
    <T> void receive(Message<T> message);

    /**
     * 
     * @return the Entity linked to the component.
     */
    Entity getEntity();

    /**
     * Link the {@link entity} to the component
     * 
     * @param entity the entity to be linked with the component
     */
    void setEntity(Entity entity);

}
