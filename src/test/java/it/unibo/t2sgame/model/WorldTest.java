package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.WaveFactory;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.api.WorldFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WaveFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WorldFactoryImpl;

class WorldTest {

    private final WorldFactory worldFactory = new WorldFactoryImpl();
    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private WaveFactory waveFactory;

    void testBasics(final World w) {
        this.waveFactory = new WaveFactoryImpl(w);
        final var wave = this.waveFactory.createBasicWave(1);
        final var e = this.entityFactory.createBaseEnemy(new Vector2D(0, 0));
        // Adding one entity
        w.addEntity(e);
        assertTrue(w.getEntities().contains(e));
        // Removing one entity
        w.removeEntity(e);
        assertFalse(w.getEntities().contains(e));
        // Adding more entities
        final var entities = List.of(
                this.entityFactory.createBaseEnemy(new Vector2D(0, 0)),
                this.entityFactory.createBaseEnemy(new Vector2D(0, 0)));
        w.addEntities(entities);
        assertTrue(w.getEntities().containsAll(entities));
        // Removing more entities
        w.removeEntities(entities);
        assertFalse(w.getEntities().containsAll(entities));
        // Getting the map
        assertNotNull(w.getMap());
        // Getting get/set wave
        assertEquals(Optional.empty(), w.getCurrentWave());
        w.setWave(wave);
        assertNotEquals(Optional.empty(), w.getCurrentWave());
    }

    @Test
    void testWorldWithOnePlayer() {
        final var world = worldFactory.createWorldWithOnePlayer();
        assertEquals(1, world.getPlayers().size());
        testBasics(world);
    }

    @Test
    void testWorldWithPlayerAndCompanion() {
        final var world = this.worldFactory.createWorldWithPlayerAndCompanion();
        assertEquals(1, world.getPlayers().size());
        assertEquals(1, world.getEntities().stream()
                .filter(e -> e.getType() == Type.COMPANION).count());
        testBasics(world);

    }

}
