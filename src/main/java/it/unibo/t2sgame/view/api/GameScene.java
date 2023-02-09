package it.unibo.t2sgame.view.api;

import java.util.List;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
/**
 * Interface representing a GameScene of the game. 
 */
public interface GameScene extends BaseScene {
    /**
     * method that render the GameScene using a Graphic technology.
     */
    void render();
    /**
     * 
     * @param gameEngine
     */
    void setEngine(GameEngine gameEngine);
    /**
     * @return the Graphic architecture used.
     */
    Graphic getGraphic();
    /**
     * setter to receive the list of KeyboardInputControllers of the players.
     * @param keyInControllers the list of all keyboard input controller
     */
    void setInputControllers(List<KeyboardInputController> keyInControllers);
    /**
     * method called when the game is over, this method calls the rendering of a GameOverScene.
     */
    void gameOver();
    /**
     * method called to render FPS.
     * @param fps the game is running on
     */
    void renderFPS(int fps);
}
