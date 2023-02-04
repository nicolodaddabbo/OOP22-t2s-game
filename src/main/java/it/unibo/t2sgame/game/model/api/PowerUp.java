package it.unibo.t2sgame.game.model.api;

import it.unibo.t2sgame.core.entity.api.Entity;

@FunctionalInterface
public interface PowerUp {
    public void obtain(Entity entity);
}
