package it.unibo.t2sgame.physics.api;

import it.unibo.t2sgame.model.api.Component;

public interface PhysicsComponent extends Component {

    /**
     * Updates the position of the passed entity if it has one.
     * @param entity the entity to update
     */
    @Override
    void update();

}
