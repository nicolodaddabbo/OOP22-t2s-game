package it.unibo.t2sgame.view.api;

import java.util.List;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
/**
 * abstract class that represents a GameScene and implements all the common implementation of a GameScene.
 */
public abstract class AbstractGameScene extends AbstractBaseScene implements GameScene {
    private GameEngine gameEngine;
    private List<KeyboardInputController> keyInControllers;
    private Graphic graphic;
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
    /**
     * getter of the GameEngine.
     * @return the game engine
     */
    public GameEngine getGameEngine() {
        return gameEngine;
    }
    /**
     * getter of the list of KeyboardInputController.
     * @return the list of KeyboardInputController
     */
    public List<KeyboardInputController> getKeyInControllers() {
        return keyInControllers;
    }
    /**
     * setter for the graphics.
     * @param graphic to be set.
     */
    public void setGraphic(final Graphic graphic) {
        this.graphic = graphic;
    }
}
