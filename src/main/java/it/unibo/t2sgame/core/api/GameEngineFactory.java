package it.unibo.t2sgame.core.api;
/**
 * A GameEngine Factory to produce GameEngine instance.
 */
public interface GameEngineFactory {
    /**
     * 
     * @return an GameEngine which doesn't care about fps locking.
     * This is usefull for players who are playing from desktop and 
     * don't care about battery consumption and cpu/gpu's usage
     * 
     */
    GameEngine createEngine();
    /**
     * 
     * @return an GameEngine which care about fps locking.
     * This is useful for players who are playing from laptops and 
     * care about battery consumption and cpu/gpu's usage
     */
    GameEngine createGameEngineWithFpsLock();
}
