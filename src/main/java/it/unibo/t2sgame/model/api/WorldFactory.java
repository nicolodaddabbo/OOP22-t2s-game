package it.unibo.t2sgame.model.api;

import java.util.List;

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
}
