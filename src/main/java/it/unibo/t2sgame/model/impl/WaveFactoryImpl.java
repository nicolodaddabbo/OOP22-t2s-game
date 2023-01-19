package it.unibo.t2sgame.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.core.api.State;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.model.api.Wave;
import it.unibo.t2sgame.model.api.WaveFactory;

public class WaveFactoryImpl implements WaveFactory {
    private EntityFactory entityFactory;

    private Wave createWaveFromEnemies(final List<Entity> enemies) {
        return new Wave() {
            final int roundId = enemies.size();
            @Override
            public List<Entity> getEnemies() {
                return enemies;
            }

            @Override
            public int getWaveID() {
                return this.roundId;
            }
        };
    } 
   
    @Override
    public Wave createBasicWave(final int round) {
        return createWaveFromEnemies(new ArrayList<>(Collections.nCopies(round, entityFactory.createBaseEnemy())));
    }

}
