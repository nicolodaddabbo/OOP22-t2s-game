package it.unibo.t2sgame.core.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.systems.api.GameSystem;
import it.unibo.t2sgame.core.systems.api.GameSystemFactory;
import it.unibo.t2sgame.core.systems.impl.GameSystemFactoryImpl;
import it.unibo.t2sgame.view.api.GameScene;

public class GameEngineImpl implements GameEngine {
    /**
     * This long indicates the period of frame creation in nanoseconds
     */
    private static final long NS_FRAME_PERIOD = (long) (7 * 1E6);
    /**
     * This long indicates the perod of updating the states systems in nanoseconds
     */
    private static final long UPDATE_PERDIOD = (long) (7 * 1E6);
    /**
     * These systems are related to those which gives information usefull for
     * states systems.
     * These systems has to be called once during a game loop's cycle
     */
    private final List<GameSystem> initializers = new ArrayList<>();
    /**
     * These systems are related to those which updates the game time.
     * These systems would be called more times in a game loop's cycle, due to
     * the absence of a delta time step variable. 
     */
    private final List<GameSystem> states = new ArrayList<>();
    // A factory for creating GameSystems
    private final GameSystemFactory systemFactory = new GameSystemFactoryImpl(); 
    // The scene where the GraphicsSystem has to render
    private  Optional<GameScene> scene = Optional.empty();
    /*  Usefull to synchronize states systems and to wait for next frame */ 
    private final StopWatch timer = new StopWatch().start();
    /* Represent the "gap" between game time and real time */
    private long lag = 0;

    /**
     * Creating a new istance of Engine class.
     * When an engine is created, different gamesytems will be setted up
     */
    public GameEngineImpl() {
        this.initializers.addAll(List.of(
            this.systemFactory.craeteInputSystem()
        ));
        this.states.addAll(List.of(
            this.systemFactory.createPhysicsSystem(),
            this.systemFactory.createCollisionSystem()
        ));
    }

    @Override
    public void update() {
        this.lag = this.lag + this.timer.getElapsedNanos();
            this.timer.restart();
        this.initializers.forEach(GameSystem::update); 
        /**
         *  This loop substitutes the usage of a delta time step 
         *  variabile to procede the game time.
         *  Using a delta time causes a non deterministic game
         *  due to the number of update's calls which which
         *  would be different based on the cpu / gpu "speed" of 
         *  the computer that is running the engine.
         *  */    
        while(!this.isSync()){
            this.states.forEach(GameSystem::update);
            this.lag = this.lag - UPDATE_PERDIOD;
        }
        this.scene.ifPresent(GameScene::render);
        this.waitForNextFrame();
    }

    @Override
    public GameEngine addEntityToSystems(Entity entity) {
        this.getSystemsOf(entity).forEach(s -> s.addComponent(entity.getComponent(s.getType())));
        return this;
    }

    @Override
    public GameEngine removeEntityToSystems(Entity entity) {
        this.getSystemsOf(entity).forEach(s -> s.removeComponent(entity.getComponent(s.getType())));
        return this;
    }

    @Override
    public Set<GameSystem> getSystems() {
        return Stream.concat(this.initializers.stream(), this.states.stream()).collect(Collectors.toSet());
    }

    @Override
    public <T extends Component> Optional<GameSystem> getSystem(Class<T> clazz) {
        return this.getSystems().stream().filter(s -> s.checkType(clazz)).findFirst();
    }

    /**
     * 
     * @param entity the entity to known which systems contain it
     * @return the systems which contains component related to {@link entity} 
     * 
     */
    private Set<GameSystem> getSystemsOf(Entity entity){
        return this.getSystems().stream().filter(s -> s.isMember(entity)).collect(Collectors.toSet());
    }

    /**
     * 
     * @return true if states systems are synchronized with the real time,
     * false otherwise
     */
    private boolean isSync(){
        return this.lag < UPDATE_PERDIOD;
    }

    /**
     * A waiter function usefull to optimize performance.
     * As soon as the thread has told to scene to render, it sleeps
     * for a time which is equal to the frame period procession.
     * This will cause an fps lock during the game loop.
     */
    private void waitForNextFrame(){
        var timeToSleep = NS_FRAME_PERIOD - this.timer.getElapsedNanos();
        if(timeToSleep > 0){
            try {
                // Sleeping time has to be converted in milliseconds
                Thread.sleep((long) ((timeToSleep) / 1E6));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    @Override
    public void setScene(GameScene scene) {
        this.scene = Optional.of(scene);        
    }

    
}
