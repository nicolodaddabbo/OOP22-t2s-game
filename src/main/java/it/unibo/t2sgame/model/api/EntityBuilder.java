package it.unibo.t2sgame.model.api;

/**
 * An entity builder to build an Entity instance
 */
public interface EntityBuilder {
    /**
     * 
     */
    void physic();
    /**
     * 
     */
    void input();
    /**
     * 
     */
    void graphic();
    /**
     * 
     * @return the built entity
     */
    Entity build();

}
