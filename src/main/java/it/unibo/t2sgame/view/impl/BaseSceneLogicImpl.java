package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.core.engine.impl.GameEngineImpl;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.GameFactoryImpl;
import it.unibo.t2sgame.view.api.Window;
/**
 * the class that represents the basic logic of the initializazion of a basic game scene. 
 */
public class BaseSceneLogicImpl {
    /**
     * method used to run and initialize the scene.
     * @param game the type of game 
     * @param window in which the game is going to be rendered
     */
    private void runScene(final Game game, final Window window) {
        var scene = window.createGameScene();
        var engine = new GameEngineImpl(scene, game);
        scene.setEngine(engine);
        scene.initialize();
        /*
         * Letting the JavaFx Thread to be free in order to handle the GUI.
         * Not creating a new thread to handle game loop would JavaFx Thread
         * to be used in the loop causing the crash of the GUI 
         */
        new Thread(engine::run).start();
    }
    /**
     * method called when the user wants to create a single player game.
     * @param window in which the game is going to be rendered
     */
    public void createSinglePlayer(final Window window) {
        this.runScene(new GameFactoryImpl().createSinglePlayerGame(), window);
    }
    /**
     * method called when the user wants to create a multi player game.
     * @param window in which the game is going to be rendered
     */
    public void createMultiPlayer(final Window window) {
        this.runScene(new GameFactoryImpl().createMultiPlayerGame(), window);
    }
}
