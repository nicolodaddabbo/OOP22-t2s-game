package it.unibo.t2sgame.game;

import java.util.Optional;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.engine.impl.GameEngineImpl;
import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.impl.WaveFactoryImpl;
import it.unibo.t2sgame.view.api.GameScene;

public class GameImpl implements Game {
    private Optional<GameScene> scene = Optional.empty();
    private final GameEngine engine = new GameEngineImpl();
    private final State state;
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
    public void nextWave() {
        this.state.incrementRound();
        var wave = new WaveFactoryImpl().createBasicWave(this.state.getRound());
        wave.getEnemies().forEach(e -> {
            e.setWorld(world);
            this.engine.addEntity(e);
        });
        this.world.setWave(wave);
    }

    @Override
    public void start() {
        while (!this.isOver()) {
            this.addWaveIfOver();
            this.engine.update();
        }

    }

    @Override
    public void setScene(GameScene scene) {
        this.scene = Optional.of(scene);
    }

    private void addWaveIfOver() {
        if (this.state.isWaveOver(this.world.getCurrentWave())) {
            this.nextWave();
        }
    }

    @Override
    public boolean isOver() {
        return this.state.isOver(this.world.getPlayers());
    }

    @Override
    public void initSettings() {
        this.scene.ifPresent(s -> s.setEngine(this.engine));
        this.engine.setScene(this.scene.get());
    }

    @Override
    public void initGame() {
        this.world.getEntities().forEach(this.engine::addEntity);
    }

}
