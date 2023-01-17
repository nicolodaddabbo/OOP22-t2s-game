package it.unibo.t2sgame.model.api;
/**
 * Rapresenting the "World" of T2S game.
 * The World is like a "container" of different entities involved in the game.
 */
public interface World {
    /**
     * 
     * @return the wave of the current round 
     */
    Wave getCurrentWave();
    /**
     * 
     * @return an entity rappresenting the "player" 
     */
    Entity getPlayer();
}
