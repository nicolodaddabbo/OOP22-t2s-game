package it.unibo.t2sgame.core.engine.impl;

import it.unibo.t2sgame.core.engine.api.GameEngine;

/**
 * This interface models the concept of "game loop" body.
 * The game loop has three main blocks:
 * - Input handling.
 * - Update the game time and status based on the input.
 * - Render what updated.
 */
interface GameLoop {

    /**
     * Processes all the InputComponents in the game.
     * This method should be called once during the game loop cycle
     */
    void processInput();

    /**
     * Updates the game time and statud based on the input received.
     * If you have chosen the {@link SynchronizedGameLoop} decorator class, this
     * method
     * will be call >= 1 times.
     * The number of calls depends from the gap between "game time" and "real time"
     * in terms of lag.
     */
    void updateGame();

    /**
     * Renders the scene.
     * This method could left the thread in a sleeping mode if the
     * {@link FpsLockGameLoop} decorator class has been choosen.
     */
    void render();

    /**
     * 
     * @return the engine where the game loop operates.
     */
    GameEngine getEngine();
}
