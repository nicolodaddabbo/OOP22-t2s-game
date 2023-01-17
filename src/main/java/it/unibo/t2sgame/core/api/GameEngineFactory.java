package it.unibo.t2sgame.core.api;
/**
 * A GameEngine Factory to produce GameEngine instance.
 */
public interface GameEngineFactory {
    /**
     * 
     * @return an instance of GameEngine interface. The instance's sub-type depends
     * on the current GameEngineFactory's implementation.
     */
    GameEngine createEngine();
}
