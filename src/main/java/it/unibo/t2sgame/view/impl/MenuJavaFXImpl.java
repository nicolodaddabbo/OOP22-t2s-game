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
        final var root = new GridPane();
        final Button singlePlayer = new Button("Single Player");
        final Button withCompanion = new Button("With Companion");
        final var screenBounds = Screen.getPrimary().getBounds();
        final var dpiW = screenBounds.getWidth() / AbstractBaseScene.getBaseWidth();
        final var dpiH = screenBounds.getHeight() / AbstractBaseScene.getBaseHeight();
        GridPane.setHalignment(singlePlayer, HPos.CENTER);
        GridPane.setHalignment(withCompanion, HPos.CENTER);
        root.setVgap(AbstractBaseScene.getPadding() * dpiH);
        singlePlayer.setFont(Font.font(null, FontWeight.BOLD, AbstractBaseScene.getFontSize() * dpiW));
        withCompanion.setFont(singlePlayer.getFont());
        singlePlayer.setPrefSize(MenuJavaFXImpl.PREFBUTTONWIDTH * dpiW, MenuJavaFXImpl.PREFBUTTONHEIGHT * dpiH);
        withCompanion.setPrefSize(singlePlayer.getPrefWidth(), singlePlayer.getPrefHeight());
        withCompanion.setWrapText(true);
        root.setMinSize(AbstractBaseScene.getBaseWidth() * dpiW / 2, AbstractBaseScene.getBaseHeight() * dpiH / 2);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:#000");
        root.add(singlePlayer, 1, 1);
        root.add(withCompanion, 1, 2);
        final Scene scene = new Scene(root, AbstractBaseScene.getBaseWidth() / 2,
                AbstractBaseScene.getBaseHeight() / 2, Color.BLACK);
        stage.setTitle("T2S-game");
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                this.close();
            }
        });
        stage.setOnCloseRequest(event -> this.close());
        final BaseSceneLogicImpl sceneImpl = new BaseSceneLogicImpl();
        singlePlayer.setOnAction(event -> sceneImpl.createSinglePlayer(this.getWindow()));
        withCompanion.setOnAction(event -> sceneImpl.createMultiPlayer(this.getWindow()));
        stage.setScene(scene);
        stage.show();
    }

    private void close() {
        Platform.exit();
        Runtime.getRuntime().exit(0);
    }
}
