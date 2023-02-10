package it.unibo.t2sgame.game.ecs.api;

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

}
