package it.unibo.t2sgame.core.impl;

import java.util.Optional;
import java.util.function.BiConsumer;

import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.core.api.GameEngine;
import it.unibo.t2sgame.core.api.GameEngineFactory;
import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.physics.api.PhysicsComponent;
import it.unibo.t2sgame.view.api.Scene;

public class GameEngineFactoryImpl implements GameEngineFactory {

    private static final int FRAME_PERIOD = 7000;

    private GameEngine engineFrom(final BiConsumer<Long, Long> waitForNextFrame) {
        return new GameEngine() {
            private static final int MS_PER_UPDATE = 7000;
            private Optional<Game> game = Optional.empty();
            private Optional<Scene> scene = Optional.empty();
            private long lag = 0;

            @Override
            public void run() {
                long previous = System.nanoTime();
                long current;
                long elapsed;
                if(this.game.isEmpty() || this.scene.isEmpty()){
                    return;
                }
                while(!this.isGameOver()){
                    current = System.nanoTime();
                    elapsed = current - previous;
                    this.lag = this.lag + elapsed;
                    // Process Input
                    this.updateComponent(InputComponent.class);
                    // Process Physics
                    while(!this.isSync()){
                        this.updateComponent(PhysicsComponent.class);
                        this.lag = this.lag - MS_PER_UPDATE;
                    }
                    // Render Graphics
                    this.scene.get().render();
                    // Waiting function
                    waitForNextFrame.accept(System.nanoTime(), current);
                    previous = current;
                }
            }

            private boolean isGameOver(){
                var g = this.game.get();
                return g.getState().isOver(g.getWorld().getPlayers());
            }
            
            private boolean isSync(){
                return this.lag < MS_PER_UPDATE;
            }

            private <T extends Component> void updateComponent(final Class<T> type){
                this.game.get().getWorld().getEntities().forEach(e -> e.getComponent(type).ifPresent(c -> c.update(e)));
            }

            @Override
            public GameEngine setGame(final Game g) {
                this.game = Optional.of(g);
                return this;
            }

            @Override
            public Optional<Game> getGame() {
                return this.game;
            }
        
        };
    }

    @Override
    public GameEngine createEngine() {
        return engineFrom((t1, t2) -> {/* Do nothing */});
    }

    @Override
    public GameEngine createGameEngineWithFpsLock() {
        return engineFrom((t1, t2) -> {
            var dt = t1 - t2;
            try {
                if(FRAME_PERIOD - dt > 0){
                    Thread.sleep(FRAME_PERIOD - dt);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

}
