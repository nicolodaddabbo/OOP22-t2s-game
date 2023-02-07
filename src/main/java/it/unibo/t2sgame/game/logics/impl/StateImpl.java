package it.unibo.t2sgame.game.logics.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.impl.PowerUpFactoryImpl;

public class StateImpl implements State {
    private static final int POWER_UP_ROUND = 5;
    private int round = 0;
    private final PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();
    private final EventFactory eventFactory = new EventFactoryImpl();
    private final Random random = new Random();

    @Override
    public boolean isOver(List<Entity> players) {
        return players.isEmpty();
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

    @Override
    public void generatePowerUp(final World world) {
        if (this.round % POWER_UP_ROUND == 0) {
            var powerUpList = this.powerUpFactory.getObtainablePowerUpList();
            world.notifyEvent(this.eventFactory.onPowerUpEvent(powerUpList.get(this.random.nextInt(powerUpList.size()))));
        }
    }

}
