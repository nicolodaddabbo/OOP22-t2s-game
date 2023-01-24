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

    /*
     * This long indicates the period of frame creation in nanoseconds
     */
    private static final long FRAME_PERIOD = (long) (7 * 1E6);
    /*
     * This long indicates the perod of updating the states systems in nanoseconds
     */
    private static final long UPDATE_PERDIOD = (long) (7 * 1E6);
    /*
     * These systems are related to those which gives information usefull for
     * states systems.
     * These systems has to be called once during a game loop's cycle
     */
    private final List<GameSystem> initializers = new ArrayList<>();
    /*
     * These systems are related to those which updates the game time.
     * These systems would be called more times in a game loop's cycle, due to
     * the absence of a delta time step variable. 
     */
    private final List<GameSystem> states = new ArrayList<>();
    /*
     * This systems are related to the systems which has to be called after
     * updates the state of the entities.
     * In these field would be entered all related graphics domain systems.
     */
    private List<GameSystem> terminals = new ArrayList<>();
    // A list of entities contained in the engine
    private List<Entity> entities = new ArrayList<>(); 
    // A factory for creating GameSystems
    private GameSystemFactory systemFactory = new GameSystemFactoryImpl(); 
    // The scene where the GraphicsSystem has to render
    private Optional<GameScene> scene = Optional.empty();
    /* Represent the "gap" between game time and real time */
    private long lag = 0;
    /*  Usefull to synchronize states systems and to wait for next frame */ 
    private StopWatch timer = new StopWatch().start();

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
        this.terminals.forEach(GameSystem::update);
        this.scene.ifPresent(GameScene::render);
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
        var dt = this.timer.getElapsedNanos();
        if(FRAME_PERIOD - dt > 0){
            try {
                Thread.sleep((long) ((FRAME_PERIOD - dt) / 1E6));
            } catch (InterruptedException e) {
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
