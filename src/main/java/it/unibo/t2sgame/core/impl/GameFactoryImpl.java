package it.unibo.t2sgame.core.impl;

import java.util.List;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.core.api.GameFactory;
import it.unibo.t2sgame.core.api.State;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.model.api.World;
import it.unibo.t2sgame.model.api.WorldFactory;
import it.unibo.t2sgame.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.model.impl.WaveFactoryImpl;
import it.unibo.t2sgame.model.impl.WorldFactoryImpl;

public class GameFactoryImpl implements GameFactory {


    private Game gameFrom(final State s, final World w){
        return new Game() {

            @Override
            public State getState() {
                return s;
            }

            @Override
            public World getWorld() {
                return w;
            }

            @Override
            public void nextWave() {
                s.incrementRound();
                w.setWave(new WaveFactoryImpl().createBasicWave(s.getRound()));                
            }
            
        };
    }

    private EntityFactory entityFactory = new EntityFactoryImpl();
    private WorldFactory worldFactory = new WorldFactoryImpl();

    @Override
    public Game createSinglePlayerGame() {
        return gameFrom(new StateImpl(), this.worldFactory.createWorldWithOnePlayer());
    }

    @Override
    public Game createMultiPlayerGame() {
        return gameFrom(new StateImpl(),
        this.worldFactory.createWorldWithMorePlayer(
            List.of(
                this.entityFactory.createPlayer(new Vector2D(0, 0)),
                this.entityFactory.createPlayer(new Vector2D(0, 0)))));
    }
    
}
