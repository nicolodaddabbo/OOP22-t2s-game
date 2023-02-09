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

public class WaveTest {
    private WaveFactory waveFactory = new WaveFactoryImpl(new WorldFactoryImpl().createWorldWithOnePlayer());
    private int round = 10;
    
    @Test
    public void testBasicWaveSpawn(){
        var wave = this.waveFactory.createBasicWave(this.round);
        assertFalse(wave.getEnemies().isEmpty());
        assertEquals(round / 2, wave.getEnemies().size());
    }

    @Test
    public void testBossWaveSpawn(){
        var wave = this.waveFactory.createBossWave(this.round);
        assertFalse(wave.getEnemies().isEmpty());
        assertEquals((Math.ceil((round / 2) / 2.0)) + 1, wave.getEnemies().size());
    }

    @Test
    public void testAddEntityToWave(){
        var wave = this.waveFactory.createBasicWave(0);
        assertTrue(wave.getEnemies().isEmpty());
        wave.addEnemy(new EntityFactoryImpl().createBaseEnemy(new Vector2D(0, 0)));
        assertFalse(wave.getEnemies().isEmpty());
    }
}
