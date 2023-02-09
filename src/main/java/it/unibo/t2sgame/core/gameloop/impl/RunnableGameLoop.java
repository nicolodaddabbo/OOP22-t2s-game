package it.unibo.t2sgame.core.gameloop.impl;

import java.util.Optional;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.gameloop.api.GameLoop;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.view.api.GameScene;

/**
 * This class models an Runnable's implementation.
 * The game loop logics and body is contained in the run method, decouplig the
 * domain of the game loop from the engine class.
 */
public class RunnableGameLoop implements Runnable {
    /* The engine which calls the runnable */
    private final GameEngine engine;
    /* The object delegated to process the various part of the game loop cycle */
    private final GameLoop gameLoop;

    /**
     * Create a runnable which runs a Game loop.
     * @param engine the engine which creates this runnable
     */
    public RunnableGameLoop(final GameEngine engine) {
        this.engine = engine;
        this.gameLoop = new FrequencyCounterGameLoop(
            // Locking the frequency 
            new FrequencyLockedGameLoop(
            // Synchronze the gameloop
            new SynchronizedGameLoop(
            // Use a concurrent updater when updating components.
            new ConcurrentGameLoop(engine))));
    }

    /**
     * Running the game loop until:
     * - The game logics determs the end of the game.
     * - The runnnig status of the engine is on stop.
     */
    @Override
    public void run() {
        while (!this.isStopped()) {
            this.gameLoop.processInput();
            this.gameLoop.updateGame();
            this.gameLoop.render();
        }
        // Rendering the game over
        this.getScene().ifPresent(GameScene::gameOver);
    }

    private boolean isStopped() {
        return this.getGame().isOver() || !this.engine.isRunning();
    }

    private Game getGame() {
        return this.engine.getGame();
    }

    private Optional<GameScene> getScene() {
        return this.engine.getScene();
    }

}
