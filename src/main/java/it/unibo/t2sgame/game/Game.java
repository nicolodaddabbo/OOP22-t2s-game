package it.unibo.t2sgame.game;

import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.view.api.GameScene;
/**
 * This interface abstracts the concepts of "Game" ("Partita" in italian)
 */
public interface Game {
    /**
     * 
     * @return the state of the current game
     */
    State getState();
    /**
     * 
     * @return the world of the current game
     */
    World getWorld();
    /**
     * Init the game scene 
     */
    void initSettings();
    /**
     * Init the games logic and entities
     */
    void initGame();
    /**
     * 
     * @return true if game status determs the end of game, otherwise false
     */
    boolean isOver();
    /**
     * Generating a new wave 
     */
    void nextWave();
    /**
     * Start the game 
     */
    void start();
    /**
     * Setting the scene of the game
     */
    void setScene(GameScene scene);
}
