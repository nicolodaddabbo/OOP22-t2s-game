package it.unibo.t2sgame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.core.impl.StateImpl;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.model.api.Wave;
import it.unibo.t2sgame.model.api.WaveFactory;
import it.unibo.t2sgame.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.model.impl.WaveFactoryImpl;

public class StateTest {
    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final WaveFactory waveFactory = new WaveFactoryImpl();
    @Test
    void testStateBasicImpl(){
        var state = new StateImpl();
        var alivePlayer = this.entityFactory.createPlayer();
        var deathPlayer = this.entityFactory.createPlayer();
        Wave waveWithAliveEnemy = this.waveFactory.createBasicWave(1);
        Wave emptyWave = this.waveFactory.createBasicWave(0);
        // Checking round
        assertEquals(0, state.getRound());
        state.incrementRound();
        assertEquals(1, state.getRound());
        state.incrementRound();
        assertEquals(2, state.getRound());
        // Checking game over
        assertFalse(state.isOver(List.of(alivePlayer)));    
        //assertTrue(state.isOver(List.of(deathPlayer)));
        // Checking wave over
        assertFalse(state.isWaveOver(waveWithAliveEnemy));
        assertTrue(state.isWaveOver(emptyWave));
    }
}
