package it.unibo.t2sgame.core.api;
/**
 * This interface abstracts the concept of a "Game engine". 
 * A game engine is the core of a game software architecture, which allows us
 * to synchronize differents domain of the game, such as Input's handling with the 
 * Physic's handling.
 */
public interface GameEngine {
    /**
     * This method rapresent the game loop of the
     * current GameEngine implementation.
     */
    void run();
}
