package it.unibo.t2sgame.model.api;

/**
 * This interface represents the components of the entities.
 */
public interface Component {
    
    /**
     * This method updates the domain of the component of specified entity.
     * @param entity 
     */
    void update(Entity entity);

    /**
     * 
     */
    void receive();

}