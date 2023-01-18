package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Wave;
import it.unibo.t2sgame.model.api.WorldFactory;
import it.unibo.t2sgame.model.impl.EntityImpl;

public class WorldTest {
    
    private WorldFactory factory = null;
    @Test
    void testBasicWorld(){
        var world = this.factory.createBasicWorld();
        // Check if getPlayer return an entity with an InputComponent 
        assertTrue(world.getPlayers().get(0).getComponent(InputComponent.class).isPresent());
        // Check if getCurrentWave returns a set of entity with an InputComponent
        assertTrue(world.getCurrentWave().getEnemies().stream().map(e -> e.getComponent(InputComponent.class).isPresent()).reduce((r1,r2) -> (r1 && r2)).get());
        // Checking set a new Wave
        Wave w = null;
        world.setWave(w);
        assertEquals(w,world.getCurrentWave());
        // Checking add a new entity
        Entity e = new EntityImpl();
        world.addEntity(e);
        assertTrue(world.getEntities().contains(e));
    }
}
