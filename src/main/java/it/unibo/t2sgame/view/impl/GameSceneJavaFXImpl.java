package it.unibo.t2sgame.view.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.view.api.AbstractGameScene;
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
/**
 * Scene representing the implementation of a GameScene using JavaFX.
 */
public class GameSceneJavaFXImpl extends AbstractGameScene {
    private Canvas canvas;
    private GraphicsContext gContext;
    private Map<String, Image> cachedSprites;
    private final Stage stage;
    private Text round = new Text();
    private double dpiW;
    private double dpiH;
    private BackgroundImage backgroundImage;
    private static final double HEARTSIZE = 40;
    private static final double BACKGROUNDTILESIZE = 300;
    private static final double PADDING = 10;

    GameSceneJavaFXImpl(final Stage stage, final Window window) {
        super(window);
        this.stage = stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        var root = new Pane();
        var map = this.gameEngine.getGame().getWorld().getMap();
        var screenBounds = Screen.getPrimary().getBounds();
        /*
         * takes the width and height of the primary screen and proportion it on the
         * static sizes
         */
        this.dpiW = screenBounds.getWidth() / map.getWidth();
        this.dpiH = screenBounds.getHeight() / map.getHeight();
        var proportionedWidth = map.getWidth() * this.dpiW;
        var proportionedHeight = map.getHeight() * this.dpiH;
        storeSprites();
        var scene = new Scene(root, proportionedWidth, proportionedHeight, Color.BLACK);
        /* initializing all text settings settings */
        this.round.setText("");
        this.round.setFont(Font.font(null, FontWeight.BOLD, FONTSIZE * this.dpiW));
        this.round.setTextOrigin(VPos.BOTTOM);
        this.round.setTextAlignment(TextAlignment.CENTER);
        this.round.setStroke(Color.WHITE);
        this.round.setY(proportionedHeight);
        root.getChildren().add(this.round);
        root.setBackground(new Background(backgroundImage));
        this.canvas = new Canvas(proportionedWidth, proportionedHeight);
        this.gContext = this.canvas.getGraphicsContext2D();
        this.graphic = new GraphicJavaFXImpl(this.gContext, this.dpiW, this.dpiH);
        scene.setOnKeyPressed(event -> keyInControllers.forEach(c -> c.notifyKeyPressed(event.getCode().getCode())));
        scene.setOnKeyReleased(event -> keyInControllers.forEach(c -> c.notifyKeyReleased(event.getCode().getCode())));
        root.getChildren().add(this.canvas);
        root.getChildren().get(root.getChildren().indexOf(this.round)).toFront();
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            this.gameEngine.updateGraphics(graphic);
            gContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            this.gameEngine.updateGraphics(this.graphic);
            this.getGame().getWorld().getPlayers().forEach(p -> p.getComponent(HealthComponent.class).ifPresent(c -> {
                Stream.iterate(0, i -> i + 1)
                        .limit(c.getHealth())
                            .forEach(n -> this.gContext.drawImage(cachedSprites.get("full_heart"), 
                                    (HEARTSIZE + PADDING) * this.dpiW * n, 0, HEARTSIZE * this.dpiW, HEARTSIZE * this.dpiW));
            }));
            this.round.setText("Round " + this.gameEngine.getGame().getState().getRound());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver() {
        Platform.runLater(() -> this.window.createGameOverScene(this.gameEngine.getGame().getState().getRound()).initialize());
    }

    private void storeSprites() {
        cachedSprites = new HashMap<>();
        try {
            this.cachedSprites.put("full_heart",
                    new Image(new FileInputStream("src/main/resources/sprites/heart_darker.png")));
            backgroundImage = new BackgroundImage(
                    new Image(new FileInputStream("src/main/resources/sprites/Brickwall5_Texture.png"),
                            BACKGROUNDTILESIZE * this.dpiW, BACKGROUNDTILESIZE * this.dpiH, false, true),
                    BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
