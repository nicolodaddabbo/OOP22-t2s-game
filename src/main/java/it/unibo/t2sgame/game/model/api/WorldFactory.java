package it.unibo.t2sgame.game.model.api;

/**
 * This interface models a factory of World.
 * See {@link World} to know what is a World.
 */
public interface WorldFactory {
    /**
     * 
     * @return a World implementation with only one player
     */
    World createWorldWithOnePlayer();

    /**
     * 
     * @return a World implementation with one player and one companion
     */
    World createWorldWithPlayerAndCompanion();

}
