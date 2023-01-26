package it.unibo.t2sgame.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.WaveFactory;

public class WaveFactoryImpl implements WaveFactory {
    private EntityFactory entityFactory = new EntityFactoryImpl();
    private Random random = new Random(); 

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
        List<Entity> enemies;
        enemies = Stream.generate(() -> entityFactory.createBaseEnemy(new Vector2D(random.nextDouble(1000), random.nextDouble(1000))))
            .limit(round)
            .collect(Collectors.toList());
        return createWaveFromEnemies(enemies);
    }

}
