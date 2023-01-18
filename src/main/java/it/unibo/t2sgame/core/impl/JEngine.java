package it.unibo.t2sgame.core.impl;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.core.api.GameEngine;
import it.unibo.t2sgame.core.api.State;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.World;

public class JEngine implements GameEngine {
    /* Updating period in milliseconds */
    private static final long MS_PER_UPDATE = 7;
    /* Frame period in milliseconds */
    private static final long FRAME_PERIOD = 7;

    private Game game;
    private long lag = 0;

    private final Predicate<Long> synchronizer = (lag) -> lag < MS_PER_UPDATE;

    private final BiConsumer<Long, Long> waitNextFrame = (t1, t2) -> {
        var dt = t1 - t2;
        try {
            Thread.sleep(FRAME_PERIOD - dt);
        } catch (Exception e) {
            // [TODO]
            e.printStackTrace();
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        long previous = System.nanoTime();
        long current;
        long elapsed;
        while (!this.getState().isOver()) {
            current = System.nanoTime();
            elapsed = current - previous;
            // Calculate the delay between the last and the new frame
            this.lag = this.lag + elapsed;
            this.processInput();
            // Updating physic system
            this.update();
            // Updating graphic system
            this.render();
            this.waitNextFrame.accept(System.nanoTime(), current);
            previous = current;
        }
    }

    /*
     * Processing all input components present in the world
     */
    private void processInput() {
        this.getEntities().forEach(e -> e.getInputComponent().ifPresent(ic -> ic.update(e)));
    }
    /*
     * Processing all physic components present in the world
     */
    private void update() {
        while(this.synchronizer.test(this.lag)){
            this.getEntities().forEach(e -> e.getPhysicComponent().ifPresent(pc -> pc.update(e)));
            this.lag = this.lag - MS_PER_UPDATE;
        }
    }
    /*
     * Delegating the rendering of the entities to the scene.
     */
    private void render() {
        // this.scene.render();
    }
    /*
     * Getting the Collection of all entities in the world
     */
    private Collection<Entity> getEntities(){
        return this.getWorld().getEntities();
    }
    /*
     * Getting the Game's World
     */
    private World getWorld(){
        return this.game.getWorld();
    }
    /*
     * Getting the Game' State
     */
    private State getState(){
        return this.game.getState();
    }
}
