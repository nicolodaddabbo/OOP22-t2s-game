package it.unibo.t2sgame.game.ecs.api;

/**
 * This interface models a message container.
 * 
 * @param <T> the type of message
 */
public interface Message<T> {
    /**
     * 
     * @return the message of type T
     */
    T getMessage();
}
