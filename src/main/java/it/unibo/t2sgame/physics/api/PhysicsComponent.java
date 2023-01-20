package it.unibo.t2sgame.physics.api;

import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Message;

public interface PhysicsComponent extends Component {

    /**
     * Updates the position of the passed entity if it has one.
     * @param entity the entity to update
     */
    @Override
    void update(Entity entity);

    @Override
    <T> void receive(Message<T> message);

}
