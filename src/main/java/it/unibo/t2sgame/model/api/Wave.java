package it.unibo.t2sgame.model.api;

import java.util.List;

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
