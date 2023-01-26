package it.unibo.t2sgame;

import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.GameFactoryImpl;
import it.unibo.t2sgame.view.impl.SceneFactoryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class T2SFxApplication extends Application {

 
    private Game game = new GameFactoryImpl().createSinglePlayerGame();
    @Override
    public void start(Stage primaryStage) throws Exception {
        var scene = new SceneFactoryImpl().createJavaFXScene(); 
        game.setScene(scene);
        // Needing to set the observer view
        scene.initialize();
        /*
         * Letting the JavaFx Thread to be free in order to handle the GUI.
         * Not creating a new thread to handle game loop would JavaFx Thread
         * to be used in the loop causing the crash of the GUI 
         */
        new Thread(() -> this.run(new GameFactoryImpl().createSinglePlayerGame())).start();
    }


    public void run(Game game){
        // Initialize the game initializing scene settings
        this.game.initSettings();
        // Initialize the game initializing entities
        this.game.initGame();
        // Starting the game
        this.game.start();
    }
    
}
