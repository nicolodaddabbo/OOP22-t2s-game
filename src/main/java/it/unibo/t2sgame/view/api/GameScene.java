package it.unibo.t2sgame.view.api;

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
     * 
     * @param game
     */
    void setGame(Game game);

    Graphic getGraphic();

    void setInputController(KeyboardInputController keyInController);
}
