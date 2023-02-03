package it.unibo.t2sgame.view.api;

import java.util.List;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;

public abstract class AbstractGameScene extends AbstractBaseScene implements GameScene{

    protected GameEngine gameEngine;
    protected Game game;
    protected List<KeyboardInputController> keyInControllers;
    protected Graphic graphic;

    protected AbstractGameScene(Window window) {
        super(window);
    }

    @Override
    public void setEngine(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public Game getGame(){
        return this.gameEngine.getGame();
    }

    @Override
    public Graphic getGraphic(){
        return this.graphic;
    }

    @Override
    public void setInputControllers(List<KeyboardInputController> keyInControllers){
        this.keyInControllers = keyInControllers;
    }
}
