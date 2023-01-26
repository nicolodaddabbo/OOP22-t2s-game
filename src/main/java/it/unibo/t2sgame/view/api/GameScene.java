package it.unibo.t2sgame.view.api;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
/**
 * Interface representing a GameScene of the game 
 */
public interface GameScene {
    /**
     * method that render the GameScene using a Graphic technology
     */
    void render();
    /**
     * method that initialize the GameScene
     */
    void initialize();
    /**
     * 
     * @param game representing the game that is going to be played and rendered
     */
    void setGame(Game game);
    /**
     * 
     * @param keyInController representing the input controller that uses the key clicked by the 
     *                        user on a keyboard
     */
    void setKeyboardInputController(KeyboardInputController keyInController);

    /**
     * 
     * @param gameEngine
     */
    void setEngine(GameEngine gameEngine);
}
