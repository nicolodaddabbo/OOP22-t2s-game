package it.unibo.t2sgame.view.api;
import it.unibo.t2sgame.core.entity.api.Entity;

/**
 * Interface that indicates how an Entity is drawn
 */
public interface Graphic {
    /**
     * method used to draw an enity that has a sprite
     * @param entity the entity to draw
     * @param spriteName the key of the sprite of the entity
     */
    void drawFromSprite(Entity entity, String spriteName);
    /**
     * method used to draw a rectangular entity
     * @param entity the entity to draw
     */
    void drawRectangle(Entity entity);
    /**
     * method used to draw a circular entity
     * @param entity the entity to draw
     */
    void drawCircle(Entity entity);
}
