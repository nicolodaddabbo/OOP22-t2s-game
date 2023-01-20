package it.unibo.t2sgame.model;


import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.impl.MoveUp;
import it.unibo.t2sgame.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.physics.api.PhysicsComponent;
import it.unibo.t2sgame.view.api.GraphicComponent;

public class MessageTest {
    private final EntityFactoryImpl entityFactory = new EntityFactoryImpl();
    @Test
    void testMessage(){
        final var e1 = this.entityFactory.createPlayer(new Vector2D(0, 0));
        // Notify from Input to physics
        e1.notifyComponent(PhysicsComponent.class, () -> new MoveUp());
        // Notify from Physics to Graphics
        e1.notifyComponent(GraphicComponent.class, () -> new Vector2D(0, 0));
        // Receive from Physics1
        e1.getComponent(PhysicsComponent.class).ifPresent(pc -> pc.receive(() -> new MoveUp()));
        // Receive from Graphics
        e1.getComponent(GraphicComponent.class).ifPresent(gc -> gc.receive(() -> new Vector2D(0, 0)));
    }
}
