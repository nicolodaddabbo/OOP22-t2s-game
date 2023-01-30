package it.unibo.t2sgame.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.engine.impl.GameEngineImpl;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.Event;
/**
 * An Enviroment is a Entities' container.
 * Adding an entity to the enviroment automatically adds
 * the entity to the engine.
 */
public abstract class AbstractEnviroment implements Enviroment {
    /*
     * A list of entities present in the enviroment
     */
    private final List<Entity> entities = new ArrayList<>();
    /*
     * The engine which is delegated to updates the GameSystems
     */
    private final GameEngine engine = new GameEngineImpl();

    private final Queue<Event> eventQueue = new LinkedList<>();

    /**
     * Creating a new Enviroment where entities will be displayed in the {@link scene}
     * @param scene the scene where the entities will be displayed
     */
    public AbstractEnviroment() {
    }

    /**
     * Update the enviroment
     */
    public void update(){
        this.handleEvents();
        this.engine.update();
    }

    /**
     * Add the entity {@link e} to the enviroment
     * @param e the entity to be added
     */
    public void addEntity(Entity entity){
        this.entities.add(entity);
        // Add the entity to the engine in order to update his state.
        this.engine.addEntityToSystems(entity);
        // Set the entity enviroment
        //e.setEnviroment(this);
    }

    /**
     * Remove the entity {@link e} to the enviroment
     * @param e the entity to be removed 
     * */
    public void removeEntity(Entity e){
        this.entities.remove(e);
        // Add the entity to the engine in order to update his state.
        this.engine.removeEntityToSystems(e);
    }

    public void notifyEvent(final Event event) {
        this.eventQueue.add(event);
    }

    public void handleEvents() {
        this.eventQueue.forEach(e -> e.execute(this));
        this.eventQueue.clear();
    }

    @Override
    public GameEngine getEngine() {
        return this.engine;
    }

}
