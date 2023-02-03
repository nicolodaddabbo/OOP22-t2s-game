package it.unibo.t2sgame.game.model.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.WaveFactory;
import it.unibo.t2sgame.game.model.api.World;

public class WaveFactoryImpl implements WaveFactory {
    private EntityFactory entityFactory = new EntityFactoryImpl();
    private Random random = new Random();
    private World world; 
    private double width, height;

    public WaveFactoryImpl(final World world){
        this.world = world;
        this.width = this.world.getMap().getWidth();
        this.height = this.world.getMap().getHeight();
    }

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
        enemies = Stream.generate(() -> entityFactory.createBaseEnemy(new Vector2D(random.nextDouble(0, this.width), random.nextDouble(0, this.height))))
            .limit(round)
            .collect(Collectors.toList());
        return createWaveFromEnemies(enemies);
    }
}
