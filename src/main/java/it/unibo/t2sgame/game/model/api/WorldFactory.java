package it.unibo.t2sgame.game.model.api;

import java.util.List;

import it.unibo.t2sgame.core.entity.api.Entity;

public interface WorldFactory {
    /**
     * 
     * @return a World implementation with only one player  
     */
    World createWorldWithOnePlayer();
    /**
     * 
     * @return a World implementation with more player
     */
    World createWorldWithMorePlayer(List<Entity> players);

    World createBasicWorld();
}
