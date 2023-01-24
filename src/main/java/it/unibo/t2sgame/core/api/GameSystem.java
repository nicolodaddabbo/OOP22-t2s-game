package it.unibo.t2sgame.core.api;

import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;

public interface GameSystem {
    /**
     * Updating the current system
     */
    void update();
    /**
     * Check if the entity has a component related to the current system
     * @param e entity to be checked
     * @return true if the entity has a component related to the current system,
     * otherwise false
     */
    boolean isMember(Entity e);
    /**
     * 
     * @param <T> the type of component contained in the system
     * @param clazz 
     * @return an Optional containing the system if present, otherwise false
     */
    <T extends Component> boolean checkType(Class<T> clazz);
    /**
     * 
     *@return
     */
     Class<? extends Component> getType();
    /**
     * 
     * @param entity
     * @return
     */
     GameSystem addEntity(Entity entity);
}
