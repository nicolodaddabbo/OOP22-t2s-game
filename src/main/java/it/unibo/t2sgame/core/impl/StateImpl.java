package it.unibo.t2sgame.core.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.core.api.State;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Wave;

public class StateImpl implements State{
    private int round = 0;

    @Override
    public boolean isOver(List<Entity> players) {
        // Need to check the all players' life
        return false;
    }

    @Override
    public boolean isWaveOver(Optional<Wave> w) {
        return w.isEmpty() || w.get().getEnemies().isEmpty();
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public void incrementRound() {
        this.round++;        
    }

    
}
