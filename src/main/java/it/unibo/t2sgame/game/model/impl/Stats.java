package it.unibo.t2sgame.game.model.impl;

import java.util.Random;

final class Stats {

    private Stats() {
    }

    static final class Player {
        static final int HEALTH = 3;
        static final double MOVEMENT_SPEED = 1.0;
        static final int PROJECTILE_DAMAGE = 1;
        static final double FIRERATE_SECONDS = 0.5;
        static final double PROJECTILE_SIZE = 20.0;
        static final double PROJECTILE_SPEED = 1.5;
        static final double COLLISION_WIDTH = 60.0;
        static final double COLLISION_HEIGHT = 80.0;
        static final double RENDER_WIDTH = 60.0;
        static final double RENDER_HEIGHT = 80.0;

        private Player() {
        }
    }

    static final class Companion {
        static final double MOVEMENT_SPEED = 1.0;
        static final int DAMAGE = 1;
        static final double COOLDOWN_SECONDS = 1.0;
        static final double COLLISION_WIDTH = 60.0;
        static final double COLLISION_HEIGHT = 80.0;
        static final double RENDER_WIDTH = 60.0;
        static final double RENDER_HEIGHT = 80.0;

        private Companion() {
        }
    }

    static final class Enemy {
        static final int HEALTH = 1;
        static final int DAMAGE = 1;
        static final double DAMAGE_COOLDOWN = 1.0;
        static final double MOVEMENT_SPEED = 0.3;
        static final double COLLISION_WIDTH = 60.0;
        static final double COLLISION_HEIGHT = 50.0;
        static final double RENDER_WIDTH = 60.0;
        static final double RENDER_HEIGHT = 60.0;

        private Enemy() {
        }
    }

    static final class GaussianEnemy {
        static final int HEALTH = 1;
        static final int DAMAGE = 1;
        static final double DAMAGE_COOLDOWN = 1.0;
        static final double MOVEMENT_SPEED = 0.8;
        static final double COLLISION_WIDTH = 60.0;
        static final double COLLISION_HEIGHT = 50.0;
        static final double RENDER_WIDTH = 60.0;
        static final double RENDER_HEIGHT = 60.0;

        private GaussianEnemy() {
        }
    }

    static final class WildEnemy {
        private static final Random RANDOM = new Random();
        static final int HEALTH = 1;
        static final int DAMAGE = 1;
        static final double DAMAGE_COOLDOWN = 1.0;
        static final double MOVEMENT_SPEED = RANDOM.nextDouble(0.3, 1);
        static final double COLLISION_WIDTH = 60.0;
        static final double COLLISION_HEIGHT = 50.0;
        static final double RENDER_WIDTH = 60.0;
        static final double RENDER_HEIGHT = 60.0;

        private WildEnemy() {
        }
    }

    static final class BossEnemy {
        static final int HEALTH = 25;
        static final int DAMAGE = 2;
        static final double DAMAGE_COOLDOWN = 1.0;
        static final double MOVEMENT_SPEED = 0.3;
        static final double COLLISION_WIDTH = 200.0;
        static final double COLLISION_HEIGHT = 140.0;
        static final double RENDER_WIDTH = 200.0;
        static final double RENDER_HEIGHT = 140.0;

        private BossEnemy() {
        }
    }

}
