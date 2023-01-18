package it.unibo.t2sgame.model.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.model.api.Wave;
import it.unibo.t2sgame.model.api.World;
import it.unibo.t2sgame.model.api.WorldFactory;

public class WorldFactoryImpl implements WorldFactory{

    private World createWorldWith(List<Entity> players){
        return new World() {
            @Override
            public Wave getCurrentWave() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public List<Entity> getPlayers() {
                return players;
            }

            @Override
            public List<Entity> getEntities() {
                return Stream.concat(this.getPlayers().stream(), this.getEntities().stream()).toList();
            }
        };
    }

    @Override
    public World createBasicWorld() {
        EntityFactory entityFactory =  /* new EntityFactory(); */ null;
        return createWorldWith(List.of(entityFactory.createPlayer()));
    }
    
}
