package it.unibo.t2sgame.model.api;

/**
 * An Entity factory to produce Entity isntances.
 */
public interface EntityFactory {
    /**
     * 
     * @return Entity instance representing the "Player"
     */
    Entity createPlayer();
    /**
     * 
     * @return Entity instance representing the "Projectile"
     */
    Entity createProjectile();

    /**
     * 
     * @return Entity instance representing a basic "Enemy"
     */
    Entity createBaseEnemy();
    
}
