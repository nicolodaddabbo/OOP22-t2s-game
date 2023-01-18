package it.unibo.t2sgame.core.api;

public interface GameFactory {
    /**
     * 
     * @return a new game whose logics is setted for Single Player mode
     */
    Game createSinglePlayerGame();
    /**
     * 
     * @return a new game whose logics is setted for Multi Player mode
     */
    Game createMultiPlayerGame();
}
