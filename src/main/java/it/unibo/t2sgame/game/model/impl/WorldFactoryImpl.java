package it.unibo.t2sgame.game.model.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import javax.sql.rowset.WebRowSet;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.api.WorldFactory;

public class WorldFactoryImpl implements WorldFactory{

    private final EntityFactory entityFactory = new EntityFactoryImpl();

    @Override
    public World createWorldWithOnePlayer() {
        var world = this.createBasicWorld();
        return world.addPlayer(this.entityFactory.createPlayer(new Vector2D(world.getMap().getWidth() / 2, world.getMap().getHeight() / 2 )));
    }

    @Override
    public World createWorldWithMorePlayer(final List<Entity> players) {
        var world = this.createBasicWorld();
        return world.addEntities(players); 
    }

    @Override
    public World createBasicWorld() {
        var world = new WorldImpl();
        var width = world.getMap().getWidth();
        var height = world.getMap().getHeight();
        return world.addEntities(Set.of(
            // Adding the walls
            this.entityFactory.createWall(new Vector2D(0 ,height / 2), 0, height),
            this.entityFactory.createWall(new Vector2D(width, height / 2), 0, height),
            this.entityFactory.createWall(new Vector2D(width / 2, 0 ), width, 0),
            this.entityFactory.createWall(new Vector2D(width / 2 , height), width, 0)
        ));
    }
}
