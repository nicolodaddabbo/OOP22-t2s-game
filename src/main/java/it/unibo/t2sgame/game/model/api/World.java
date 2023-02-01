package it.unibo.t2sgame.game.model.api;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.Event;
import it.unibo.t2sgame.game.logics.api.GameMap;

/**
 * Rapresenting the "World" of T2S game.
 * The World is like a "container" of different entities involved in the game.
 */
public interface World {
    /**
     * 
     * @return the wave of the current round 
     */
    Optional<Wave> getCurrentWave();
    /**
     * 
     * @return a list of entities rappresenting the "players" 
     */
    List<Entity> getPlayers();
    /**
     * 
     * @return a list of entities in the world
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
    World addEntity(Entity e);
    
    World addPlayer(Entity e);
    /**
     * Adding a Collection of entities in to the world
     * @param entities the entities to add to world
     */
    World addEntities(Collection<Entity> entities);
    /**
     * Remove an entity from the World
     * @param e Entity to be removed from the world
     */
    World removeEntity(Entity e);
    /**
     * Removing a collection of entities from the world
     * @param entities the entities to remove from the world
     */
    World removeEntities(Collection<Entity> entities);
    /**
     * Add the event to the World event queue
     * @param event occurred
     */
    void notifyEvent(Event event);

    /**
     * Handle all the events in the event queue
     */
    void handleEvents();

    GameMap getMap();
}
