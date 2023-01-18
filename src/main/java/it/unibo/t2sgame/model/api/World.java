package it.unibo.t2sgame.model.api;

import java.util.List;

/**
 * Rapresenting the "World" of T2S game.
 * The World is like a "container" of different entities involved in the game.
 */
public interface World {
    /**
     * 
     * @return the wave of the current round 
     */
    Wave getCurrentWave();
    /**
     * 
     * @return an entity rappresenting the "player" 
     */
    List<Entity> getPlayers();
    /**
     * 
     * @return a collection of entities in the world
     */
    List<Entity> getEntities();
    /**
     * Set the next wave
     */
    void setWave(Wave next);

    /**
     * Add an entity to the World
     * @param e Entity to be added to the world
     */
    void addEntity(Entity e);
}
