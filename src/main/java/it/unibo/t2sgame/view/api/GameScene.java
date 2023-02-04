package it.unibo.t2sgame.view.api;

import java.util.List;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
/**
 * Interface representing a GameScene of the game 
 */
public interface GameScene extends BaseScene{
    /**
     * method that render the GameScene using a Graphic technology
     */
    void render();
    /**
     * 
     * @param gameEngine
     */
    void setEngine(GameEngine gameEngine);
    /**
     * @return the game in which the GameScene was created 
     */
    Game getGame();
    /**
     * @return the Graphic architecture used
     */
    Graphic getGraphic();
    /**
     * setter to receive the list of KeyboardInputControllers of the players
     */
    void setInputControllers(List<KeyboardInputController> keyInControllers);
    /**
     * method called when the game is over, this method calls the rendering of a GameOverScene
     */
    void gameOver();
}
