package it.unibo.t2sgame.game;

import java.util.Optional;

import it.unibo.t2sgame.core.State;
import it.unibo.t2sgame.game.model.api.Wave;

public class T2SLogics implements State {
    private final T2SWorld world;
    private int round = 0;

    public T2SLogics(final T2SWorld world) {
        this.world = world;
    }

    @Override
    public boolean isOver() {
        return this.world.getPlayers().isEmpty();
    }

    public boolean isWaveOver(Optional<Wave> w) {
        return w.isEmpty() || w.get().getEnemies().isEmpty();
    }

    public int getRound() {
        return this.round;
    }

    public void incrementRound() {
        this.round++;        
    }
    
}
