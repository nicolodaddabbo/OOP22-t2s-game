package it.unibo.t2sgame.game.model.api;

import java.util.List;

import it.unibo.t2sgame.core.entity.api.Entity;

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
}
