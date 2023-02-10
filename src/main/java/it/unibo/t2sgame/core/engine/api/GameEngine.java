package it.unibo.t2sgame.core.engine.api;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.ecs.api.Component;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;

/**
 * This interface models a GameEngine.
 * A GameEngine is the core of a game.
 * As an "engine", this class coordinates the updating of the game and entities
 * with the
 * updating of the view.
 */
public interface GameEngine {
    /**
     * Launch the current instance of the engine by starting a game loop.
     * The loop will continue until either the state and logics of the hosted game
     * determs the end or {@link stop} method is called.
     * The game loop will run on the engine thread.
     */
    void run();

    /**
     * Stop the game loop which was running.
     * The thread where the game loop was running will interrupt.
     */
    void stop();

    /**
     * 
     * @return true if the engine is running, false otherwise.
     */
    boolean isRunning();

    /**
     * 
     * @return the game which is hosted in the engine.
     */
    Game getGame();

    /**
     * 
     * @return an Optional containing the GameScene if present, otherwise returns.
     *         an empty Optional.
     */
    Optional<GameScene> getScene();

    /**
     * Set the the GameScene which is delegated to render by the engine.
     * 
     * @param scene the GameScene which is delegated to render by the engine.
     */
    void setScene(GameScene scene);

    /**
     * 
     * @param <T>   The type of the class.
     * @param clazz The Class object of {@link T} type.
     * @return a group of component with type {@link clazz} present in the engine.
     */
    <T extends Component> List<T> getComponents(Class<T> clazz);

    /**
     * Update the all graphics components using {@link g} to draw the entities.
     * 
     * @param g The graphics instance which knows how to represents the graphics
     *          component on the GameScene.
     */
    void updateGraphics(Graphic g);

}
