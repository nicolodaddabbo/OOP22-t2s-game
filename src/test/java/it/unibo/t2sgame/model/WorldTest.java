package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.model.api.Wave;
import it.unibo.t2sgame.model.api.World;
import it.unibo.t2sgame.model.api.WorldFactory;
import it.unibo.t2sgame.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.model.impl.EntityImpl;
import it.unibo.t2sgame.model.impl.WaveFactoryImpl;
import it.unibo.t2sgame.model.impl.WorldFactoryImpl;

public class WorldTest {
    
    private WorldFactory worldFactory = new WorldFactoryImpl();
    private EntityFactory entityFactory = new EntityFactoryImpl();
    private WaveFactoryImpl waveFactory = new WaveFactoryImpl();

    private void basics(final World w){
        // Setting a wave
        w.setWave(new WaveFactoryImpl().createBasicWave(1));
        // Check if getPlayers return a list of entity with an Input Component
        assertTrue(w.getPlayers().stream().map(p -> p.getComponent(InputComponent.class).isPresent()).reduce((r1,r2) -> (r1 && r2)).get());
        // Check if getCurrentWave returns a set of entity with an InputComponent
         assertTrue(w.getCurrentWave().getEnemies().stream().map(e -> e.getComponent(InputComponent.class).isPresent()).reduce((r1,r2) -> (r1 && r2)).get());
        // Checking set a new Wave
        Wave wave = this.waveFactory.createBasicWave(2);
        w.setWave(wave);
        assertEquals(wave,w.getCurrentWave());
        // Checking add a new entity
        Entity e = new EntityImpl(new Vector2D(0, 0));
        w.addEntity(e);
        assertTrue(w.getEntities().contains(e));
    }

    @Test
    void testWorldWithOnePlayer(){
        var world = this.worldFactory.createWorldWithOnePlayer();
        // Check if world contains only one player
        assertTrue(world.getPlayers().size() == 1);
        // Check the basics
        basics(world);
      
    }

    @Test
    void testWorldWithMorePlayer(){
        var players = List.of(this.entityFactory.createPlayer(new Vector2D(0, 0)), this.entityFactory.createPlayer(new Vector2D(0, 0)));
        var world = this.worldFactory.createWorldWithMorePlayer(players);
        // Check if world contains more then one players
        assertTrue(world.getPlayers().size() > 1);
        // Check the basics
        basics(world);
    }
}
