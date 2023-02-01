package it.unibo.t2sgame.game.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.Event;
import it.unibo.t2sgame.game.logics.api.GameMap;
import it.unibo.t2sgame.game.logics.impl.GameMapImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.Wave;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.api.WorldFactory;

public class WorldFactoryImpl implements WorldFactory{

    private final class WorldImpl implements World {
        /*
         * The lisf of entities in the world 
         */
        private final Set<Entity> entities = new HashSet<>();
        /**
         * An Optional containing the current wave if present
         */
        private final List<Entity> players = new ArrayList<>();
        private Optional<Wave> currentWave = Optional.empty();
        private final Queue<Event> eventQueue = new LinkedList<>();
        private final GameMap gameMap = new GameMapImpl(1200, 800);

        @Override
        public Optional<Wave> getCurrentWave() {
            return this.currentWave;
        }

        @Override
        public List<Entity> getPlayers() {
            return new ArrayList<>(this.players);
        }

        @Override
        public List<Entity> getEntities() {
            return new ArrayList<>(this.entities);
        }

        @Override
        public void addEntity(Entity e) {
            // Adding the entity to the current world
            this.entities.add(e);       
            // Setting the world where the entity is placed to the entity
            e.setWorld(this);
        }


        public void addPlayer(Entity player){
            this.players.add(player);
            this.addEntity(player);
        }

        @Override
        public void removeEntity(Entity e) {
            this.entities.remove(e);       
        }

        @Override
        public void setWave(final Wave next) {
            this.currentWave = Optional.of(next);
            next.getEnemies()
                .forEach(e -> e.getComponent(CollisionComponent.class)
                    .get()
                    .setCollisions(this.getPlayers().stream()
                        .map(p -> p.getComponent(CollisionComponent.class).get())
                        .collect(Collectors.toSet())));
            /* Adding all enemies to the entities */
            next.getEnemies().forEach(this::addEntity);
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

        public GameMap getMap(){
            return this.gameMap;
        }
    }


    private final EntityFactory entityFactory = new EntityFactoryImpl();

    @Override
    public World createWorldWithOnePlayer() {
        var player = this.entityFactory.createPlayer(new Vector2D(0, 0));
        var world = new WorldImpl();
        world.addPlayer(player);
        return world;
    }

    @Override
    public World createWorldWithMorePlayer(final List<Entity> players) {
        var world = new WorldImpl();
        players.forEach(world::addPlayer);
        return world;
    }
}
