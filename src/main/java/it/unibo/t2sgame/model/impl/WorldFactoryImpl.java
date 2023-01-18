package it.unibo.t2sgame.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.model.api.Wave;
import it.unibo.t2sgame.model.api.World;
import it.unibo.t2sgame.model.api.WorldFactory;

public class WorldFactoryImpl implements WorldFactory{

    private World createWorldWith(final List<Entity> players){
        return new World() {
            private final List<Entity> entities = new ArrayList<>(); 
            private Wave currentWave;

            @Override
            public Wave getCurrentWave() {
                return this.currentWave;
            }

            @Override
            public List<Entity> getPlayers() {
                return players;
            }

            @Override
            public List<Entity> getEntities() {
                return this.entities;
            }

            @Override
            public void addEntity(Entity e) {
                this.entities.add(e);                
            }

            @Override
            public void setWave(final Wave next) {
                this.currentWave = next;
                /* Adding all enemies to the entities */
                this.currentWave.getEnemies().forEach(entities::add);
            }
            
            
        };
    }

    @Override
    public World createBasicWorld() {
        EntityFactory entityFactory =  /* new EntityFactory(); */ null;
        return createWorldWith(List.of(entityFactory.createPlayer()));
    }
    
}
