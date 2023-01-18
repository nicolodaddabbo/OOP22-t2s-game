package it.unibo.t2sgame.model.api;
import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.physic.api.PhysicComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;
import java.util.Optional;
import java.util.Set;

/**
 * This interface represents the entities of the game.
 */
public interface Entity {
    /**
     * 
     * @return list of components of the entity
     */
    Set<Component> getComponents();
    /**
     * 
     * @return Optional containing the specified Component if present,
     * otherwise returns an empty Optional
     */
    <T extends Component> Optional<Component> getComponent(Class<T> componentClass);
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
     * This method is used to add components to the entity.
     * @param component adds specified component to the set of components of the entity
     */
    Entity addComponent(Component component);
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
