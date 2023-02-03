package it.unibo.t2sgame.view.impl;


import it.unibo.t2sgame.view.api.AbstractBaseScene;
import it.unibo.t2sgame.view.api.Window;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuJavaFXImpl extends AbstractBaseScene{

    private final Stage stage;

    public MenuJavaFXImpl(final Stage stage, final Window window) {
        super(window);
        this.stage = stage;
    }

    @Override
    public void initialize() {
        Group root = new Group();    
        Button singlePlayer = new Button("Single Player");
        singlePlayer.setTranslateX(150);
        singlePlayer.setTranslateY(60);
        Button multiPlayer = new Button("Multiplayer");
        multiPlayer.setTranslateX(300);
        multiPlayer.setTranslateY(60);
        root.getChildren().add(singlePlayer);
        root.getChildren().add(multiPlayer);
        Scene scene = new Scene(root, 800, 800, Color.BLACK);
        stage.setTitle("T2S-game");
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode().equals(KeyCode.ESCAPE)) {
                this.close();
            }    
        });
        stage.setOnCloseRequest(event -> {
            this.close();
        });  
        BaseSceneLogicImpl sceneImpl = new BaseSceneLogicImpl();
        singlePlayer.setOnAction(event -> {
            sceneImpl.createSinglePlayer(this.window);
        });
        multiPlayer.setOnAction(event -> {
            sceneImpl.createMultiPlayer(this.window);
        });
        stage.setScene(scene);
        stage.show();
    }

    private void close(){
        Platform.exit();
        System.exit(0);
    }
    
}
