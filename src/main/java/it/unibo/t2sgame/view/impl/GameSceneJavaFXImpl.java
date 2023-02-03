package it.unibo.t2sgame.view.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import it.unibo.t2sgame.core.components.impl.GraphicComponent;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.view.api.AbstractGameScene;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;
import it.unibo.t2sgame.view.api.Window;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameSceneJavaFXImpl extends AbstractGameScene {
    private Canvas canvas;
    private GraphicsContext gContext;
    private Map<String, Image> cachedSprites;
    private final Stage stage;
    private Text round = new Text();
    private double dpiW;
    private double dpiH;
    private BackgroundImage backgroundImage;
    private static final double BASEWIDTH = 1920;
    private static final double BASEHEIGHT = 1080;

    public GameSceneJavaFXImpl(Stage stage, Window window) {
        super(window);
        this.stage = stage;
    }

    @Override
    public void initialize() {
        var root = new Pane();
        var map = this.gameEngine.getGame().getWorld().getMap();
        var screenBounds = Screen.getPrimary().getBounds();
        /*
         * takes the width and height of the primary screen and proportion it on the
         * static sizes
         */
        this.dpiW = screenBounds.getWidth() / GameSceneJavaFXImpl.BASEWIDTH;
        this.dpiH = screenBounds.getHeight() / GameSceneJavaFXImpl.BASEHEIGHT;
        var proportionedWidth = map.getWidth() * this.dpiW;
        var proportionedHeight = map.getHeight() * this.dpiH;
        storeSprites();
        var scene = new Scene(root, proportionedWidth, proportionedHeight, Color.BLACK);
        /* initializing all text settings settings */
        this.round.setText("");
        this.round.setFont(Font.font(null, FontWeight.BOLD, 30 * this.dpiW));
        this.round.setTextOrigin(VPos.BOTTOM);
        this.round.setTextAlignment(TextAlignment.CENTER);
        this.round.setY(proportionedHeight);
        this.round.setStroke(Color.WHITE);
        root.getChildren().add(this.round);
        root.setBackground(new Background(backgroundImage));
        this.canvas = new Canvas(proportionedWidth, proportionedHeight);
        this.gContext = this.canvas.getGraphicsContext2D();
        this.graphic = new GraphicJavaFXImpl(this.gContext, this.dpiW, this.dpiH);
        scene.setOnKeyPressed(event -> keyInControllers.forEach(c -> c.notifyKeyPressed(event.getCode().getCode())));
        scene.setOnKeyReleased(event -> keyInControllers.forEach(c -> c.notifyKeyReleased(event.getCode().getCode())));
        root.getChildren().add(this.canvas);
        root.getChildren().get(root.getChildren().indexOf(this.round)).toFront();
        stage.sizeToScene();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void storeSprites() {
        cachedSprites = new HashMap<>();
        try {
            this.cachedSprites.put("full_heart",
                    new Image(new FileInputStream("src/main/resources/sprites/heart_darker.png")));
            backgroundImage = new BackgroundImage(
                    new Image(new FileInputStream("src/main/resources/sprites/Brickwall5_Texture.png"),
                            300 * this.dpiW, 300 * this.dpiH, false, true),
                    BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        Platform.runLater(() -> {
            gContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            this.gameEngine.getComponents(GraphicComponent.class).forEach(c -> {
                c.setGraphics(this.graphic);
                c.update();
            });
            this.getGame().getWorld().getPlayers().get(0).getComponent(HealthComponent.class).ifPresent(c -> {
                Stream.iterate(0, i -> i + 1)
                        .limit(c.getHealth())
                        .forEach(n -> this.gContext.drawImage(cachedSprites.get("full_heart"), 50 * this.dpiW * n, 0,
                                40 * this.dpiW, 40 * this.dpiW));
            });
            this.round.setText("Round " + this.gameEngine.getGame().getState().getRound());
        });
    }
}
