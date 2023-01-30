package it.unibo.t2sgame.game;

import it.unibo.t2sgame.core.AbstractGame;
import it.unibo.t2sgame.game.model.impl.WaveFactoryImpl;

public class T2SGame extends AbstractGame {
    private final T2SWorld t2sWorld;
    private final T2SLogics t2sLogics;

    public T2SGame(final T2SWorld enviroment, final T2SLogics state) {
        super(enviroment, state);
        this.t2sWorld = enviroment;
        this.t2sLogics = state;
    }

    public void nextWave() {
        this.t2sLogics.incrementRound();
        var wave = new WaveFactoryImpl().createBasicWave(this.t2sLogics.getRound());
        wave.getEnemies().forEach(e -> {
            // e.setWorld(t2sWorld);
            this.t2sWorld.addEntity(e);
        });
        this.t2sWorld.setWave(wave);
    }
    
    @Override
    public void checkLogics() {
        this.addWaveIfOver();
    }

    private void addWaveIfOver() {
        if (this.t2sLogics.isWaveOver(this.t2sWorld.getCurrentWave())) {
            this.nextWave();
        }
    }

    // public void initSettings() {
    //     this.t2sWorld.getEngine().setScene(this.t2sLogics);
    // }
    
}
