package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.model.impl.EntityImpl;
import it.unibo.t2sgame.physics.api.PhysicsComponent;
import it.unibo.t2sgame.physics.api.PhysicsComponentFactory;
import it.unibo.t2sgame.physics.impl.PhysicsComponentFactoryImpl;
import it.unibo.t2sgame.view.api.GraphicComponent;
import it.unibo.t2sgame.view.impl.GraphicComponentFactoryImpl;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.api.InputComponent;;

public class EntityTest {
    
    private PhysicsComponentFactory physicFactory = new PhysicsComponentFactoryImpl();

    @Test void testGetEntityComponent() {
        var entity = new EntityImpl(new Vector2D(0, 0));
        entity.addComponent(this.physicFactory.createCirclePhyisicsComponent(1));
        var componentOptional = entity.getComponent(PhysicsComponent.class);
        assertTrue(componentOptional.isPresent());
        assertTrue(PhysicsComponent.class.isAssignableFrom(componentOptional.get().getClass()));
        componentOptional = entity.getComponent(InputComponent.class);
        assertFalse(componentOptional.isPresent());
        componentOptional = entity.getComponent(GraphicComponent.class);
        assertFalse(componentOptional.isPresent());
    }

    @Test
    void testCloneComponent(){
        var entity = new EntityImpl(new Vector2D(0, 0));
        entity.addComponent(new GraphicComponentFactoryImpl().getPlayerGraphicComponent());
        var clonedEntity = entity.clone();
        assertEquals(entity, clonedEntity);
        var nonClonedEntity = new EntityImpl(new Vector2D(0, 0));
        nonClonedEntity.addComponent(new GraphicComponentFactoryImpl().getBaseEnemyGraphicComponent());
        assertNotEquals(nonClonedEntity, entity);
    }
}
