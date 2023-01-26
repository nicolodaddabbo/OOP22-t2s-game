package it.unibo.t2sgame.core.systems.api;

/**
 * A factory to create GameSystem instance.
 */
public interface GameSystemFactory {
    /**
     * 
     * @return a GameSystem whose component covers the input domain
     */
    GameSystem craeteInputSystem();
    /**

     * @return a GameSystem whose component covers the physics domain
     */
    GameSystem createPhysicsSystem();
    /**
     * 
     * @return a GameSystem whose component covers the collision domain
     */
    GameSystem createCollisionSystem();
    /**
     * 
     * @return a GameSystem whose component covers the graphics domain
     */
    GameSystem createGraphicsSystem();

}
