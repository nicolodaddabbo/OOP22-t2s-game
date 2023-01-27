package it.unibo.t2sgame.game.model.api;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.input.api.Directions;

/**
 * An Entity factory to produce Entity isntances.
 */
public interface EntityFactory {
    /**
     * 
     * @param position the starting position
     * @return Entity instance representing the "Player"
     */
    Entity createPlayer(Vector2D position);
    /**
     * 
     * @param position the starting position
     * @param direction the starting direction
     * @return Entity instance representing the "Projectile"
     */
    Entity createProjectile(Vector2D position, Directions direction);
    /**
     * 
     * @param position the starting position
     * @return Entity instance representing a basic "Enemy"
     */
    Entity createBaseEnemy(Vector2D position);
    /**
     * 
     * @param position the position to be placed (the position will be the center of the wall)
     * @param width the wall width
     * @param height the wall height
     * @return Entity instance representing a "Wall"
     */
    Entity createWall(Vector2D position, double width, double height);
    
}
