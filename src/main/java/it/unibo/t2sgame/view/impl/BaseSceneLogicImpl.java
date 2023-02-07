package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.core.engine.impl.GameEngineImpl;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.GameFactoryImpl;
import it.unibo.t2sgame.view.api.Window;

public class BaseSceneLogicImpl {
    public void runScene(final Game game, final Window window) {
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

    public void createSinglePlayer(final Window window) {
        this.runScene(new GameFactoryImpl().createSinglePlayerGame(), window);
    }


    public void createMultiPlayer(final Window window) {
        this.runScene(new GameFactoryImpl().createMultiPlayerGame(), window);
    }
}
