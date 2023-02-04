package it.unibo.t2sgame.view.impl;


import it.unibo.t2sgame.view.api.AbstractBaseScene;
import it.unibo.t2sgame.view.api.Window;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuJavaFXImpl extends AbstractBaseScene{

    private final Stage stage;

    public MenuJavaFXImpl(final Stage stage, final Window window) {
        super(window);
        this.stage = stage;
    }

    @Override
    public void initialize() {
        var root = new GridPane();    
        Button singlePlayer = new Button("Single Player");
        Button multiPlayer = new Button("Multiplayer");
        var screenBounds = Screen.getPrimary().getBounds();
        var dpiW = screenBounds.getWidth() / AbstractBaseScene.BASEWIDTH;
        var dpiH = screenBounds.getHeight() / AbstractBaseScene.BASEHEIGHT;
        GridPane.setHalignment(singlePlayer, HPos.CENTER);
        GridPane.setHalignment(multiPlayer, HPos.CENTER);
        root.setVgap(10*dpiH);
        singlePlayer.setFont(Font.font(null, FontWeight.BOLD, 30 * dpiW));
        multiPlayer.setFont(singlePlayer.getFont());
        singlePlayer.setPrefSize(300*dpiW, 100*dpiH);
        multiPlayer.setPrefSize(singlePlayer.getPrefWidth(), singlePlayer.getPrefHeight());
        root.setMinSize(AbstractBaseScene.BASEWIDTH * dpiW / 2, AbstractBaseScene.BASEHEIGHT * dpiH / 2);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:#000");
        
        root.add(singlePlayer, 1, 1);
        root.add(multiPlayer, 1, 2);
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
