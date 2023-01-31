package it.unibo.t2sgame.core.engine.api;

import java.util.List;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.game.Game;

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
     * @param <T>
     * @param clazz
     */
    <T extends Component> void updateComponentBy(Class<T> clazz);
    /**
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    <T extends Component> List<T> getComponents(Class<T> clazz);

}

