package it.unibo.t2sgame.core.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.api.Engine;
import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.core.api.GameFactory;
import it.unibo.t2sgame.core.api.State;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.model.api.World;
import it.unibo.t2sgame.model.api.WorldFactory;
import it.unibo.t2sgame.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.model.impl.WaveFactoryImpl;
import it.unibo.t2sgame.model.impl.WorldFactoryImpl;
import it.unibo.t2sgame.view.api.GameScene;

public class GameFactoryImpl implements GameFactory {


    private Game gameFrom(final State s, final World w){
        return new Game() {
            private Optional<GameScene> scene = Optional.empty();

            private Engine engine = new EngineImpl();

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
                var wave = new WaveFactoryImpl().createBasicWave(s.getRound());
                wave.getEnemies().forEach(e -> {
                    e.setWorld(w);
                    this.engine.addEntity(e);
                });
                w.setWave(wave);                
            }

            @Override
            public void start() {
                while(!this.isOver()){
                    this.addWaveIfOver();
                    engine.update();
                }
              
            }

            @Override
            public void setScene(GameScene scene) {
                this.scene = Optional.of(scene);                
            }
            

            private void addWaveIfOver(){
                if(s.isWaveOver(w.getCurrentWave())){
                    this.nextWave();
                }
            }

            @Override
            public boolean isOver() {
                return s.isOver(w.getPlayers());
            }

            @Override
            public void initSettings() {
                this.scene.ifPresent(scene -> scene.setEngine(engine));
                this.scene.ifPresent(scene -> scene.setGame(this));
                engine.setScene(this.scene.get());
            }

            @Override
            public void initGame() {
                w.getEntities().forEach(e -> this.engine.addEntity(e));
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
