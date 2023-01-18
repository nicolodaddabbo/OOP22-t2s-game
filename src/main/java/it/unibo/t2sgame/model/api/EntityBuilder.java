package it.unibo.t2sgame.model.api;

import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.physic.api.PhysicComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;

/**
 * An entity builder to build an Entity instance
 */
public interface EntityBuilder {
    /**
     * This method adds the physic component to the entity
     * @param physicComponent
     * @return the entity builder
     */
    EntityBuilder physic(PhysicComponent physicComponent);
    /**
     * This method adds the input component to the entity
     * @param inputComponent
     * @return the entity builder
     */
    EntityBuilder input(InputComponent inputComponent);
    /**
     * This method adds the input component to the entity
     * @param graphicComponent
     * @return the entity builder
     */
    EntityBuilder graphic(GraphicComponent graphicComponent);
    /**
     * 
     * @return the built entity
     */
    Entity build();

}
