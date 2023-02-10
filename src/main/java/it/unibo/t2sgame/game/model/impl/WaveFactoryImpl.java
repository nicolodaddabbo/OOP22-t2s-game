package it.unibo.t2sgame.game.model.impl;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.game.ecs.api.Entity;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.WaveFactory;
import it.unibo.t2sgame.game.model.api.World;
/**
 * class that is implementing interface WaveFactory.
 */
public class WaveFactoryImpl implements WaveFactory {
    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final Random random = new Random();
    private final double width;
    private final double height;
    private static final double OUTSIDEMAPVALUE = 100.0;

    private enum Enemy {
        BASE(EntityFactory::createBaseEnemy),
        GAUSSIAN(EntityFactory::createGaussianEnemy),
        WILD(EntityFactory::createWildEnemy),
        BOSS(EntityFactory::createBossEnemy);

        private BiFunction<EntityFactory, Vector2D, Entity> entityBiFunction;
        private static final Random RANDOM = new Random();
        private static final int BASEENEMYPROBABILISTICWEIGH = 50;
        private static final int GAUSSIANENEMYPROBABILISTICWEIGH = 90;

        Enemy(final BiFunction<EntityFactory, Vector2D, Entity> entityBiFunction) {
            this.entityBiFunction = entityBiFunction;
        }

        public static Enemy randomEnemy() {
            final var randomValue = RANDOM.nextInt(100);
            return randomValue < BASEENEMYPROBABILISTICWEIGH 
                ? Enemy.BASE 
                : randomValue < GAUSSIANENEMYPROBABILISTICWEIGH 
                    ? Enemy.GAUSSIAN 
                    : Enemy.WILD;
        }

        public BiFunction<EntityFactory, Vector2D, Entity> getBiFunction() {
            return this.entityBiFunction;
        }
    }
    /**
     * constructor of WaveFactoryImpl.
     * @param world in which the waves are going to be spawned
     */
    public WaveFactoryImpl(final World world) {
        this.width = world.getMap().getWidth();
        this.height = world.getMap().getHeight();
    }

    private Wave createWaveFromEnemies(final List<Entity> enemies) {
        return new Wave() {
            /**
            * {@inheritDoc}
            */
            @Override
            public List<Entity> getEnemies() {
                return enemies;
            }
            /**
            * {@inheritDoc}
            */
            @Override
            public Wave addEnemy(final Entity entity) {
                enemies.add(entity);
                return this;
            }
        };
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public Wave createBasicWave(final int round) {
        return createWaveFromEnemies(Stream.generate(() -> Enemy.BASE.getBiFunction().apply(this.entityFactory,
                    new Vector2D(random.nextDouble(0, this.width), random.nextDouble(0, this.height))))
                .limit((long) Math.ceil(round / 2.0))
                .collect(Collectors.toList()));
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public Wave createRandomWave(final int round) {
        return createWaveFromEnemies(Stream.generate(() -> Enemy.randomEnemy().getBiFunction().apply(entityFactory,
                    new Vector2D(random.nextDouble(-OUTSIDEMAPVALUE, this.width + OUTSIDEMAPVALUE), 
                                random.nextDouble(-OUTSIDEMAPVALUE, this.height + OUTSIDEMAPVALUE))))
                .limit((long) Math.ceil(round / 2.0))
                .collect(Collectors.toList()));
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public Wave createBossWave(final int round) {
        return this.createRandomWave(round / 2)
                .addEnemy(entityFactory.createBossEnemy(new Vector2D(this.width / 2, this.height / 2)));
    }
}
