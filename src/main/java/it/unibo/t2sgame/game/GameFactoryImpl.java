package it.unibo.t2sgame.game;

import java.util.List;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.game.logics.impl.StateImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.WorldFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WorldFactoryImpl;

public class GameFactoryImpl implements GameFactory {
    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final WorldFactory worldFactory = new WorldFactoryImpl();

    @Override
    public Game createSinglePlayerGame() {
        return new GameImpl(new StateImpl(), this.worldFactory.createWorldWithOnePlayer());
    }

    @Override
    public Game createMultiPlayerGame() {
        return new GameImpl(new StateImpl(), this.worldFactory.createWorldWithMorePlayer(
                List.of(
                        this.entityFactory.createPlayer(new Vector2D(0, 0)),
                        this.entityFactory.createPlayer(new Vector2D(0, 0)))));
    }

}
