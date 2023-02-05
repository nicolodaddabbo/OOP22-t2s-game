package it.unibo.t2sgame.game.model.impl;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
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
    private final double width;
    private final double height;

    private enum Enemy {
        BASE(EntityFactory::createBaseEnemy),
        GAUSSIAN(EntityFactory::createGaussianEnemy),
        WILD(EntityFactory::createWildEnemy),
        BOSS(EntityFactory::createBossEnemy);

        BiFunction<EntityFactory, Vector2D, Entity> entityBiFunction;

        Enemy(BiFunction<EntityFactory, Vector2D, Entity> entityBiFunction) {
            this.entityBiFunction = entityBiFunction;
        }

        private static final Random RANDOM = new Random();

        public static Enemy randomEnemy() {
            return Enemy.values()[RANDOM.nextInt(Enemy.values().length - 1)];
        }

        public BiFunction<EntityFactory, Vector2D, Entity> getBiFunction() {
            return this.entityBiFunction;
        }
    }

    public WaveFactoryImpl(final World world) {
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

            @Override
            public Wave addEnemy(Entity entity) {
                enemies.add(entity);
                return this;
            }
        };
    }

    @Override
    public Wave createBasicWave(final int round) {
        return createWaveFromEnemies(Stream.generate(() -> Enemy.BASE.getBiFunction().apply(this.entityFactory,
                    new Vector2D(random.nextDouble(0, this.width), random.nextDouble(0, this.height))))
                .limit(round)
                .collect(Collectors.toList()));
    }

    @Override
    public Wave createRandomWave(final int round) {
        return createWaveFromEnemies(Stream.generate(() -> Enemy.randomEnemy().getBiFunction().apply(entityFactory,
                    new Vector2D(random.nextDouble(0, this.width), random.nextDouble(0, this.height))))
                .limit(round)
                .collect(Collectors.toList()));
    }

    @Override
    public Wave createBossWave(final int round) {
        return this.createRandomWave(round / 2)
                .addEnemy(entityFactory.createBossEnemy(new Vector2D(this.width / 2, this.height / 2)));
    }
}
