package it.unibo.t2sgame.game.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.game.logics.api.Event;
import it.unibo.t2sgame.game.logics.api.GameMap;
import it.unibo.t2sgame.game.logics.impl.GameMapImpl;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.World;

final class WorldImpl implements World {
    /*
     * The lisf of entities in the world.
     * The implementation of the List is thread safe, allowing add / remove entities
     * during components update without problems
     */
    private final List<Entity> entities = new CopyOnWriteArrayList<>();

    private final List<Entity> players = new ArrayList<>();
    /*
     * An Optional containing the current wave if present
     */
    private Optional<Wave> currentWave = Optional.empty();
    /*
     * 
     */
    private final ConcurrentLinkedQueue<Event> eventQueue = new ConcurrentLinkedQueue<>();
    /*
     * The "logic" game map of the world
     */
    private final GameMap gameMap = new GameMapImpl(1200, 800);

    @Override
    public Optional<Wave> getCurrentWave() {
        return this.currentWave;
    }

    @Override
    public List<Entity> getPlayers() {
        return new ArrayList<>(this.entities.stream().filter(e -> e.getType() == Type.PLAYER).toList());
    }

    @Override
    public List<Entity> getEntities() {
        return new ArrayList<>(this.entities);
    }

    @Override
    public World addEntities(Collection<Entity> entities) {
        this.entities.addAll(entities);
        // Setting the reference to the world for each entity added
        entities.forEach(e -> e.setWorld(this));
        return this;
    }

    @Override
    public World addEntity(Entity e) {
        return this.addEntities(Collections.singleton(e));
    }

    public World addPlayer(Entity player) {
        return this.addEntity(player);
    }

    @Override
    public World removeEntities(Collection<Entity> entities) {
        this.entities.removeAll(entities);
        return this;
    }

    @Override
    public World removeEntity(Entity e) {
        return this.removeEntities(Collections.singleton(e));
    }

    @Override
    public void setWave(final Wave next) {
        this.currentWave = Optional.of(next);
        /* Adding all enemies to the entities */
        this.addEntities(next.getEnemies());
    }

    @Override
    public void notifyEvent(final Event event) {
        this.eventQueue.add(event);
    }

    @Override
    public void handleEvents() {
        this.eventQueue.forEach(e -> e.execute(this));
        this.eventQueue.clear();
    }

    @Override
    public GameMap getMap() {
        return this.gameMap;
    }

    
}