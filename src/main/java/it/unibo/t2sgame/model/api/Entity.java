package it.unibo.t2sgame.model.api;
import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.physic.api.PhysicComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;
import java.util.List;
import java.util.Optional;

/**
 * This interface represents the entities of the game.
 */
public interface Entity {
    
    /**
     * 
     * @return list of components of the entity
     */
    List<Component> getComponents();
    /**
     * 
     * @return Optional containing the specified Component if present,
     * otherwise returns an empty Optional
     */
    <T extends Component> Optional<T> getComponent(Class<T> componentClass);
    /**
     * 
     * @return Optional containing the Graphic Component if present,
     * otherwise returns an empty Optional
     */
    Optional<GraphicComponent> getGraphicComponent();
    /**
     * 
     * @return Optional containing the Input Component if present,
     * otherwise returns an empty Optional
     */
    Optional<InputComponent> getInputComponent();
    /**
     * 
     * @return Optional containing the Physic Component if present,
     * otherwise returns an empty Optional
     */
    Optional<PhysicComponent> getPhysicComponent();
    /**
     * 
     * @param graphicComponent sets entity graphic component to the specified graphic component
     */
    void setGraphicComponent(GraphicComponent graphicComponent);
    /**
     * 
     * @param inputComponent sets entity input component to the specified input component
     */
    void setInputComponent(InputComponent inputComponent);
    /**
     * 
     * @param physicComponent sets entity physic component to the specified physic component
     */
    void setPhysicComponent(PhysicComponent physicComponent);
    /**
     * 
     */
    void notifyComponent();

    /**
     * Method used to apply the Prototype pattern
     * @return an exact copy of the Entity
     */
    Entity clone();
}
