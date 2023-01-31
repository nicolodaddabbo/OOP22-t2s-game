package it.unibo.t2sgame.view.api;
import it.unibo.t2sgame.core.entity.api.Entity;

public interface Graphic {
    
    void drawFromSprite(Entity entity, String spriteName);

    void drawRectangle(Entity entity);

    void drawCircle(Entity entity);
}
