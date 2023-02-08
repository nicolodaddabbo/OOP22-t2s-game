package it.unibo.t2sgame.view.api;

import java.util.List;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
/**
 * abstract class that represents a GameScene and implements all the common implementation of a GameScene.
 */
public abstract class AbstractGameScene extends AbstractBaseScene implements GameScene {
    /**
     * variable that contains the GameEngine used.
     */
    protected GameEngine gameEngine;
    /**
     * variable that contains the game in which the scene is going to be used.
     */
    protected Game game;
    /**
     * list that contains all the keyboard input controllers in the game.
     */
    protected List<KeyboardInputController> keyInControllers;
    /**
     * variable that contains the Graphic that is going to be used to render the entities.
     */
    protected Graphic graphic;
    /**
     * contructor of an AbstractGameScene.
     * @param window in which the scenes are going to be contained
     */
    protected AbstractGameScene(final Window window) {
        super(window);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setEngine(final GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Game getGame() {
        return this.gameEngine.getGame();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Graphic getGraphic() {
        return this.graphic;
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public void setInputControllers(final List<KeyboardInputController> keyInControllers) {
        this.keyInControllers = keyInControllers;
    }
}
