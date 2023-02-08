package it.unibo.t2sgame.view.api;

/**
 * Functional interface that represent the launcher of our application.
 */
@FunctionalInterface
public interface T2SLauncher {
    /**
     * Method that implements the appropriate instruction to launch the game.
     */
    void launch();
}
