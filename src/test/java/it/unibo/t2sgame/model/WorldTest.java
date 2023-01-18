package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.WorldFactory;

public class WorldTest {
    
    private WorldFactory factory = null;
    @Test
    void testBasicWorld(){
        // Creating the world
        var world = this.factory.createBasicWorld();
        // Check if getPlayer return an entity with an PlayerInputComponent
        assertTrue(world.getPlayer().getComponent(InputComponent.class).isPresent());
        // Check if getCurrentWave returns a set of entity with an AIInputComponent
        assertTrue(world.getCurrentWave().getEnemies().stream().map(e -> e.getComponent(InputComponent.class).isPresent()).reduce((r1,r2) -> (r1 && r2)).get());
    }
}
