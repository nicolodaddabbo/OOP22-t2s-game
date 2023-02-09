package it.unibo.t2sgame.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.game.logics.impl.StateImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.WaveFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WaveFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WorldFactoryImpl;

class StateTest {

    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final WaveFactory waveFactory = new WaveFactoryImpl(new WorldFactoryImpl().createWorldWithOnePlayer());

    @Test
    void testState() {
        // Testing the state's basic implementation
        final var state = new StateImpl();
        assertEquals(0, state.getRound());
        state.incrementRound();
        assertEquals(1, state.getRound());
        state.incrementRound();
        assertEquals(2, state.getRound());
        state.incrementRound();
        assertEquals(3, state.getRound());
        state.incrementRound();
        assertEquals(4, state.getRound());
        // Testing isOver method
        assertTrue(state.isOver(List.of()));
        assertFalse(state.isOver(List.of(this.entityFactory.createPlayer(new Vector2D(0, 0)))));
        // Testing isWaveOver logics
        final var w = this.waveFactory.createBasicWave(1);
        assertFalse(state.isWaveOver(Optional.of(w)));
        w.getEnemies().clear();
        assertTrue(state.isWaveOver(Optional.of(w)));
        // Testing an empty optional
        assertTrue(state.isWaveOver(Optional.empty()));
    }
}
