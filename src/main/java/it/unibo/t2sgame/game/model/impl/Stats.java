package it.unibo.t2sgame.game.model.impl;

import java.util.List;
import java.util.Random;

import it.unibo.t2sgame.core.entity.api.Type;

public final class Stats {
    
    private Stats() {
    }

    public static final class Player{
        private Player() {
        }
        public static final int HEALTH = 3;
        public static final double MOVEMENT_SPEED = 1.0;
        public static final int PROJECTILE_DAMAGE = 1;
        public static final double FIRERATE_SECONDS = 0.5;
        public static final double PROJECTILE_SIZE = 20.0;
        public static final double PROJECTILE_SPEED = 1.5;
        public static final double COLLISION_WIDTH = 60.0;
        public static final double COLLISION_HEIGHT = 80.0;
        public static final double RENDER_WIDTH = 60.0;
        public static final double RENDER_HEIGHT = 80.0;
    }

    public static final class Companion{
        private Companion() {
        }
        public static final double MOVEMENT_SPEED = 1.0;
        public static final int DAMAGE = 1;
        public static final double COOLDOWN_SECONDS = 1.0;
        public static final double COLLISION_WIDTH = 60.0;
        public static final double COLLISION_HEIGHT = 80.0;
        public static final double RENDER_WIDTH = 60.0;
        public static final double RENDER_HEIGHT = 80.0;
    }

    public static class Enemy{
        private Enemy() {
        }
        public static final int HEALTH = 1;
        public static final int DAMAGE = 1;
        public static final double DAMAGE_COOLDOWN = 1.0;
        public static final double MOVEMENT_SPEED = 0.3;
        public static final double COLLISION_WIDTH = 60.0;
        public static final double COLLISION_HEIGHT = 50.0;
        public static final double RENDER_WIDTH = 60.0;
        public static final double RENDER_HEIGHT = 60.0;
    }

    public static final class GaussianEnemy extends Enemy{
        private static final Random random = new Random();
        private GaussianEnemy() {
        }
        public static final double MOVEMENT_SPEED = random.nextDouble(0.3, 1);
    }

    public static final class WildEnemy extends Enemy{
        private WildEnemy() {
        }
        public static final double MOVEMENT_SPEED = 0.8;
    }

    public static final class BossEnemy extends Enemy{
        private BossEnemy() {
        }
        public static final int HEALTH = 25;
        public static final int DAMAGE = 2;
        public static final double MOVEMENT_SPEED = 0.3;
        public static final double COLLISION_WIDTH = 200.0;
        public static final double COLLISION_HEIGHT = 140.0;
        public static final double RENDER_WIDTH = 200.0;
        public static final double RENDER_HEIGHT = 140.0;
    }

}
