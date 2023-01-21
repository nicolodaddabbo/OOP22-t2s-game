package it.unibo.t2sgame;

import it.unibo.t2sgame.core.impl.GameEngineFactoryImpl;
import it.unibo.t2sgame.core.impl.GameFactoryImpl;
import it.unibo.t2sgame.view.impl.SceneFactoryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class T2SFxApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var scene = new SceneFactoryImpl().createJavaFXScene(); 
        var engine = new GameEngineFactoryImpl().createGameEngineWithFpsLock(scene);
        // Needing to set the observer view
        //scene.setObserver(engine);
        scene.initialize();
        /*
         * Letting the JavaFx Thread to be free in order to handle the GUI.
         * Not creating a new thread to handle game loop would JavaFx Thread
         * to be used in the loop causing the crash of the GUI 
         */
        new Thread(() -> engine.setGame(new GameFactoryImpl().createSinglePlayerGame()).run()).start();
    }
    
}
