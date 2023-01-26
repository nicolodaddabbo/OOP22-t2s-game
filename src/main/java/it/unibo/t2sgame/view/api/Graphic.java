package it.unibo.t2sgame.view.api;
import it.unibo.t2sgame.core.entity.api.Entity;

public interface Graphic {
    
    /**
     * This method draws a specified player Entity
     * @param player 
     */
    void drawPlayer(Entity player);

    /**
     * This method draws a specified projectile Entity
     * @param projectile 
     */
    void drawProjectile(Entity projectile);

    /**
     * This method draws a specified basic enemy Entity
     * @param enemy 
     */
    void drawBaseEnemy(Entity enemy);

}
