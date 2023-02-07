package it.unibo.t2sgame.game.model.api;

import java.util.List;

import it.unibo.t2sgame.core.entity.api.Entity;

/**
 * interface that represents the concept of a Wave of enemies.
 */
public interface Wave {
    /**
     * 
     * @return a list of Entity representing a Wave
     */
    List<Entity> getEnemies();

    /**
     * 
     * @return the id of the wave
     */
    int getWaveID();
    /**
     * @param entity to add in the wave
     * @return the wave with the new entity
     */
    Wave addEnemy(Entity entity);
}
