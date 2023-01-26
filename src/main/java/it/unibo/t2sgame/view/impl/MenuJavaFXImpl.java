package it.unibo.t2sgame.view.impl;


import it.unibo.t2sgame.view.api.BaseScene;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuJavaFXImpl implements BaseScene{

    private final Stage stage;

    public MenuJavaFXImpl(final Stage stage) {
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
        Scene scene = new Scene(root, 1200, 800, Color.BLACK);
        BaseSceneLogicImpl sceneImpl = new BaseSceneLogicImpl();
        singlePlayer.setOnAction(event -> {
            sceneImpl.createSinglePlayer(stage);
        });
        multiPlayer.setOnAction(event -> {
            sceneImpl.createMultiPlayer(stage);
        });
        stage.setScene(scene);
    }
    
}
