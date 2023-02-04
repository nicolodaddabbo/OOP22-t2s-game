package it.unibo.t2sgame.core.engine.api;

/**
 * This abstract class is usefull for Decorator pattern's implementation.
 */
public abstract class GameLoopDecorator implements GameLoop {
    /**
     * The base GameLoop which has been decorated
     */
    private final GameLoop decorated;
    
    protected GameLoopDecorator(GameLoop decorated) {
        this.decorated = decorated;
    }

    @Override
    public void processInput() {
        this.decorated.processInput();
    }

    @Override
    public void render() {
        this.decorated.render();
    }

    @Override
    public void updateGame() {
        this.decorated.updateGame();
    }

    @Override
    public GameEngine getEngine() {
        return this.decorated.getEngine();
    }

}
