package it.unibo.t2sgame.model.api;
import it.unibo.t2sgame.view.api.GraphicComponent;
import java.util.List;
import java.util.Optional;

/**
 * This interface represents the entities of the game.
 */
public interface Entity {
    
    /**
     * @return list of components of the entity
     */
    List<Component> getComponents();

    /**
     * @return optional of the GraphicComponent of the entity
     */
    Optional<GraphicComponent> getGraphicComponent();

    /**
     * 
     */
    void notifyComponent();

}
