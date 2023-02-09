package it.unibo.t2sgame.game;

import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.impl.WaveFactoryImpl;

/**
 * This class represents a T2S Game.
 * To running a game, the instance of the class has to be hosted by the
 * {@link GameEngine}.
 */
public class T2SGame implements Game {
    /*
     * The state and logics of T2SGame.
     */
    private final State state;
    /*
     * The world where all entities are stored.
     */
    private final World world;

    /**
     * Create a T2SGame based on {@link state} and {@link world}.
     * 
     * @param state represents the state/logics of the current game.
     * @param world represents the world where the entities of the game are placed.
     */
    public T2SGame(final State state, final World world) {
        this.state = state;
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.state.isOver(this.world.getPlayers());
    }

    /**
     * {@inheritDoc}
     */
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
