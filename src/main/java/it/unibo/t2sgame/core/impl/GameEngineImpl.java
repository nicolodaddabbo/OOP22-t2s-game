package it.unibo.t2sgame.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.api.GameEngine;
import it.unibo.t2sgame.core.api.GameSystem;
import it.unibo.t2sgame.core.api.GameSystemFactory;
import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.GameScene;

public class GameEngineImpl implements GameEngine {
    /**
     * Using the static initializer in order to create and
     * starting the timers
     */
    {
        this.syncTimer = new StopWatch().start();
        this.renderTimer = new StopWatch().start();
    }

    /*
     * This long indicates the period of frame creation in nanoseconds
     */
    private final static long FRAME_PERIOD = (long) (7 * 1E6);
    /*
     * This long indicates the perod of updating the states systems in nanoseconds
     */
    private final static long UPDATE_PERDIOD = (long) (7 * 1E6);
    /*
     * 
     */
    private List<GameSystem> initializers = new ArrayList<>();
    /*
     * 
     */
    private List<GameSystem> states = new ArrayList<>();
    /*
     * 
     */
    private List<GameSystem> terminals = new ArrayList<>();
    // A list of entities contained in the engine
    private List<Entity> entities = new ArrayList<>(); 
    // A factory for creating GameSystems
    private GameSystemFactory systemFactory = new GameSystemFactoryImpl(); 
    // The scene where the GraphicsSystem has to render
    private Optional<GameScene> scene = Optional.empty();
    private long lag = 0;
    private StopWatch syncTimer;
    private StopWatch renderTimer;

   

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

        this.terminals.addAll(List.of(
           //this.systemFactory.createGraphicsSystem()
        ));
    }

    @Override
    public void update() {
        this.renderTimer.restart();
        this.lag = this.lag + this.syncTimer.getElapsedNanos();
        this.syncTimer.restart();
        this.initializers.forEach(GameSystem::update); 
        /**
         *  This loop substitutes the usage of a delta time
         *  to procede the game time.
         *  Using a delta time causes a non deterministic game
         *  due to the different number of calls of update which differs.
         *  */    
        while(!this.isSync()){
            this.states.forEach(GameSystem::update);
            this.lag = this.lag - UPDATE_PERDIOD;
        }
        this.terminals.forEach(GameSystem::update);
        this.scene.ifPresent(s -> s.render());
        this.waitForNextFrame();
    }

    @Override
    public GameEngine addEntity(Entity e) {
        this.entities.add(e);
        // Add the entity to the corresponding systems
        this.getSystems().stream()
            .filter(s -> s.isMember(e))
            .forEach(s -> s.addEntity(e));
        return this;
    }

    @Override
    public GameEngine removeEntity(Entity e) {
        this.entities.remove(e);
        return this;
    }

    @Override
    public Set<GameSystem> getSystems() {
        return Stream.concat(Stream.concat(this.initializers.stream(),this.states.stream()), this.terminals.stream())
            .collect(Collectors.toSet());
    }

    @Override
    public <T extends Component> Optional<GameSystem> getSystem(Class<T> clazz) {
        return this.getSystems().stream().filter(null).findFirst();
    }

    private boolean isSync(){
        return this.lag < UPDATE_PERDIOD;
    }

    private void waitForNextFrame(){
        var dt = this.renderTimer.getElapsedNanos();
        if(FRAME_PERIOD - dt > 0){
            try {
                Thread.sleep((long) ((FRAME_PERIOD - dt) / 1E6));
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    @Override
    public List<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public void setScene(GameScene scene) {
        this.scene = Optional.of(scene);        
    }
    
}
