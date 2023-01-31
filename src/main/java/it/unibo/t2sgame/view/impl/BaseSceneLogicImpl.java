package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.core.engine.impl.GameEngineImpl;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.GameFactoryImpl;
import javafx.stage.Stage;

public class BaseSceneLogicImpl {
    
    public void runScene(Game game, Stage stage){
        var scene = new SceneFactoryJavaFXImpl(stage).createGameScene();
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

    public void createSinglePlayer(Stage stage) {
        this.runScene(new GameFactoryImpl().createSinglePlayerGame(), stage);
    }


    public void createMultiPlayer(Stage stage) {
        this.runScene(new GameFactoryImpl().createMultiPlayerGame(), stage);
    }
}
