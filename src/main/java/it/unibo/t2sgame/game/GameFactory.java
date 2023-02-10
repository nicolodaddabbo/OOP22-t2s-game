package it.unibo.t2sgame.game;

/**
 * A factory to create Game instance.
 */
public interface GameFactory {
    /**
     * 
     * @return a new game based on SinglePlayer Mode
     */
    Game createSinglePlayerGame();
    /**
     * 
     * @return a new game based on MultiPlayer mode
     */
    Game createWithCompanion();
}
