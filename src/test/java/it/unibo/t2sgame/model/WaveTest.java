package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.game.model.api.WaveFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WaveFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WorldFactoryImpl;

class WaveTest {
    private final WaveFactory waveFactory = new WaveFactoryImpl(new WorldFactoryImpl().createWorldWithOnePlayer());
    private static final int ROUND = 10;

    @Test
    void testBasicWaveSpawn() {
        final var wave = this.waveFactory.createBasicWave(ROUND);
        assertFalse(wave.getEnemies().isEmpty());
        assertEquals(ROUND / 2, wave.getEnemies().size());
    }

    @Test
    void testBossWaveSpawn() {
        final var wave = this.waveFactory.createBossWave(ROUND);
        assertFalse(wave.getEnemies().isEmpty());
        assertEquals(Math.ceil(ROUND / 4.0) + 1, wave.getEnemies().size());
    }

    @Test
    void testAddEntityToWave() {
        final var wave = this.waveFactory.createBasicWave(0);
        assertTrue(wave.getEnemies().isEmpty());
        wave.addEnemy(new EntityFactoryImpl().createBaseEnemy(new Vector2D(0, 0)));
        assertFalse(wave.getEnemies().isEmpty());
    }
}
