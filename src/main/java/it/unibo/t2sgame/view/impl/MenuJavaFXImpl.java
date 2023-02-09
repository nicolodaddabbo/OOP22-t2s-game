package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.AbstractBaseScene;
import it.unibo.t2sgame.view.api.Window;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * class representing a MenuScene that uses JavaFX.
 */
public class MenuJavaFXImpl extends AbstractBaseScene {
    private final Stage stage;
    private static final double PREFBUTTONWIDTH = 300;
    private static final double PREFBUTTONHEIGHT = 100;

    MenuJavaFXImpl(final Stage stage, final Window window) {
        super(window);
        this.stage = stage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        var root = new GridPane();
        Button singlePlayer = new Button("Single Player");
        Button multiPlayer = new Button("Multiplayer");
        var screenBounds = Screen.getPrimary().getBounds();
        var dpiW = screenBounds.getWidth() / AbstractBaseScene.getBaseWidth();
        var dpiH = screenBounds.getHeight() / AbstractBaseScene.getBaseHeight();
        GridPane.setHalignment(singlePlayer, HPos.CENTER);
        GridPane.setHalignment(multiPlayer, HPos.CENTER);
        root.setVgap(AbstractBaseScene.getPadding() * dpiH);
        singlePlayer.setFont(Font.font(null, FontWeight.BOLD, AbstractBaseScene.getFontSize() * dpiW));
        multiPlayer.setFont(singlePlayer.getFont());
        singlePlayer.setPrefSize(MenuJavaFXImpl.PREFBUTTONWIDTH * dpiW, MenuJavaFXImpl.PREFBUTTONHEIGHT * dpiH);
        multiPlayer.setPrefSize(singlePlayer.getPrefWidth(), singlePlayer.getPrefHeight());
        root.setMinSize(AbstractBaseScene.getBaseWidth() * dpiW / 2, AbstractBaseScene.getBaseHeight() * dpiH / 2);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:#000");
        root.add(singlePlayer, 1, 1);
        root.add(multiPlayer, 1, 2);
        Scene scene = new Scene(root, AbstractBaseScene.getBaseWidth() / 2, AbstractBaseScene.getBaseHeight() / 2, Color.BLACK);
        stage.setTitle("T2S-game");
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                this.close();
            }
        });
        stage.setOnCloseRequest(event -> this.close());
        BaseSceneLogicImpl sceneImpl = new BaseSceneLogicImpl();
        singlePlayer.setOnAction(event -> sceneImpl.createSinglePlayer(this.getWindow()));
        multiPlayer.setOnAction(event -> sceneImpl.createMultiPlayer(this.getWindow()));
        stage.setScene(scene);
        stage.show();
    }

    private void close() {
        Platform.exit();
        System.exit(0);
    }
}
