package it.unibo.t2sgame.view.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.view.api.AbstractBaseScene;
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
 * class representing the implementation of a GameScene using JavaFX.
 */
public class GameSceneJavaFXImpl extends AbstractGameScene {
    private Canvas canvas;
    private GraphicsContext gContext;
    private Map<String, Image> cachedSprites;
    private final Stage stage;
    private Text roundText = new Text();
    private Text fpsText = new Text();
    private double dpiW;
    private double dpiH;
    private BackgroundImage backgroundImage;
    private int round;
    private static final double HEARTSIZE = 40;
    private static final double BACKGROUNDTILESIZE = 300;
    private static final int POWERUPROUND = 5;

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
        var map = this.getGameEngine().getGame().getWorld().getMap();
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
        this.roundText.setText("");
        this.roundText.setFont(Font.font(null, FontWeight.BOLD, AbstractBaseScene.getFontSize() * this.dpiW));
        this.roundText.setTextOrigin(VPos.BOTTOM);
        this.roundText.setTextAlignment(TextAlignment.CENTER);
        this.roundText.setStroke(Color.WHITE);
        this.roundText.setY(proportionedHeight);
        this.fpsText.setText("");
        this.fpsText.setFont(Font.font(null, FontWeight.BOLD, AbstractBaseScene.getFontSize() * this.dpiW));
        this.fpsText.setTextOrigin(VPos.BOTTOM);
        this.fpsText.setTextAlignment(TextAlignment.CENTER);
        this.fpsText.setStroke(Color.WHITE);
        this.fpsText.setY(proportionedHeight);
        this.fpsText.setX(proportionedWidth - getFontSize() * 3);
        root.getChildren().add(this.roundText);
        root.getChildren().add(this.fpsText);
        root.setBackground(new Background(backgroundImage));
        this.canvas = new Canvas(proportionedWidth, proportionedHeight);
        this.gContext = this.canvas.getGraphicsContext2D();
        this.setGraphic(new GraphicJavaFXImpl(this.gContext, this.dpiW, this.dpiH));
        scene.setOnKeyPressed(
                event -> this.getKeyInControllers().forEach(c -> c.notifyKeyPressed(event.getCode().getCode())));
        scene.setOnKeyReleased(
                event -> this.getKeyInControllers().forEach(c -> c.notifyKeyReleased(event.getCode().getCode())));
        root.getChildren().add(this.canvas);
        root.getChildren().get(root.getChildren().indexOf(this.roundText)).toFront();
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
            this.gContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
            this.getGameEngine().updateGraphics(this.getGraphic());
            this.getGameEngine().getGame().getWorld().getPlayers().forEach(p -> p.getComponent(HealthComponent.class)
                    .ifPresent(c -> {
                        Stream.iterate(0, i -> i + 1)
                                .limit(c.getHealth())
                                .forEach(n -> this.gContext.drawImage(this.cachedSprites.get("full_heart"),
                                        (HEARTSIZE + getPadding()) * this.dpiW * n, 0, HEARTSIZE * this.dpiW,
                                        HEARTSIZE * this.dpiW));
                    }));
            round = this.getGameEngine().getGame().getState().getRound();
            this.roundText.setText(this.round % POWERUPROUND == 0
                    ? "ROUND" + this.round + " - POWERUP OBTAINED"
                    : "ROUND " + this.round);
        });
    }

    /**
     * {@inheritDoc}
     */
    public void renderFPS(final int fps) {
        this.fpsText.setText("" + fps);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver() {
        Platform.runLater(
                () -> this.getWindow().createGameOverScene(this.getGameEngine().getGame().getState().getRound())
                        .initialize());
    }

    private void storeSprites() {
        cachedSprites = new HashMap<>();
        try {
            this.cachedSprites.put("full_heart",
                    new Image(new FileInputStream("src/main/resources/sprites/heart_darker.png")));
            this.backgroundImage = new BackgroundImage(
                    new Image(new FileInputStream("src/main/resources/sprites/Brickwall5_Texture.png"),
                            BACKGROUNDTILESIZE * this.dpiW, BACKGROUNDTILESIZE * this.dpiH, false, true),
                    BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
