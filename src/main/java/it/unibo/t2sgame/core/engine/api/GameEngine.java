package it.unibo.t2sgame.core.engine.api;

import java.util.List;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;

/**
 * 
 */
public interface GameEngine {
    /**
     * Run the current istance of the engine.
     * This method wil start a game loop, ending only when
     * the state and logics of the game hosted determs the ending
     */
    void run();

    /**
     * 
     * @return the game which is hosted by the engine
     */
    Game getGame();

    /**
     * 
     * @return the scene where the game is rendered
     */
    GameScene getScene();

    /**
     * 
     * @param <T>
     * @param clazz
     */
    <T extends Component> void updateComponentBy(Class<T> clazz);

    /**
     * 
     * @param <T>
     * @param clazz
     */
    <T extends Component> void updateComponentByConcurrent(Class<T> clazz);

    /**
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    <T extends Component> List<T> getComponents(Class<T> clazz);

    /**
     * Update the all graphics components using {@link g} to draw the entities
     * @param g
     */
    void updateGraphics(Graphic g);

}
