package it.unibo.t2sgame.game;

import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.impl.WaveFactoryImpl;

public class GameImpl implements Game {
    /*
     * The state and logics of T2SGame
     */
    private final State state;
    /**
     * The world where all entities are stored
     */
    private final World world;

    public GameImpl(final State state, final World world) {
        this.state = state;
        this.world = world;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public boolean isOver() {
        return this.state.isOver(this.world.getPlayers());
    }

    @Override
    public void update() {
        this.addWaveIfOver();
        this.world.handleEvents();
    }

    private void nextWave() {
        this.state.incrementRound();
        this.state.generatePowerUp(this.world);
        var wave = this.state.getRound() % 10 == 0
                ? new WaveFactoryImpl(this.world).createBossWave(this.state.getRound())
                : new WaveFactoryImpl(this.world).createRandomWave(this.state.getRound());
        this.world.setWave(wave);
    }

    private void addWaveIfOver() {
        if (this.state.isWaveOver(this.world.getCurrentWave())) {
            this.nextWave();
        }
    }
}
